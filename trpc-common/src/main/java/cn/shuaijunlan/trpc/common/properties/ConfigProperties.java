package cn.shuaijunlan.trpc.common.properties;

import cn.shuaijunlan.trpc.common.constant.Constants;
import cn.shuaijunlan.trpc.common.utils.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:02 AM 3/5/19.
 */
public final class ConfigProperties implements Serializable {
    private static final long serialVersionUID = 122L;

    private  String protocol;

    private  String host;

    private  int port;

    private  Map<String, String> parameters;

    private volatile transient Map<String, Number> numbers;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public String getParameter(String key, String defaultValue) {
        String value = getParameter(key);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }
    public String getParameter(String key) {
        String value = parameters.get(key);
        if (StringUtils.isEmpty(value)) {
            value = parameters.get(key);
        }
        return value;
    }

    public long getParameter(String key, long defaultValue) {
        String value = getParameter(key);
        if (value == null) {
            return defaultValue;
        }
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return Long.parseLong(value);
    }

    public int getParameter(String key, int defaultValue) {
        String value = getParameter(key);
        if (value == null) {
            return defaultValue;
        }
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return "ConfigProperties{" +
                "protocol='" + protocol + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", parameters=" + parameters +
                '}';
    }
}
