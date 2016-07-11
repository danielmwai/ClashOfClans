package com.commonSocket.net.notice;

public abstract interface Notice {

    public abstract void readerIdleEvent(Session paramSession)
            throws Exception;

    public abstract void bothIdleEvent(Session paramSession)
            throws Exception;

    public abstract void writerIdleEvent(Session paramSession)
            throws Exception;
}
