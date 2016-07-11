package com.bw.jms.receiverBase;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.springframework.jms.listener.SimpleMessageListenerContainer;

/**
 * @author 赵清有 接收容器
 *
 */
public class ReceiveContainer extends SimpleMessageListenerContainer {

    private static final Logger log = Logger.getLogger(ReceiveContainer.class);

    @Override
    protected void doInitialize() throws JMSException {
        super.doInitialize();
        log.info("Start Jms ReceiverContainer[" + this.getClass().getName()
                + "] succeed!");
    }

}
