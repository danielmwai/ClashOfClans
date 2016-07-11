package com.bw.exception;

public class CityCacheDaoException extends RuntimeException {

    private static final long serialVersionUID = 1636065759470999050L;

    public CityCacheDaoException() {
    }

    public CityCacheDaoException(final String msg) {
        super(msg);
    }

    public CityCacheDaoException(final Throwable cause) {
        super(cause);
    }

    public CityCacheDaoException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}
