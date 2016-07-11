package com.commonSocket.net.filter.support;

import com.commonSocket.net.util.NodeUtil;
import com.google.protobuf.ByteString;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CommandNotify
        implements Serializable {

    private static final long serialVersionUID = 6604076090949224076L;
    private String nodeId;
    private String appId;
    private String sessionId;
    private String userId;
    private String mobile;
    private String ua;
    private String version;
    private String refId;
    private String releaseFlag;
    private String fid;
    private String channelKey;
    private String province;
    private String city;
    private ByteString message;

    public CommandNotify() {
        this.nodeId = NodeUtil.getLocalNodeId();
    }

    public String getNodeId() {
        return this.nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUa() {
        return this.ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public ByteString getMessage() {
        return this.message;
    }

    public void setMessage(ByteString message) {
        this.message = message;
    }

    public String getRefId() {
        return this.refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getReleaseFlag() {
        return this.releaseFlag;
    }

    public void setReleaseFlag(String releaseFlag) {
        this.releaseFlag = releaseFlag;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getChannelKey() {
        return this.channelKey;
    }

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Field[] getFields(Class<?> obj) {
        if (obj.getName().equals("java.lang.Object")) {
            return obj.getDeclaredFields();
        }
        Field[] superField = getFields(obj.getSuperclass());
        Field[] currField = obj.getDeclaredFields();
        Field[] field = new Field[superField.length + currField.length];
        System.arraycopy(superField, 0, field, 0, superField.length);
        System.arraycopy(currField, 0, field, superField.length, currField.length);
        return field;
    }

    public String toString() {
        Field[] fields = getFields(getClass());
        StringBuffer buffer = new StringBuffer(getClass().getSimpleName());
        buffer.append("[ ");
        for (int i = 0; i < fields.length; i++) {
            buffer.append(fields[i].getName()).append("=");
            try {
                String propertyName = fields[i].getName();
                PropertyDescriptor prop = new PropertyDescriptor(propertyName, getClass());
                Object result = prop.getReadMethod().invoke(this, new Object[0]);
                buffer.append(result);
            } catch (Exception localException) {
            }
            buffer.append(" ");
        }

        buffer.append("]");
        return buffer.toString();
    }
}
