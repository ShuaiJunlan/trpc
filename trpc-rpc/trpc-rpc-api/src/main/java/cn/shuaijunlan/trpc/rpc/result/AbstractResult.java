package cn.shuaijunlan.trpc.rpc.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 1:18 PM 3/5/19.
 */
public abstract class AbstractResult implements Serializable {
    private Map<String, String> attachments = new HashMap<String, String>();

    private Object result;

    private Throwable exception;

    public Map<String, String> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, String> attachments) {
        this.attachments = attachments;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
