package cn.shuaijunlan.trpc.remoting.netty4.codec;

import cn.shuaijunlan.trpc.remoting.api.Codec;
import cn.shuaijunlan.trpc.remoting.api.protocol.AbstractProtocol;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:45 PM 2/28/19.
 */
public class MessageCodec implements Codec {

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
        }
        return byteBuf;
    }

    @Override
    public AbstractProtocol decode(ByteBuf buf) {
        return null;
    }
}
