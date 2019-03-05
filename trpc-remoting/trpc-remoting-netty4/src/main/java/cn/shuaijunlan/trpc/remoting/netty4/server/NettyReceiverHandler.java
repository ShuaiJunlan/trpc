package cn.shuaijunlan.trpc.remoting.netty4.server;

import cn.shuaijunlan.serizlization.java.JavaNativeSerialization;
import cn.shuaijunlan.trpc.common.utils.ClassUtils;
import cn.shuaijunlan.trpc.remoting.api.message.RequestMessage;
import cn.shuaijunlan.trpc.remoting.api.message.ResponseMessage;
import cn.shuaijunlan.trpc.remoting.api.protocol.AbstractProtocol;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:56 PM 2/28/19.
 */
public class NettyReceiverHandler extends SimpleChannelInboundHandler<AbstractProtocol> {
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(20, 20, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10000), new ThreadFactory() {
        private final AtomicInteger threadCount = new AtomicInteger(0);

        private static final String PREFIX = "NETTY-SERVER-THREAD-";
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(PREFIX+threadCount.getAndIncrement());
            return thread;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    private static final HashMap<String, HashSet<Class<?>>> CLAZZS = new HashMap<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyReceiverHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractProtocol msg) throws Exception {
        if (msg instanceof TrpcProtocol){
            LOGGER.debug(msg.toString());

            TrpcProtocol protocol = (TrpcProtocol) msg;
            Channel channel = ctx.channel();

            THREAD_POOL_EXECUTOR.execute(new Runnable() {
                @Override
                public void run() {
                    byte[] data =  protocol.getData();
                    RequestMessage requestMessage = null;
                    try {
                        requestMessage = (RequestMessage) JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION.deserialize(data);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    LOGGER.debug(requestMessage.toString());

                    HashSet<Class<?>> classes;
                    if ((classes = CLAZZS.get(requestMessage.getInterfaceName())) == null){
                        try {
                            classes = (HashSet<Class<?>>) ClassUtils.getAllSubClassByInterfaceName(requestMessage.getInterfaceName());
                            if (classes.size() == 0){
                                LOGGER.warn("Warning that the size of the subClass which implement the interface [{}] is zero!", requestMessage.getInterfaceName());
                                return;
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        CLAZZS.put(requestMessage.getInterfaceName(), classes);
                    }

                    Class[] re = new Class[classes.size()];
                    classes.toArray(re);

                    // re[0].newInstance();
                    // Class.forName(requestMessage.getInterfaceName())
                    // Class.forName("org.example.Test").newInstance();

                    String methodName = requestMessage.getMethodName();

                    Class t = re[0];
                    Class[] parameterTypes = new Class[requestMessage.getParameterTypes().length];
                    Object o = null;
                    for (int i = 0;i < parameterTypes.length; i++){
                        try {
                            parameterTypes[i] = Class.forName(requestMessage.getParameterTypes()[i]);
                            Method method = t.getMethod(methodName, parameterTypes);
                            o = method.invoke(re[0].newInstance(), requestMessage.getParameterValues());
                            LOGGER.debug("Invoke return [{}]", o);
                        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }

                    //returning
                    ResponseMessage responseMessage = new ResponseMessage();
                    responseMessage.setResponseID(protocol.getRequestID());
                    responseMessage.setReturnType("java.lang.String");
                    responseMessage.setReturnValue((String) o);
                    responseMessage.setAttachment(new HashMap<>());

                    channel.writeAndFlush(responseMessage);
                }
            });

        }
        // super.channelRead(ctx, msg);
    }
}
