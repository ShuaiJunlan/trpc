package cn.shuaijunlan.serialization.fastjson;

import cn.shuaijunlan.trpc.serialization.api.Serialization;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:10 PM 3/1/19.
 */
public class FastjsonSerializationTest {

    private Serialization serialization = FastjsonSerialization.FASTJSON_SERIALIZATION;
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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deserialize() {
    }
}