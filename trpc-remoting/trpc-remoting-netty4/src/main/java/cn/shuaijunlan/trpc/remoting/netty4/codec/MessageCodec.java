package cn.shuaijunlan.trpc.remoting.netty4.codec;

import cn.shuaijunlan.trpc.remoting.api.Codec;
import cn.shuaijunlan.trpc.remoting.api.protocol.AbstractProtocol;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import cn.shuaijunlan.trpc.serialization.api.Serialization;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

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
    // public ByteBuf encode(AbstractProtocol protocol, ByteBuf buf){
    //     return buf;
    // }

    @Override
    public ByteBuf encode(AbstractProtocol protocol, ByteBuf byteBuf) {

        if (protocol instanceof TrpcProtocol){
            byteBuf.writeShort(TrpcProtocol.getMagicNumber());
            byteBuf.writeByte(((TrpcProtocol) protocol).getRequestType());
            byteBuf.writeByte(((TrpcProtocol) protocol).getSerializationType());
            byteBuf.writeLong(((TrpcProtocol) protocol).getRequestID());
            byteBuf.writeInt(((TrpcProtocol) protocol).getDataLength());
            try {
                byteBuf.writeBytes(getSerialization(((TrpcProtocol) protocol).getSerializationType()).serialize(((TrpcProtocol) protocol).getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteBuf;
    }

    @Override
    public AbstractProtocol decode(ByteBuf buf) {

        System.out.println(buf.readableBytes());

        TrpcProtocol protocol = new TrpcProtocol();
        buf.skipBytes(2);//magic number
        protocol.setRequestType(buf.readByte());
        protocol.setSerializationType(buf.readByte());
        protocol.setRequestID(buf.readLong());
        protocol.setDataLength(buf.readInt());
        byte[] data = new byte[protocol.getDataLength()];
        buf.readBytes(data);
        try {
            protocol.setData(getSerialization(protocol.getSerializationType()).deserialize(data));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return protocol;
    }
    private Serialization getSerialization(byte type){
        Serialization serialization = null;
        try {
            serialization = (Serialization)Class.forName("").newInstance();
        } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return serialization;

    }
}
