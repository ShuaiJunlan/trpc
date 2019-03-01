package cn.shuaijunlan.trpc.common.proxy;

import org.junit.Test;

import static org.junit.Assert.*;

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