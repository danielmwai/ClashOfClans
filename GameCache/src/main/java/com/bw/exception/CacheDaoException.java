package com.bw.exception;

/**
 * @author dennyZhao
 *
 * 2012-04-23 上午11:30:52
 */
public class CacheDaoException extends RuntimeException {

    private static final long serialVersionUID = 1636065759470999050L;

    public CacheDaoException() {
    }

    /**
     * @param msg
     */
    public CacheDaoException(String msg) {
        super(msg);
    }

    /**
     * @param cause
     */
    public CacheDaoException(Throwable cause) {
        super(cause);
    }

    /**
     * @param msg
     * @param cause
     */
    public CacheDaoException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
