package cn.shuaijunlan.trpc.remoting.netty4.codec;

import cn.shuaijunlan.trpc.remoting.api.protocol.AbstractProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 5:57 PM 2/28/19.
 */
public class MessageEncoder extends MessageToByteEncoder<AbstractProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractProtocol msg, ByteBuf out) throws Exception {
        MessageCodec.getMessageCodecInstance().encode(msg, out);
    }
}
