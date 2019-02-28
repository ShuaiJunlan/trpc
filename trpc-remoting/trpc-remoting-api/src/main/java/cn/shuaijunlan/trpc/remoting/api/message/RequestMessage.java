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
    private String className;
    private String methodName;
    private String[] parameterTypes;
    private String[] parameterValues;
    private HashMap<String, String> attachment;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String[] getparameterTypes() {
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
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters=" + Arrays.toString(parameterTypes) +
                ", parameterValues=" + Arrays.toString(parameterValues) +
                ", attachment=" + attachment +
                '}';
    }
}
