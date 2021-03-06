package cn.shuaijunlan.trpc.remoting.netty4;

import cn.shuaijunlan.trpc.common.utils.Assert;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:52 PM 3/5/19.
 */
public class TrpcContext {
    public static final AtomicLong REQUEST_ID = new AtomicLong(1);
    public static final ConcurrentHashMap<Long, TrpcFuture> FUTURE_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>(1<<8);

    private TrpcFuture trpcFuture;
    public TrpcContext(){
    }

    private static final ThreadLocal<TrpcContext> TRPC_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();


    public static TrpcContext getContext(){
        if (TRPC_CONTEXT_THREAD_LOCAL.get() == null){
            TRPC_CONTEXT_THREAD_LOCAL.set(new TrpcContext());
        }
        return TRPC_CONTEXT_THREAD_LOCAL.get();
    }

    public TrpcFuture getTrpcFuture() {
        Assert.notNull(trpcFuture, "trpcFuture==null");
        return trpcFuture;
    }

    public void setTrpcFuture(TrpcFuture trpcFuture) {
        this.trpcFuture = trpcFuture;
    }
}
