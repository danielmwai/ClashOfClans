package com.bw.receiverContainer;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

import com.bw.jms.receiverBase.ReceiveContainer;

/**
 * @author denny zhao 日期:2012-12-10
 */
public class BwUserMapDataVOContainer extends SimpleMessageListenerContainer {

    private static final Logger log = Logger.getLogger(ReceiveContainer.class);

    @Override
    protected void doInitialize() throws JMSException {
        super.doInitialize();
        log.info("Start Jms ReceiverContainer[" + this.getClass().getName()
                + "] succeed!");
    }
}
