package cn.shuaijunlan.trpc.rpc;

import cn.shuaijunlan.trpc.remoting.api.message.RequestMessage;
import cn.shuaijunlan.trpc.remoting.netty4.NettyClient;
import cn.shuaijunlan.trpc.remoting.netty4.TrpcContext;
import cn.shuaijunlan.trpc.remoting.netty4.TrpcFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 1:32 PM 3/5/19.
 */
public class TrpcInvoker {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrpcInvoker.class);

    public Object invoke(String interfaceName, String methodName, String[] argsValue, String[] argsType,  boolean isAsync, boolean isOneWay){
        LOGGER.debug("invoke");
        //获取所有的实现
        List<NettyClient> all = getAllRpcInterfacesImpls();

        //基于负载均衡算法选择一个连接
        NettyClient nettyClient = getNettyClientLoadBalance(all);

        //构建请求消息体
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setMethodName(methodName);
        requestMessage.setInterfaceName(interfaceName);
        requestMessage.setRequestID(TrpcContext.REQUEST_ID.getAndIncrement());
        requestMessage.setParameterValues(argsValue);
        requestMessage.setParameterTypes(argsType);

        //one way
        if (isOneWay){
            LOGGER.debug("oneway");
            requestMessage.setRequestType((byte)1);
            nettyClient.doWrite(requestMessage);
            try {
                return new TrpcFuture().setCompletableImmediately().get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        //同步
        if (!isAsync){
            LOGGER.debug("sync");
            requestMessage.setRequestType((byte)2);
            TrpcFuture trpcFuture = new TrpcFuture();
            nettyClient.doWrite(requestMessage);
            TrpcContext.FUTURE_CONCURRENT_HASH_MAP.put(requestMessage.getRequestID(), trpcFuture);

            try {
                return trpcFuture.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        }else { //异步
            LOGGER.debug("async");
            requestMessage.setRequestType((byte)3);
            nettyClient.doWrite(requestMessage);
            TrpcFuture trpcFuture = new TrpcFuture();
            TrpcContext.FUTURE_CONCURRENT_HASH_MAP.put(requestMessage.getRequestID(), trpcFuture);
            TrpcContext.getContext().setTrpcFuture(trpcFuture);
        }
        return null;
    }
    private List<NettyClient> getAllRpcInterfacesImpls(){
        NettyClient nettyClient = new NettyClient();
        nettyClient.doConnect("127.0.0.1", 8080);
        ArrayList<NettyClient> arrayList = new ArrayList<>();
        arrayList.add(nettyClient);

        return arrayList;
    }
    private NettyClient getNettyClientLoadBalance(List<NettyClient> clients){
        return clients.get(0);
    }
}
