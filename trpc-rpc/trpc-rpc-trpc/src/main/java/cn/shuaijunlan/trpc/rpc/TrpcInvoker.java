package cn.shuaijunlan.trpc.rpc;

import cn.shuaijunlan.trpc.remoting.api.message.RequestMessage;
import cn.shuaijunlan.trpc.remoting.netty4.NettyClient;
import cn.shuaijunlan.trpc.rpc.bootstrap.TrpcClientBootstrap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 1:32 PM 3/5/19.
 */
public class TrpcInvoker {
    public Object invoke(String interfaceName, String methodName, boolean isAsync, boolean isOneWay){
        //获取所有的实现
        List<NettyClient> all = getAllRpcInterfacesImpls();

        //基于负载均衡算法选择一个连接
        NettyClient nettyClient = getNettyClientLoadBalance(all);

        //构建请求消息体
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setMethodName(methodName);
        requestMessage.setInterfaceName(interfaceName);
        requestMessage.setRequestID(TrpcContext.REQUEST_ID.getAndIncrement());

        //one way
        if (isOneWay){
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
            requestMessage.setRequestType((byte)2);
            TrpcFuture trpcFuture = new TrpcFuture();
            TrpcContext.FUTURE_CONCURRENT_HASH_MAP.put(requestMessage.getRequestID(), trpcFuture);

            try {
                return trpcFuture.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        }else { //异步
            requestMessage.setRequestType((byte)3);
            TrpcFuture trpcFuture = new TrpcFuture();
            TrpcContext.FUTURE_CONCURRENT_HASH_MAP.put(requestMessage.getRequestID(), trpcFuture);
        }
        return null;
    }
    private List<NettyClient> getAllRpcInterfacesImpls(){
        return new ArrayList<>();
    }
    private NettyClient getNettyClientLoadBalance(List<NettyClient> clients){
        return clients.get(0);
    }
}
