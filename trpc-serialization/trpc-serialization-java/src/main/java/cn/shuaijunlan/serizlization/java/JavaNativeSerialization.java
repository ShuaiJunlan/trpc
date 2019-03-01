package cn.shuaijunlan.serizlization.java;

import cn.shuaijunlan.trpc.common.utils.Assert;
import cn.shuaijunlan.trpc.serialization.api.Serialization;

import java.io.*;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:08 PM 2/28/19.
 */
public class JavaNativeSerialization implements Serialization {
    private JavaNativeSerialization(){}

    @Override
    public byte[] serialize(Object o) throws IOException {
        Assert.notNull(o, "object == null");
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
        objectOutputStream.writeObject(o);
        byte[] arr = arrayOutputStream.toByteArray();
        objectOutputStream.close();
        arrayOutputStream.close();
        return arr;
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        Assert.notNull(bytes, "bytes == null");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Object obj = objectInputStream.readObject();
        objectInputStream.close();
        inputStream.close();
        return obj;
    }

    public static final JavaNativeSerialization JAVA_NATIVE_SERIALIZATION = new JavaNativeSerialization();

}
