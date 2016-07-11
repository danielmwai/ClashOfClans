package com.commonSocket.net.exception;

public class OperationFailedException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public OperationFailedException(String msg) {
        setMessage(msg);
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }
}
