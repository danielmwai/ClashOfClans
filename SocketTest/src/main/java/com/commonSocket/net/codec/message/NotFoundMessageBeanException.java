package com.commonSocket.net.codec.message;

import com.commonSocket.net.exception.NestedRuntimeException;

public class NotFoundMessageBeanException extends NestedRuntimeException {

    private static final long serialVersionUID = -5888875484056954462L;

    public NotFoundMessageBeanException() {
        super("Not Found Message Bean Exception!");
    }

    public NotFoundMessageBeanException(Throwable e) {
        super("Not Found Message Bean Exception!", e);
    }

    public NotFoundMessageBeanException(String message, Throwable e) {
        super(message, e);
    }
}
