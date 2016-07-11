package com.commonSocket.net.action;

import com.commonSocket.net.IoMode;
import com.google.protobuf.ByteString;
import org.apache.mina.core.session.IoSession;

public abstract interface Request {

    public abstract IoSession getSession();

    public abstract void setSession(IoSession paramIoSession);

    public abstract int getCommandId();

    public abstract void setCommandId(int paramInt);

    public abstract ByteString getMessage();

    public abstract long getSequence();

    public abstract void setSequence(long paramLong);

    public abstract IoMode getIoMode();

    public abstract void setIoMode(IoMode paramIoMode);

    public abstract Object getAttribute(String paramString);

    public abstract void setAttribute(String paramString, Object paramObject);

    public abstract Object removeAttribute(String paramString);
}
