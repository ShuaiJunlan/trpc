package cn.shuaijunlan.trpc.remoting.api.message;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 9:54 PM 2/28/19.
 */
public class ResponseMessage extends AbstractMessage implements Serializable {
    private static final long serialVersionUID = 11L;
    private String returnType;
    private String returnValue;
    private HashMap<String, String> attachment;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public HashMap<String, String> getAttachment() {
        return attachment;
    }

    public void setAttachment(HashMap<String, String> attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "returnType='" + returnType + '\'' +
                ", returnValue='" + returnValue + '\'' +
                ", attachment=" + attachment +
                '}';
    }
}
