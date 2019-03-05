package cn.shuaijunlan.trpc.remoting.netty4.rpc;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 1:01 PM 3/1/19.
 */
public class HelloImpl implements IHello {
    @Override
    public String sayHello(String hello) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello: " + hello;
    }
}
