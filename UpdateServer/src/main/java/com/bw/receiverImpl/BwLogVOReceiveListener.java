package com.bw.receiverImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwLogVO;

public class BwLogVOReceiveListener implements MessageListener {

    private static final Logger logger = Logger.getLogger(BwLogVOReceiveListener.class);

    public IReceiverUtil receiverUtil;

    @Override
    public void onMessage(Message message) {
        if (message != null && message instanceof ObjectMessage) {
            ObjectMessage objMsg = (ObjectMessage) message;
            try {
                if (objMsg.getObject() != null && objMsg.getObject() instanceof BwLogVO) {
                    receiverUtil.receiveBwLogVO((BwLogVO) objMsg.getObject());
                }
            } catch (JMSException e) {
                logger.error(e);
            }
        }
    }

    public IReceiverUtil getReceiverUtil() {
        return receiverUtil;
    }

    public void setReceiverUtil(IReceiverUtil receiverUtil) {
        this.receiverUtil = receiverUtil;
    }
}
