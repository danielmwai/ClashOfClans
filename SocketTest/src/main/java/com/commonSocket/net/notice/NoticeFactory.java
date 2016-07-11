package com.commonSocket.net.notice;

public abstract interface NoticeFactory {

    public abstract Notice creatNotice(String paramString)
            throws Exception;
}
