package cn.shuaijunlan.trpc.remoting.netty4.codec;

import cn.shuaijunlan.serizlization.java.JavaNativeSerialization;
import cn.shuaijunlan.trpc.remoting.api.Codec;
import cn.shuaijunlan.trpc.remoting.api.protocol.AbstractProtocol;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import cn.shuaijunlan.trpc.serialization.api.Serialization;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:45 PM 2/28/19.
 */
public class MessageCodec implements Codec {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageCodec.class);
    private static volatile MessageCodec messageCodec = null;

    private MessageCodec(){}

    public static Codec getMessageCodecInstance(){
        if (messageCodec == null){
            synchronized (MessageCodec.class){
                if (messageCodec == null){
                    messageCodec = new MessageCodec();
                    return messageCodec;
                }
            }
        }
        return messageCodec;
    }

    @Override
    public ByteBuf encode(AbstractProtocol protocol, ByteBuf byteBuf) {
        LOGGER.debug("encode");

        if (protocol instanceof TrpcProtocol){
            byteBuf.writeShort(TrpcProtocol.getMagicNumber());
            byteBuf.writeByte(((TrpcProtocol) protocol).getRequestType());
            byteBuf.writeByte(((TrpcProtocol) protocol).getSerializationType());
            byteBuf.writeLong(((TrpcProtocol) protocol).getRequestID());
            byteBuf.writeInt(((TrpcProtocol) protocol).getDataLength());
            byteBuf.writeBytes(((TrpcProtocol) protocol).getData());
        }else {
            LOGGER.debug("else");
        }
        return byteBuf;
    }

    @Override
    public AbstractProtocol decode(ByteBuf buf) {
        LOGGER.debug("decode");

        TrpcProtocol protocol = new TrpcProtocol();
        buf.skipBytes(2);//magic number
        protocol.setRequestType(buf.readByte());
        protocol.setSerializationType(buf.readByte());
        protocol.setRequestID(buf.readLong());
        protocol.setDataLength(buf.readInt());
        byte[] data = new byte[protocol.getDataLength()];
        buf.readBytes(data);
        protocol.setData(data);
        return protocol;
    }
    private Serialization getSerialization(byte type){
        Serialization serialization = null;
        // try {
        //     serialization = (Serialization)Class.forName("cn.shuaijunlan.serizlization.java.JavaNativeSerialization").newInstance();
        // } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
        //     e.printStackTrace();
        // }
        return JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION;

    }
}
