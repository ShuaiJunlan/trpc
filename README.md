## trpc

[![Build Status](https://travis-ci.com/ShuaiJunlan/trpc.svg?token=99wnxLqpskbRCf2sLyrg&branch=master)](https://travis-ci.com/ShuaiJunlan/trpc) [![license](https://img.shields.io/github/license/ShuaiJunlan/trpc.svg)](https://github.com/ShuaiJunlan/trpc/blob/master/LICENSE)

A tiny rpc framework basing on Netty.

### Features
* Customized rpc protocol named `TrpcPrptocol`
* Basing on reactor threads model, achieving high performance and high throughput
* Supporting sync and async remoting procedure call
* Supporting multi proxy methods including `Java Proxy` and `cglib`
* Supporting multi serialization methods including `Java serialization` `Fastjson` and `Hessian`

### TODO
- [ ] Achieving service registry and discovery module
- [ ] Adding load balance strategy
- [ ] Supporting Java SPI, dynamic loading needed modules
- [ ] Supporting `router strategy` and `cluster fault tolerance`

### Get Started
* **Exporting services**
```java
public interface Interfaces {
    String getName(String name);
}

public class InterfacesImpl implements Interfaces {
    @Override
    public String getName(String name) {
        return name + " Shuai";
    }
}

public class ServerBootstrap {
    @Test
    public void doBind() throws InterruptedException {
        NettyServer nettyServer = new NettyServer();
        nettyServer.doBind(8080);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
```
* **Remote invoking**
```java
public class JdkDynamicProxyTest {
    @Test
    public void testSync() {
        Interfaces interfaces = JdkDynamicProxy.newInstance(Interfaces.class);
        System.out.println(interfaces.getName("Junlan"));
    }
    
    @Test
    public void testAsync() throws InterruptedException {
        Interfaces interfaces = JdkDynamicProxy.newInstance(Interfaces.class);
        System.out.println(interfaces.getName("hello trpc"));
        Thread.sleep(1000);
        //do others...
        Thread.sleep(1000);
        try {
            System.out.println(TrpcContext.getContext().getTrpcFuture().get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
### Trpc Protocol
A self-defined protocol which just support trpc framework, detailed designing as follows:

![](https://github.com/ShuaiJunlan/trpc/blob/master/documents/assert/trpc-protocol.png?raw=true)

* Magic number(two bytes): identifies trpc protocol with value: `0x0012`
* Request type(one byte): three request type, synchronization/asynchronization/one way
* Serialization type(one byte): using which serialization method
* Requested ID(eight bytes): request message id
* Data length(four bytes): the length of  the data
* Data: the binary data

### License
Licensed under `Apache-2.0`. See [LICENSE](https://github.com/ShuaiJunlan/trpc/blob/master/LICENSE) for further details.