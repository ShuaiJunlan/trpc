package cn.shuaijunlan.trpc.serialization.api;

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
    byte[] serialize(Object o);

    /**
     * deserialize
     * @param bytes
     * @return
     */
    Object deserialize(byte[] bytes);
}
