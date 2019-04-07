package cn.shuaijunlan.trpc.microbench.rpc;

import cn.shuaijunlan.trpc.rpc.proxy.JdkDynamicProxy;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 4:26 PM 4/7/19.
 */
public class PerformanceBenchmark extends AbstractJavaSamplerClient {
    private Interfaces interfaces = null;
    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sr = new SampleResult();
        sr.sampleStart();
        String re = interfaces.getName("Junlan");

        sr.setResponseData("from provider:" + re, null);
        sr.setDataType(SampleResult.TEXT);
        sr.setSuccessful(true);
        sr.sampleEnd();
        return sr;
    }

    @Override
    public void setupTest(JavaSamplerContext context) {
        interfaces = JdkDynamicProxy.newInstance(Interfaces.class);
    }

    @Override
    public void teardownTest(JavaSamplerContext context) {
        super.teardownTest(context);
    }
}
