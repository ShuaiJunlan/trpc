package cn.shuaijunlan.trpc.common.threadpool;

import cn.shuaijunlan.trpc.common.properties.ConfigProperties;

import java.util.concurrent.Executor;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:41 AM 3/5/19.
 */
public class ThreadPools {
    public static Executor newLimitedThreadPool(){
        return new LimitedThreadPool().getExecutor(new ConfigProperties());
    }
}
