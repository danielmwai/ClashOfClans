package com.commonSocket.net.notice;

public abstract interface NoticeDispatcher {

    public abstract void circular(Session paramSession)
            throws Exception;
}
