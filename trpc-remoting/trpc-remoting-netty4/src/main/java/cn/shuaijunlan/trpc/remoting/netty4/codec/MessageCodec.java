package cn.shuaijunlan.trpc.remoting.netty4.codec;

import cn.shuaijunlan.trpc.remoting.api.Codec;
import cn.shuaijunlan.trpc.remoting.api.protocol.AbstractProtocol;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import io.netty.buffer.ByteBuf;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:45 PM 2/28/19.
 */
public class MessageCodec implements Codec {

    @Override
    public ByteBuf encode(AbstractProtocol protocol) {
        if (protocol instanceof TrpcProtocol){

        }
        return null;
    }

    @Override
    public AbstractProtocol decode(ByteBuf buf) {
        return null;
    }
}
