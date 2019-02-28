package cn.shuaijunlan.serizlization.java;

import cn.shuaijunlan.trpc.common.util.Assert;
import cn.shuaijunlan.trpc.serialization.api.Serialization;

import java.io.*;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:08 PM 2/28/19.
 */
public class JavaNativeSerialization implements Serialization {

    @Override
    public byte[] serialize(Object o) throws IOException {
        Assert.notNull(o, "object == null");
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
        objectOutputStream.writeObject(o);
        return arrayOutputStream.toByteArray();
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return objectInputStream.readObject();
    }
}
