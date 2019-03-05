package cn.shuaijunlan.trpc.rpc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:52 PM 3/5/19.
 */
public class TrpcContext {
    public static final AtomicLong REQUEST_ID = new AtomicLong(1);
    public static final ConcurrentHashMap<Long, TrpcFuture> FUTURE_CONCURRENT_HASH_MAP = new ConcurrentHashMap<>(1<<8);
}
