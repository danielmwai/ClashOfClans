package com.bw.jms.sender;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.bw.jms.base.JmsSender;

/**
 * @author denny zhao 日期:2012-12-10
 */
public class SendCurrency implements JmsSender {

    private long timeOut;

    private JmsTemplate jmsTemplate;

    private String userCurrencyQueue;

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setUserCurrencyQueue(String userCurrencyQueue) {
        this.userCurrencyQueue = userCurrencyQueue;
    }

    public void send(final Object obj) {

        jmsTemplate.send(userCurrencyQueue, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {

                ObjectMessage message = session.createObjectMessage();

                //message.setObject((CGUserCurrencyVO) obj);
                message.setJMSExpiration(timeOut);

                return message;
            }
        });

    }

    @Override
    public void sendJmsMessage(Object obj) {
        send(obj);

    }

}
