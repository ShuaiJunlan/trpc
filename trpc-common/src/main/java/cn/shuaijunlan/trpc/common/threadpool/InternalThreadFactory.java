package cn.shuaijunlan.trpc.common.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:34 AM 3/5/19.
 */
public class InternalThreadFactory implements ThreadFactory {
    private final AtomicInteger atomicInteger = new AtomicInteger(1);
    private String threadName;
    private boolean daemon;
    private String poolName;
    InternalThreadFactory(String poolName, String threadName, boolean daemon){
        this.poolName = poolName;
        this.threadName = threadName;
        this.daemon = daemon;
    }
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread();
        thread.setName(poolName+threadName+"_"+atomicInteger.getAndIncrement());
        thread.setDaemon(this.daemon);
        return thread;
    }
}
