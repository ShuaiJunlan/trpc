package cn.shuaijunlan.trpc.remoting.netty4;

import cn.shuaijunlan.serizlization.java.JavaNativeSerialization;
import cn.shuaijunlan.trpc.remoting.api.message.RequestMessage;
import cn.shuaijunlan.trpc.remoting.api.protocol.TrpcProtocol;
import cn.shuaijunlan.trpc.remoting.netty4.rpc.IHello;
import cn.shuaijunlan.trpc.serialization.api.Serialization;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.*;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 7:00 PM 2/28/19.
 */
public class NettyClientTest {
    private static final AtomicLong ATOMIC_LONG = new AtomicLong(10);
    NettyServer nettyServer = null;
    // @Before
    public void before(){
        nettyServer = new NettyServer();
        nettyServer.doBind(8080);
    }

    @Test
    public void doConnect() throws IOException {
        // TrpcProtocol trpcProtocol = new TrpcProtocol();
        // trpcProtocol.setSerializationType((byte)1);
        // trpcProtocol.setRequestID(1);
        // trpcProtocol.setRequestType((byte)1);
        // trpcProtocol.setData("hello trpc");
        //
        // byte[] data = getSerialization(trpcProtocol.getSerializationType()).serialize(trpcProtocol.getData());
        // trpcProtocol.setDataLength(data.length);
        //
        // ByteBuf byteBuf = Unpooled.buffer(8);
        // byteBuf.writeShort(TrpcProtocol.getMagicNumber());
        // byteBuf.writeByte(trpcProtocol.getRequestType());
        // byteBuf.writeByte(trpcProtocol.getSerializationType());
        // byteBuf.writeLong(trpcProtocol.getRequestID());
        // byteBuf.writeInt(data.length);
        // byteBuf.writeBytes(data);
        //
        // NettyClient nettyClient = new NettyClient();
        // nettyClient.doConnect("127.0.0.1", 8080);
        // try {
        //     nettyClient.getChannel().writeAndFlush(byteBuf).sync();
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }
    @Test
    public void doRPC() throws IOException {
        TrpcProtocol trpcProtocol = new TrpcProtocol();
        trpcProtocol.setSerializationType((byte)1);
        trpcProtocol.setRequestID(1);
        trpcProtocol.setRequestType((byte)1);

        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setRequestID(ATOMIC_LONG.getAndIncrement());
        requestMessage.setRequestType((byte)1);
        requestMessage.setInterfaceName(IHello.class.getName());
        requestMessage.setMethodName("sayHello");
        requestMessage.setParameterTypes(new String[]{"java.lang.String"});
        requestMessage.setParameterValues(new String[]{"Shuai Junlan"});
        requestMessage.setAttachment(new HashMap<>());


        // byte[] data = getSerialization(trpcProtocol.getSerializationType()).serialize(requestMessage);
        // trpcProtocol.setData(data);
        //
        // trpcProtocol.setDataLength(data.length);
        //
        // ByteBuf byteBuf = Unpooled.buffer(8);
        // byteBuf.writeShort(TrpcProtocol.getMagicNumber());
        // byteBuf.writeByte(trpcProtocol.getRequestType());
        // byteBuf.writeByte(trpcProtocol.getSerializationType());
        // byteBuf.writeLong(trpcProtocol.getRequestID());
        // byteBuf.writeInt(data.length);
        // byteBuf.writeBytes(data);

        NettyClient nettyClient = new NettyClient();
        nettyClient.doConnect("127.0.0.1", 8080);
        // try {
        //     for (int i = 0; i < 30000; i++){
                nettyClient.getChannel().writeAndFlush(requestMessage);
            // }
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    // @After
    public void close(){
        nettyServer.shutdownNow();
    }

    private Serialization getSerialization(byte b){
        return JavaNativeSerialization.JAVA_NATIVE_SERIALIZATION;
    }
}