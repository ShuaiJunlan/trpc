package cn.shuaijunlan.serizlization.java;

import cn.shuaijunlan.trpc.serialization.api.Serialization;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:24 PM 2/28/19.
 */
public class JavaNativeSerializationTest {
    static {
    }

    private Serialization serialization = new JavaNativeSerialization();
    @Test
    public void serialize() {
        Employee employee = new Employee();
        employee.name = "shuaijunlan";
        employee.age = 14;
        employee.height = 190;

        try {
            byte[] bytes = serialization.serialize(employee);
            System.out.println(bytes.length);
            Employee employee1 = (Employee) serialization.deserialize(bytes);
            assertEquals(employee.toString(), employee1.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deserialize() {
    }
}