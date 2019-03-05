package cn.shuaijunlan.trpc.rpc.bootstrap;

import cn.shuaijunlan.trpc.common.properties.ConfigProperties;
import cn.shuaijunlan.trpc.remoting.netty4.NettyClient;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:28 PM 3/5/19.
 */
public class TrpcClientBootstrap {
    public static final List<NettyClient> NETTY_CLIENTS = new LinkedList<>();
    private ConfigProperties properties;

    public ConfigProperties getProperties() {
        return properties;
    }

    public void setProperties(ConfigProperties properties) {
        this.properties = properties;
    }

    public void startUp(){
        NETTY_CLIENTS.add(new NettyClient().doConnect(properties.getHost(), properties.getPort()));
    }
    public void shutDown(){

    }
}
