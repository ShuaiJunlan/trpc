package cn.shuaijunlan.trpc.remoting.netty4.client;

import cn.shuaijunlan.trpc.remoting.api.message.ResponseMessage;
import cn.shuaijunlan.trpc.remoting.netty4.NettyClient;
import cn.shuaijunlan.trpc.remoting.netty4.TrpcContext;
import cn.shuaijunlan.trpc.remoting.netty4.TrpcFuture;
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
        LOGGER.debug("NettyClientHandler");
        TrpcFuture trpcFuture;
        if ((trpcFuture = TrpcContext.FUTURE_CONCURRENT_HASH_MAP.remove(msg.getResponseID())) != null){
            trpcFuture.setValue(msg.getReturnValue());
        }else {
            LOGGER.warn("The response id: {} isn't present in TrpcContext.FUTURE_CONCURRENT_HASH_MAP", msg.getResponseID());
        }
    }
}
