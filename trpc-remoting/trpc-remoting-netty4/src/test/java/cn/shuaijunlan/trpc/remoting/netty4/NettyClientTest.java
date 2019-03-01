package cn.shuaijunlan.trpc.remoting.netty4;

import cn.shuaijunlan.serizlization.java.JavaNativeSerialization;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import cn.shuaijunlan.trpc.serialization.api.Serialization;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:00 PM 2/28/19.
 */
public class NettyClientTest {

    @Test
    public void doConnect() throws IOException {
        TrpcProtocol trpcProtocol = new TrpcProtocol();
        trpcProtocol.setSerializationType((byte)1);
        trpcProtocol.setRequestID(1);
        trpcProtocol.setRequestType((byte)1);
        trpcProtocol.setData("hello trpc");
        byte[] data = getSerialization(trpcProtocol.getSerializationType()).serialize(trpcProtocol.getData());
        trpcProtocol.setDataLength(data.length);

        ByteBuf byteBuf = Unpooled.buffer(8);
        byteBuf.writeShort(TrpcProtocol.getMagicNumber());
        byteBuf.writeByte(trpcProtocol.getRequestType());
        byteBuf.writeByte(trpcProtocol.getSerializationType());
        byteBuf.writeLong(trpcProtocol.getRequestID());
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);

        NettyClient nettyClient = new NettyClient();
        nettyClient.doConnect("127.0.0.1", 8080);
        try {
            nettyClient.getChannel().writeAndFlush(byteBuf).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Serialization getSerialization(byte b){
        return JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION;
    }
}