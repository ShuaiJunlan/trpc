package cn.shuaijunlan.trpc.common.utils;

import cn.shuaijunlan.trpc.common.utils.classutils.A;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:53 AM 3/1/19.
 */
public class ClassUtilsTest {

    @Test
    public void getAllClassByInterface() {
        System.out.println(ClassUtils.getAllClassByInterface(A.class).size());
    }
}