package cn.shuaijunlan.trpc.common.threadpool;

import cn.shuaijunlan.trpc.common.constant.Constants;
import cn.shuaijunlan.trpc.common.properties.ConfigProperties;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:09 AM 3/5/19.
 */
public class LimitedThreadPool implements ThreadPool {
    private static final AtomicInteger POOL_COUNT = new AtomicInteger(1);
    @Override
    public Executor getExecutor(ConfigProperties properties) {
        String poolName = properties.getParameter(Constants.POOL_NAME_KEY, Constants.DEFAULT_POOL_NAME);
        String threadName = properties.getParameter(Constants.THREAD_NAME_KEY, Constants.DEFAULT_THREAD_NAME);
        int cores = properties.getParameter(Constants.CORE_THREADS_KEY, Constants.DEFAULT_CORE_THREADS);
        int threads = properties.getParameter(Constants.THREADS_KEY, Constants.DEFAULT_THREADS);
        int queues = properties.getParameter(Constants.QUEUES_KEY, Constants.DEFAULT_QUEUES);
        return new ThreadPoolExecutor(cores, threads, Long.MAX_VALUE, TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<>() :
                        (queues < 0 ? new LinkedBlockingQueue<>()
                                : new ArrayBlockingQueue<>(queues)),
                new InternalThreadFactory(poolName+"_"+POOL_COUNT.getAndIncrement(), threadName, true),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
