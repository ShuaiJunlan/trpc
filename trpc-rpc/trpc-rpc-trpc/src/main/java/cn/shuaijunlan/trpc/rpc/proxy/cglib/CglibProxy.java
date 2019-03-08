package cn.shuaijunlan.trpc.rpc.proxy.cglib;

import cn.shuaijunlan.trpc.rpc.TrpcInvoker;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:46 PM 3/8/19.
 */
public class CglibProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(CglibProxy.class);

    public static Object newInstance(Class clazz){
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{clazz});

        TrpcInvoker trpcInvoker = new TrpcInvoker();

        Callback interceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                assert objects != null;
                String[] types = new String[objects.length];
                String[] values = new String[objects.length];
                int i = 0;
                for (Object arg : objects){
                    types[i] = arg.getClass().getName();
                    values[i++] = arg.toString();
                    LOGGER.debug("Parameters types: {}", Arrays.toString(types));
                }
                return trpcInvoker.invoke(clazz.getName(), method.getName(), values, types, true, false);
            }
        };

        Callback[] callbacks = new Callback[]{interceptor};
        enhancer.setCallbacks(callbacks);
        return enhancer.create();
    }
}
