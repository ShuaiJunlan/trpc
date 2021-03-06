package cn.shuaijunlan.trpc.remoting.netty4.server;

import cn.shuaijunlan.trpc.remoting.netty4.codec.MessageDecoder;
import cn.shuaijunlan.trpc.remoting.netty4.codec.MessageEncoder;
import cn.shuaijunlan.trpc.remoting.netty4.codec.Spliter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:01 PM 2/28/19.
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new Spliter(Integer.MAX_VALUE, 12, 4));
        ch.pipeline().addLast(new MessageEncoder());
        ch.pipeline().addLast(new MessageDecoder());
        ch.pipeline().addLast(new MessageSenderHandler());
        ch.pipeline().addLast(new NettyReceiverHandler());
    }
}
