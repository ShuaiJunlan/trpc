package cn.shuaijunlan.trpc.remoting.api.message;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:54 PM 2/28/19.
 */
public class ResponseMessage extends AbstractMessage implements Serializable {
    private static final long serialVersionUID = 11L;
    private String className;
    private String methodName;
    private String[] parameterTypes;
    private String[] parameterValues;
    private HashMap<String, String> attachment;
}
