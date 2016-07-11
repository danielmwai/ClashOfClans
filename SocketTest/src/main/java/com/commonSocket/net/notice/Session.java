package com.commonSocket.net.notice;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public abstract interface Session {

    public abstract void write(Object paramObject);

    public abstract IoSession getIoSession();

    public abstract Object getMessage();

    public abstract String getNoticeId();

    public abstract boolean isIdleEvent(IdleStatus paramIdleStatus);
}
