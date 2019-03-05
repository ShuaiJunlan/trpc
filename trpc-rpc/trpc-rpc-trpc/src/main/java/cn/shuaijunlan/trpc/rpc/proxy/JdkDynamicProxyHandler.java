package cn.shuaijunlan.trpc.rpc.proxy;

import cn.shuaijunlan.trpc.rpc.TrpcInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:42 AM 3/1/19.
 */
public class JdkDynamicProxyHandler implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdkDynamicProxyHandler.class);

    private final TrpcInvoker trpcInvoker = new TrpcInvoker();
    private String interfaceName;

    public JdkDynamicProxyHandler(String interfaceName){
        this.interfaceName = interfaceName;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.debug("Interface clazz: {}", interfaceName);
        LOGGER.debug("Invocation method name: {}", method.getName());

        LOGGER.debug("Parameter values: {}", Arrays.toString(args));
        String[] types = new String[args.length];
        String[] values = new String[args.length];
        int i = 0;
        if (args != null){
            for (Object arg : args){
                types[i] = arg.getClass().getName();
                values[i++] = arg.toString();
                LOGGER.debug("Parameters types: {}", Arrays.toString(types));
            }
        }
        return trpcInvoker.invoke(interfaceName, method.getName(), values, types, true, false);
    }
}
