package cn.shuaijunlan.trpc.remoting.netty4;

import cn.shuaijunlan.trpc.remoting.api.message.AbstractMessage;
import cn.shuaijunlan.trpc.remoting.netty4.client.NettyClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:46 PM 2/28/19.
 */
public class NettyClient {
    // public static final ConcurrentHashMap<Long, CompletableFuture<Object>> RESULTS = new ConcurrentHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClient.class);
    private EventLoopGroup worker = Epoll.isAvailable() ?
            new EpollEventLoopGroup(Runtime.getRuntime().availableProcessors()) :
            new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());

    private Channel channel;

    public NettyClient doConnect(String host, int port)  {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(Epoll.isAvailable()? EpollSocketChannel.class: NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new NettyClientInitializer());
        try {
            channel = bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()){
                        LOGGER.info("Client do connecting successfully");
                    }else {
                        LOGGER.error(future.cause().getMessage());
                    }
                }
            }).sync().channel();
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
            worker.shutdownGracefully();
        }
        return this;
    }

    public void doWrite(AbstractMessage message){
        channel.writeAndFlush(message);
    }
    public void close(){
        channel.close();
        worker.shutdownGracefully();
    }

    public Channel getChannel() {
        return channel;
    }
}
