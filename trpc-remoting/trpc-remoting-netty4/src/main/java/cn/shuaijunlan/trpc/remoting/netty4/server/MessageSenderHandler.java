package cn.shuaijunlan.trpc.remoting.netty4.server;

import cn.shuaijunlan.serizlization.java.JavaNativeSerialization;
import cn.shuaijunlan.trpc.remoting.api.message.AbstractMessage;
import cn.shuaijunlan.trpc.remoting.api.message.ResponseMessage;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:48 PM 3/1/19.
 */
public class MessageSenderHandler extends ChannelOutboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderHandler.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        LOGGER.debug("MessageSenderHandler");
        if (msg instanceof ResponseMessage){
            ResponseMessage responseMessage = (ResponseMessage) msg;
            byte[] bytes = JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION.serialize(responseMessage);

            TrpcProtocol trpcProtocol = new TrpcProtocol();
            trpcProtocol.setSerializationType((byte)1);
            trpcProtocol.setRequestID(1);
            trpcProtocol.setRequestType((byte)1);
            trpcProtocol.setDataLength(bytes.length);
            trpcProtocol.setData(bytes);
            // ctx.writeAndFlush(trpcProtocol);
            super.write(ctx, trpcProtocol, promise);
        }else {
            LOGGER.debug("else");
        }
    }
}
