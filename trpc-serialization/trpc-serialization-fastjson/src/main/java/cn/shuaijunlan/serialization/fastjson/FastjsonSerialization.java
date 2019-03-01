package cn.shuaijunlan.serialization.fastjson;

import cn.shuaijunlan.trpc.serialization.api.Serialization;
import com.alibaba.fastjson.JSON;

import java.io.IOException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:59 PM 3/1/19.
 */
public class FastjsonSerialization implements Serialization {
    @Override
    public byte[] serialize(Object o) throws IOException {
        return JSON.toJSONString(o).getBytes();
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        return JSON.parseObject(new String(bytes));
    }
    public static final FastjsonSerialization FASTJSON_SERIALIZATION = new FastjsonSerialization();
}
