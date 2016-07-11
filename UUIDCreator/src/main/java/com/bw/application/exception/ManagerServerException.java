package com.bw.application.exception;

/**
 * @author dennyZhao
 *
 * 2012-04-23 上午11:30:52
 */
public class ManagerServerException extends RuntimeException {

    private static final long serialVersionUID = 1636065759470999050L;

    public ManagerServerException() {
    }

    /**
     * @param msg
     */
    public ManagerServerException(String msg) {
        super(msg);
    }

    /**
     * @param cause
     */
    public ManagerServerException(Throwable cause) {
        super(cause);
    }

    /**
     * @param msg
     * @param cause
     */
    public ManagerServerException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
