package cn.shuaijunlan.trpc.common.utils;

import cn.shuaijunlan.trpc.common.A;
import org.junit.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:53 AM 3/1/19.
 */
public class ClassUtilsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtilsTest.class);

    @Test
    public void getAllClassByInterface() {
        // System.out.println(ClassUtils.getAllClassByInterface(cn.shuaijunlan.trpc.common.A.class).size());

        Reflections reflections = new Reflections(this.getClass().getPackage().getName().split("\\.")[0]);
        Set<Class<? extends A>> subTypes = reflections.getSubTypesOf(A.class);
        LOGGER.debug("Size: {}", subTypes.size());
        for (Class c : subTypes){
            LOGGER.debug(c.getName());
        }
    }
}