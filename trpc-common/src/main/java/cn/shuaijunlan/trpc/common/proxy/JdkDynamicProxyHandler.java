package cn.shuaijunlan.trpc.common.proxy;

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
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOGGER.debug("Invocation method name: {}", method.getName());

        LOGGER.debug("Parameter values: {}", Arrays.toString(args));
        if (args != null){
            List<String> list = new ArrayList<>();
            for (Object arg : args){
                list.add(arg.getClass().getName());
                LOGGER.debug("Parameters types: {}", list.toString());
            }
        }


        return args[0];
    }
}
