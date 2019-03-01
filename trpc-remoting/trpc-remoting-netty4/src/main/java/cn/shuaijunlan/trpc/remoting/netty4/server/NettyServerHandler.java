package cn.shuaijunlan.trpc.remoting.netty4.server;

import cn.shuaijunlan.trpc.common.utils.ClassUtils;
import cn.shuaijunlan.trpc.remoting.api.message.RequestMessage;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:56 PM 2/28/19.
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof TrpcProtocol){
            LOGGER.debug(msg.toString());

            TrpcProtocol protocol = (TrpcProtocol) msg;
            RequestMessage requestMessage = (RequestMessage) protocol.getData();
            HashSet<Class<?>> classes = (HashSet<Class<?>>) ClassUtils.getAllSubClassByInterfaceName(requestMessage.getInterfaceName());
            if (classes.size() == 0){
                LOGGER.warn("Warning that the size of the subClass which implement the interface [{}] is zero!", requestMessage.getInterfaceName());
                return;
            }
            Class[] re = new Class[classes.size()];
            classes.toArray(re);

            // re[0].newInstance();
            // Class.forName(requestMessage.getInterfaceName())
            // Class.forName("org.example.Test").newInstance();

            String methodName = requestMessage.getMethodName();
            Class t = re[0];
            Class[] parameterTypes = new Class[requestMessage.getParameterTypes().length];
            for (int i = 0;i < parameterTypes.length; i++){
                parameterTypes[i] = Class.forName(requestMessage.getParameterTypes()[i]);
            }
            Method method = t.getMethod(methodName, parameterTypes);
            Object o = method.invoke(re[0].newInstance(), requestMessage.getParameterValues());
            LOGGER.debug("Get invoke return [{}]", o);

        }
        super.channelRead(ctx, msg);
    }
}
