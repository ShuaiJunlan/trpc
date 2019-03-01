package cn.shuaijunlan.serialization.fastjson;

import cn.shuaijunlan.trpc.serialization.api.Serialization;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:59 PM 3/1/19.
 */
public class FastjsonSerialization implements Serialization {
    @Override
    public byte[] serialize(Object o) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("classType", o.getClass().getName());
        jsonObject.put("data", JSON.toJSONString(o));
        return jsonObject.toJSONString().getBytes();
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        JSONObject jsonObject = JSON.parseObject(new String(bytes));
        String classType = jsonObject.getString("classType");
        String data = jsonObject.getString("data");
        return JSON.parseObject(data, Class.forName(classType));
    }
    public static final FastjsonSerialization FASTJSON_SERIALIZATION = new FastjsonSerialization();
}
