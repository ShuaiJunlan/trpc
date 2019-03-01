package cn.shuaijunlan.trpc.remoting.api.message;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:03 PM 2/28/19.
 */
public class RequestMessageTest {
    @Test
    public void toStringTest(){
        RequestMessage requestMessage = new RequestMessage();
        requestMessage.setInterfaceName(this.getClass().getName());
        requestMessage.setMethodName("test");
        requestMessage.setParameterTypes(new String[]{"1", "2"});
        requestMessage.setParameterValues(new String[]{"1", "2"});
        requestMessage.setAttachment(new HashMap<String, String>());

        System.out.println(requestMessage.toString());
    }

}