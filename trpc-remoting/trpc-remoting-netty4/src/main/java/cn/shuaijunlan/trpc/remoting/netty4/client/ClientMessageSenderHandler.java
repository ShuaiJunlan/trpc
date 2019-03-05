package cn.shuaijunlan.trpc.remoting.netty4.client;

import cn.shuaijunlan.serizlization.java.JavaNativeSerialization;
import cn.shuaijunlan.trpc.remoting.api.message.RequestMessage;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:13 PM 3/1/19.
 */
public class ClientMessageSenderHandler extends ChannelOutboundHandlerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientMessageSenderHandler.class);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof RequestMessage){
            RequestMessage requestMessage = (RequestMessage)msg;
            LOGGER.debug("ClientMessageSenderHandler");
            TrpcProtocol trpcProtocol = new TrpcProtocol();
            trpcProtocol.setSerializationType((byte)1);
            trpcProtocol.setRequestID(requestMessage.getRequestID());
            trpcProtocol.setRequestType(requestMessage.getRequestType());
            byte[] data = JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION.serialize(msg);
            trpcProtocol.setData(data);
            trpcProtocol.setDataLength(data.length);
            super.write(ctx, trpcProtocol, promise);
        }else {
            LOGGER.debug("else");
        }
    }
}
