package com.commonSocket.net.action;

public abstract interface ActionDispatcher {

    public abstract void dispatcher(Request paramRequest, Response paramResponse) throws Exception;
}
