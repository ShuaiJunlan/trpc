package cn.shuaijunlan.trpc.remoting.api;

import cn.shuaijunlan.trpc.remoting.api.protocol.AbstractProtocol;
import io.netty.buffer.ByteBuf;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:18 PM 2/28/19.
 */
public interface Codec {
    /**
     * encode
     * @param protocol
     * @return
     */
    ByteBuf encode(AbstractProtocol protocol);

    /**
     * decode
     * @param buf
     * @return
     */
    AbstractProtocol decode(ByteBuf buf);
}
