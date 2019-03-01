package cn.shuaijunlan.trpc.remoting.netty4.server;

import cn.shuaijunlan.trpc.remoting.api.message.RequestMessage;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 6:56 PM 2/28/19.
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerHandler.class);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof TrpcProtocol){
            LOGGER.debug(msg.toString());
            TrpcProtocol protocol = (TrpcProtocol) msg;
            RequestMessage requestMessage = (RequestMessage) protocol.getData();
            requestMessage.getInterfaceName();
        }
        super.channelRead(ctx, msg);
    }
}
