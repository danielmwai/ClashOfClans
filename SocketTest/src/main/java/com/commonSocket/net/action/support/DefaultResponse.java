package com.commonSocket.net.action.support;

import com.commonSocket.net.action.Response;

import org.apache.mina.core.session.IoSession;

public class DefaultResponse
        implements Response {

    private IoSession ioSession;

    public DefaultResponse(IoSession ioSession) {
        this.ioSession = ioSession;
    }

    public void write(Object message) {
        this.ioSession.write(message);
    }

    public IoSession getIoSession() {
        return ioSession;
    }

    public void setIoSession(IoSession ioSession) {
        this.ioSession = ioSession;
    }

}
