package cn.shuaijunlan.trpc.common.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:40 PM 2/28/19.
 */
public class JdkProxy {
    public static <T> T newInstance(final Class<T> interfaces){
        T t = (T) Proxy.newProxyInstance(interfaces.getClassLoader(), new Class<?>[]{interfaces}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName() + ":" + args[0]);
                return args[0];
            }
        });
        return t;
    }
}
