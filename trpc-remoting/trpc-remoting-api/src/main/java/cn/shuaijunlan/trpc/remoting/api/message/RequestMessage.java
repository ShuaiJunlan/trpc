package cn.shuaijunlan.trpc.remoting.api.message;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:54 PM 2/28/19.
 */
public class RequestMessage extends AbstractMessage implements Serializable {
    private static final long serialVersionUID = 10L;
    private Long requestID;
    private Byte requestType;
    private String interfaceName;
    private String methodName;
    private String[] parameterTypes;
    private String[] parameterValues;
    private HashMap<String, String> attachment;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getRequestID() {
        return requestID;
    }

    public void setRequestID(Long requestID) {
        this.requestID = requestID;
    }

    public Byte getRequestType() {
        return requestType;
    }

    public void setRequestType(Byte requestType) {
        this.requestType = requestType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(String[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String[] getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(String[] parameterValues) {
        this.parameterValues = parameterValues;
    }

    public HashMap<String, String> getAttachment() {
        return attachment;
    }

    public void setAttachment(HashMap<String, String> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "requestID=" + requestID +
                ", requestType=" + requestType +
                ", interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", parameterValues=" + Arrays.toString(parameterValues) +
                ", attachment=" + attachment +
                '}';
    }
}
