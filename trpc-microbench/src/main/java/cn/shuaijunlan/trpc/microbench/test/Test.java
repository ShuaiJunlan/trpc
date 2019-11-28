package cn.shuaijunlan.trpc.microbench.test;

import cn.shuaijunlan.trpc.microbench.rpc.Interfaces;
import cn.shuaijunlan.trpc.rpc.proxy.JdkDynamicProxy;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:28 AM 4/9/19.
 */
public class Test {
    public static void main(String[] args) {
        Interfaces interfaces = JdkDynamicProxy.newInstance(Interfaces.class);
        System.out.println(interfaces.getName("Junlan"));
    }
}
