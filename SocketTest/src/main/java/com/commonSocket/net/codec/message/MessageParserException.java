package com.commonSocket.net.codec.message;

import com.commonSocket.net.exception.NestedCheckedException;

public class MessageParserException extends NestedCheckedException {

    private static final long serialVersionUID = 676093938724698388L;

    public MessageParserException() {
        super("Message Parser Exception!");
    }

    public MessageParserException(Throwable e) {
        super("Message Parser Exception!", e);
    }

    public MessageParserException(String message, Throwable e) {
        super("Message Parser Exception!", e);
    }
}
