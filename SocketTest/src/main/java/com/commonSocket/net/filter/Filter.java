package com.commonSocket.net.filter;

import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;
import com.commonSocket.net.notice.Session;

/**
 * @author denny zhao
 *
 */
public abstract interface Filter {

    public abstract void init();

    public abstract void doFilter(Request paramRequest, Response paramResponse, FilterChain paramFilterChain) throws Exception;

    public abstract void doFilter(Session paramSession, FilterChain paramFilterChain) throws Exception;

    public abstract void destroy();
}
