package com.commonSocket.net.notice.support;

import com.commonSocket.net.notice.Session;
import com.google.protobuf.ByteString;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class DefaultSession
        implements Session {

    private IoSession ioSession;
    private IdleStatus idleStatus;
    private ByteString message;
    private final String noticeId = "_notice";

    public DefaultSession(IoSession ioSession, IdleStatus idleStatus) {
        this.ioSession = ioSession;
        this.idleStatus = idleStatus;
    }

    public void write(Object message) {
        this.ioSession.write(message);
    }

    public IoSession getIoSession() {
        return this.ioSession;
    }

    public Object getMessage() {
        return this.message;
    }

    public String getNoticeId() {
        return "_notice";
    }

    public boolean isIdleEvent(IdleStatus idleStatus) {
        return this.idleStatus.equals(idleStatus);
    }
}
