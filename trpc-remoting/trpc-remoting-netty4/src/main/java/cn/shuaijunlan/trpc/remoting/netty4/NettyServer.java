package cn.shuaijunlan.trpc.remoting.netty4;

import cn.shuaijunlan.trpc.remoting.netty4.server.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:46 PM 2/28/19.
 */
public class NettyServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);
    private static final int PORT = 8000;
    private EventLoopGroup boss = Epoll.isAvailable() ?
            new EpollEventLoopGroup(1) :
            new NioEventLoopGroup(1);
    private EventLoopGroup worker = Epoll.isAvailable() ?
            new EpollEventLoopGroup(Runtime.getRuntime().availableProcessors()+1) :
            new NioEventLoopGroup(Runtime.getRuntime().availableProcessors()+1);

    /**
     * doBinding
     * @param port
     */
    public void doBind(final int port) {
        final ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .channel(Epoll.isAvailable()? EpollServerSocketChannel.class: NioServerSocketChannel.class)
                // .option(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new NettyServerInitializer());
        try {
            bootstrap.bind(port).addListener(
                    future -> LOGGER.info("Server bootstrap successfully, listening on port: {}", port)
            ).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
