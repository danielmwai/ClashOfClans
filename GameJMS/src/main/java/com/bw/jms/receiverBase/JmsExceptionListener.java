package com.bw.jms.receiverBase;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.apache.log4j.Logger;

/**
 * @author 赵清有 接收异常类
 *
 */
public class JmsExceptionListener implements ExceptionListener {

    private static final Logger log = Logger.getLogger(JmsExceptionListener.class);

    public void onException(JMSException e) {
        log.error("Jms connection error! ", e);
    }
}
