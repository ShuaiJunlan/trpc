package cn.shuaijunlan.trpc.rpc.proxy;

import org.junit.Test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:00 AM 3/1/19.
 */
public class JdkDynamicProxyTest {

    @Test
    public void newInstance() {
        Interfaces interfaces = JdkDynamicProxy.newInstance(Interfaces.class);
        System.out.println(interfaces.getName("hello trpc"));
    }
}