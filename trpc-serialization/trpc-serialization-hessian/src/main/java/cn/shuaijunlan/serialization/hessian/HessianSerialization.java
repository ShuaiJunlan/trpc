package cn.shuaijunlan.serialization.hessian;

import cn.shuaijunlan.trpc.serialization.api.Serialization;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 11:19 AM 3/9/19.
 */
public class HessianSerialization implements Serialization {
    public static final HessianSerialization HESSIAN_SERIALIZATION = new HessianSerialization();

    private final SerializerFactory serializerFactory = new SerializerFactory();
    @Override
    public byte[] serialize(Object o) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(bout);
        output.setSerializerFactory(serializerFactory);
        output.writeObject(o);
        output.close();
        return bout.toByteArray();
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes, 0, bytes.length);
        Hessian2Input hin = new Hessian2Input(bin);
        hin.setSerializerFactory(serializerFactory);
        Object dst = hin.readObject();
        hin.close();
        return dst;
    }
}
