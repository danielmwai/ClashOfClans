package com.commonSocket.net.action.support;

import com.commonSocket.net.IoMode;
import com.commonSocket.net.action.Request;
import com.google.protobuf.ByteString;
import java.util.HashMap;
import java.util.Map;
import org.apache.mina.core.session.IoSession;

public class DefaultRequest
        implements Request {

    public final String SESSION = "_SESSION";
    public final String MESSAGE = "_MESSAGE";
    public final String COMMANDID = "_COMMANDID";
    public final String SEQUENCE = "_SEQUENCE";
    private IoMode ioMode;
    Map<String, Object> map;

    public DefaultRequest(IoSession session, int commandId, long sequence, Object message, IoMode ioMode) {
        this.map = new HashMap();
        this.ioMode = ioMode;
        this.map.put("_SESSION", session);
        this.map.put("_COMMANDID", Integer.valueOf(commandId));
        this.map.put("_SEQUENCE", Long.valueOf(sequence));
        this.map.put("_MESSAGE", message);
    }

    public IoSession getSession() {
        return (IoSession) this.map.get("_SESSION");
    }

    public void setSession(IoSession session) {
        this.map.put("_SESSION", session);
    }

    public int getCommandId() {
        return Integer.valueOf(String.valueOf(this.map.get("_COMMANDID"))).intValue();
    }

    public void setCommandId(int commandId) {
        this.map.put("_COMMANDID", Integer.valueOf(commandId));
    }

    public ByteString getMessage() {
        return (ByteString) this.map.get("_MESSAGE");
    }

    public IoMode getIoMode() {
        return this.ioMode;
    }

    public void setIoMode(IoMode ioMode) {
        this.ioMode = ioMode;
    }

    public Object getAttribute(String key) {
        return this.map.get(key);
    }

    public void setAttribute(String key, Object value) {
        this.map.put(key, value);
    }

    public Object removeAttribute(String key) {
        return this.map.remove(key);
    }

    public long getSequence() {
        return Long.valueOf(String.valueOf(this.map.get("_SEQUENCE"))).longValue();
    }

    public void setSequence(long sequence) {
        this.map.put("_SEQUENCE", Long.valueOf(sequence));
    }
}
