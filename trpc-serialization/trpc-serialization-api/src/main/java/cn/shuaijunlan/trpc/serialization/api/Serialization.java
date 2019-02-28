package cn.shuaijunlan.trpc.serialization.api;

import java.io.IOException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:54 PM 2/28/19.
 */
public interface Serialization {
    /**
     * serialize
     * @param o
     * @return
     */
    byte[] serialize(Object o) throws IOException;

    /**
     * deserialize
     * @param bytes
     * @return
     */
    Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException;
}
