package cn.shuaijunlan.trpc.remoting.netty4;

import cn.shuaijunlan.trpc.remoting.netty4.client.NettyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:46 PM 2/28/19.
 */
public class NettyClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClient.class);
    private EventLoopGroup worker = Epoll.isAvailable() ?
            new EpollEventLoopGroup(Runtime.getRuntime().availableProcessors()) :
            new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

    private Channel channel;

    public void doConnect(String host, int port)  {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(Epoll.isAvailable()? EpollSocketChannel.class: NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new NettyClientInitializer());
        try {
            channel = bootstrap.connect(host, port).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    LOGGER.info("Client do connecting successfully");
                }
            }).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
            worker.shutdownGracefully();
        }
    }

    public Channel getChannel() {
        return channel;
    }
}
