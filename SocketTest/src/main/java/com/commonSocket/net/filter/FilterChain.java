package com.commonSocket.net.filter;

import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;
import com.commonSocket.net.notice.Notice;
import com.commonSocket.net.notice.Session;

public abstract interface FilterChain {

    public abstract void doFilter(Request paramRequest, Response paramResponse) throws Exception;

    public abstract void doFilter(Session paramSession) throws Exception;

    public abstract Action getAction();

    public abstract Notice getNotice();
}
