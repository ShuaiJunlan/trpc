package cn.shuaijunlan.trpc.microbench.remoting;

import cn.shuaijunlan.trpc.remoting.netty4.NettyServer;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:22 AM 3/5/19.
 */
public class NettyServerBenchMark {
    public static void main(String[] args) throws InterruptedException {
        NettyServer nettyServer = new NettyServer();
        nettyServer.doBind(8080);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
