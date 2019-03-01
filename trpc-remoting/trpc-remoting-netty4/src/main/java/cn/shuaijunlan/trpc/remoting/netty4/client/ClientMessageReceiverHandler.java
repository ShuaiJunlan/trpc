package cn.shuaijunlan.trpc.remoting.netty4.client;

import cn.shuaijunlan.serizlization.java.JavaNativeSerialization;
import cn.shuaijunlan.trpc.remoting.api.message.ResponseMessage;
import cn.shuaijunlan.trpc.remoting.api.protocol.AbstractProtocol;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:05 PM 3/1/19.
 */
public class ClientMessageReceiverHandler extends SimpleChannelInboundHandler<AbstractProtocol> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseMessage.class);
    // @Override
    // public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //
    //     super.channelRead(ctx, msg);
    // }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractProtocol msg) throws Exception {
        if (msg instanceof TrpcProtocol){
            TrpcProtocol protocol = (TrpcProtocol) msg;
            ResponseMessage responseMessage = (ResponseMessage) JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION.deserialize(protocol.getData());
            LOGGER.debug("Receiving response message: {}",responseMessage.toString());
            super.channelRead(ctx, responseMessage);
        }else {
            LOGGER.debug("else");
        }
    }
}
