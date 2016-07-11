package com.commonSocket.net.exception;

public class InitializeException extends NestedRuntimeException {

    private static final long serialVersionUID = 1L;

    public InitializeException() {
        super("Initialize Exception");
    }

    public InitializeException(Throwable e) {
        super("Initialize Exception", e);
    }

    public InitializeException(String message, Throwable e) {
        super(message, e);
    }
}
