package cn.shuaijunlan.trpc.rpc.proxy;

import cn.shuaijunlan.trpc.remoting.netty4.TrpcContext;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:00 AM 3/1/19.
 */
public class JdkDynamicProxyTest {

    @Test
    public void testSync() {
        Interfaces interfaces = JdkDynamicProxy.newInstance(Interfaces.class);
        System.out.println(interfaces.getName("hello trpc"));
    }
    @Test
    public void testAsync() throws InterruptedException {
        Interfaces interfaces = JdkDynamicProxy.newInstance(Interfaces.class);
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