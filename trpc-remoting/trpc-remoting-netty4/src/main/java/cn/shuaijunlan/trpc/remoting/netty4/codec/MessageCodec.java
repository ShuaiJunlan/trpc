package cn.shuaijunlan.trpc.remoting.netty4.codec;

import cn.shuaijunlan.trpc.remoting.api.Codec;
import cn.shuaijunlan.trpc.remoting.api.protocol.AbstractProtocol;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import cn.shuaijunlan.trpc.serialization.api.Serialization;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:45 PM 2/28/19.
 */
public class MessageCodec implements Codec {
    private static volatile MessageCodec messageCodec = null;

    private MessageCodec(){}

    public static MessageCodec getMessageCodecInstance(){
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
    public ByteBuf encode(AbstractProtocol protocol) {
        //get a bytebuf object
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();


        if (protocol instanceof TrpcProtocol){
            byteBuf.writeShort(TrpcProtocol.getMagicNumber());
            byteBuf.writeByte(((TrpcProtocol) protocol).getRequestType());
            byteBuf.writeByte(((TrpcProtocol) protocol).getSerizlizationType());
            byteBuf.writeLong(((TrpcProtocol) protocol).getRequestID());
            byteBuf.writeInt(((TrpcProtocol) protocol).getDataLength());
            byteBuf.writeBytes(getSerialization(((TrpcProtocol) protocol).getSerizlizationType()).serialize(((TrpcProtocol) protocol).getData()));
        }
        return byteBuf;
    }

    @Override
    public AbstractProtocol decode(ByteBuf buf) {
        TrpcProtocol protocol = new TrpcProtocol();
        protocol.setRequestType(buf.readByte());
        protocol.setSerizlizationType(buf.readByte());
        protocol.setRequestID(buf.readLong());
        protocol.setDataLength(buf.readInt());
        byte[] data = new byte[protocol.getDataLength()];
        buf.readBytes(data);
        protocol.setData(getSerialization(protocol.getSerizlizationType()).deserialize(data));
        return protocol;
    }
    private Serialization getSerialization(byte type){
        return null;
    }
}
