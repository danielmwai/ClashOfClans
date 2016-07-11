package com.commonSocket.net.exception;

public class ObjectExistsException extends NestedCheckedException {

    private static final long serialVersionUID = -7354337755120499573L;

    public ObjectExistsException() {
        super("Object ExistsException");
    }
}
