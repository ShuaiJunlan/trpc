package cn.shuaijunlan.trpc.common.threadpool;

import cn.shuaijunlan.trpc.common.properties.ConfigProperties;

import java.util.concurrent.Executor;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:07 AM 3/5/19.
 */
public interface ThreadPool {
    /**
     * creating thread pool
     * @param properties properties
     * @return {@link Executor}
     */
    Executor getExecutor(ConfigProperties properties);
}
