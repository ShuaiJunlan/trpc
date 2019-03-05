package cn.shuaijunlan.trpc.remoting.netty4.client;

import cn.shuaijunlan.trpc.remoting.api.message.ResponseMessage;
import cn.shuaijunlan.trpc.remoting.netty4.NettyClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:56 PM 2/28/19.
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<ResponseMessage> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClientHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ResponseMessage msg) throws Exception {
        NettyClient.RESULTS.get(msg.getResponseID()).complete(msg.getReturnValue());
        LOGGER.debug("NettyClientHandler");
    }
}
