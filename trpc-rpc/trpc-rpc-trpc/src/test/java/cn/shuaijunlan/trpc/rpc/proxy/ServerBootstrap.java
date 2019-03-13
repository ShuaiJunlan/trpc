package cn.shuaijunlan.trpc.rpc.proxy;

import cn.shuaijunlan.trpc.remoting.netty4.NettyServer;
import org.junit.Test;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 3:59 PM 3/5/19.
 */
public class ServerBootstrap {
    @Test
    public void doBind() throws InterruptedException {
        NettyServer nettyServer = new NettyServer();
        nettyServer.doBind(8080);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
