package cn.shuaijunlan.trpc.rpc.proxy;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:01 AM 3/1/19.
 */
public class InterfacesImpl implements Interfaces {
    @Override
    public String getName(String name) {
        return name + ".{Author: Shuai Junlan}";
    }
}
