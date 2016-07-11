package com.commonSocket.net.util;

public class AntiException extends Exception {

    private static final long serialVersionUID = 5703907642504876932L;

    public AntiException() {
    }

    public AntiException(String message) {
        super(message);
    }

    public AntiException(Throwable cause) {
        super(cause);
    }

    public AntiException(String message, Throwable cause) {
        super(message);
        AntiCrack.fake(this, cause);
    }
}
