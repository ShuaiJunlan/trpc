package cn.shuaijunlan.trpc.remoting.api.protocol;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:29 PM 2/28/19.
 */
public class TrpcProtocol extends AbstractProtocol {

    /**
     * two bytes
     */
    private static final short MAGIC_NUMBER = 0x0012;
    /**
     * one byte
     */
    private byte requestType;
    /**
     * one byte
     */
    private byte serializationType;

    /**
     * eights bytes
     */
    private long requestID;

    /**
     * four bytes
     */
    private int dataLength;
    /**
     * data
     */
    private Object data;

    public static short getMagicNumber() {
        return MAGIC_NUMBER;
    }

    public byte getRequestType() {
        return requestType;
    }

    public void setRequestType(byte requestType) {
        this.requestType = requestType;
    }

    public byte getSerizlizationType() {
        return serializationType;
    }

    public void setSerizlizationType(byte serializationType) {
        this.serializationType = serializationType;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TrpcProtocol{" +
                "requestType=" + requestType +
                ", serizlizationType=" + serializationType +
                ", requestID=" + requestID +
                ", dataLength=" + dataLength +
                ", data=" + data +
                '}';
    }
}
