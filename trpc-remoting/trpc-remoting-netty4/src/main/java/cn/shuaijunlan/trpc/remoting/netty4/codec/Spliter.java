package cn.shuaijunlan.trpc.remoting.netty4.codec;

import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:25 PM 2/28/19.
 */
public class Spliter extends LengthFieldBasedFrameDecoder {
    public Spliter(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in.getShort(in.readerIndex()) != TrpcProtocol.getMagicNumber()){
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
