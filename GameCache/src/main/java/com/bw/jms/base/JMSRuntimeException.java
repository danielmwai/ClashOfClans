package com.bw.jms.base;

/**
 *
 * @creator:赵清有 2010-8-19下午02:35:07 运行异常类
 */
public class JMSRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -6226548084010462282L;

    public JMSRuntimeException() {
        super();
    }

    public JMSRuntimeException(String msg) {
        super(msg);
    }

    public JMSRuntimeException(Throwable cause) {
        super(cause);
    }

    public JMSRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
