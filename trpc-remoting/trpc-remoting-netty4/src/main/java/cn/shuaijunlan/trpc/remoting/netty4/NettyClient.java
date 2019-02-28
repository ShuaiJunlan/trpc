package cn.shuaijunlan.trpc.remoting.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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

    public void doConnect(String host, int port)  {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(Epoll.isAvailable()? EpollServerSocketChannel.class: NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new NettyClientInitializer());
        try {
            bootstrap.connect(host, port).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    LOGGER.info("Client do connecting successfully");
                }
            }).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
