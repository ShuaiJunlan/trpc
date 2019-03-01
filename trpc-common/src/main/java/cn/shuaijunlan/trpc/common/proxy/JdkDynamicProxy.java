package cn.shuaijunlan.trpc.common.proxy;

import java.lang.reflect.Proxy;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:40 PM 2/28/19.
 */
public class JdkDynamicProxy {
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(final Class<T> interfaces){
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class<?>[]{interfaces}, new JdkDynamicProxyHandler());
    }
}
