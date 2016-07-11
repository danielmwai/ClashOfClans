package com.commonSocket.net.action.support;

import com.commonSocket.net.action.Action;
import com.commonSocket.net.action.Request;
import com.commonSocket.net.action.Response;

public abstract class ActionSupport
        implements Action {

    public abstract String execute(Request paramRequest, Response paramResponse)
            throws Exception;
}
