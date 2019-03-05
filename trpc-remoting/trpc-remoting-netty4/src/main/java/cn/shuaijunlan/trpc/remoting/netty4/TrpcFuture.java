package cn.shuaijunlan.trpc.remoting.netty4;

import cn.shuaijunlan.trpc.common.utils.Assert;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 2:56 PM 3/5/19.
 */
public class TrpcFuture {
    private CompletableFuture<Object> completableFuture;
    public TrpcFuture(){
        completableFuture = new CompletableFuture<>();
    }


    public Object get() throws ExecutionException, InterruptedException {
        return completableFuture.get();
    }
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return completableFuture.get(timeout, unit);
    }
    public TrpcFuture setCompletableImmediately(){
        completableFuture.complete(null);
        return this;
    }
    public TrpcFuture setValue(Object value){
        Assert.notNull(value, "value==null");
        completableFuture.complete(value);
        return this;
    }
}
