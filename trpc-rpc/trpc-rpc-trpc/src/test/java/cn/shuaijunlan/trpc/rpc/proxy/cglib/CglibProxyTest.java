package cn.shuaijunlan.trpc.rpc.proxy.cglib;

import cn.shuaijunlan.trpc.remoting.netty4.TrpcContext;
import cn.shuaijunlan.trpc.rpc.proxy.Interfaces;
import cn.shuaijunlan.trpc.rpc.proxy.JdkDynamicProxy;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:58 PM 3/8/19.
 */
public class CglibProxyTest {
    @Test
    public void test1() throws InterruptedException {
        Interfaces interfaces = (Interfaces) CglibProxy.newInstance(Interfaces.class);
        System.out.println(interfaces.getName("hello trpc"));
        Thread.sleep(1000);
        //do others...
        Thread.sleep(1000);
        try {
            System.out.println(TrpcContext.getContext().getTrpcFuture().get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
