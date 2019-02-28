package cn.shuaijunlan.trpc.remoting.netty4;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:37 PM 2/28/19.
 */
public class VerifyHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        super.read(ctx);
    }
}
