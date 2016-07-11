package com.bw.jms.sender;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.jms.base.JmsSender;

/**
 * @author denny zhao 日期:2012-12-10 用户兵力存储表 发送者
 */
public class BwUserBattleStatisticsVOSender implements JmsSender {

    private long timeOut;

    private JmsTemplate jmsTemplate;

    private String useQueueName;

    public void send(final Object obj) {

        jmsTemplate.send(useQueueName, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {

                ObjectMessage message = session.createObjectMessage();

                message.setObject((BwUserCharacterVO) obj);

                message.setJMSExpiration(timeOut);

                return message;
            }
        });

    }

    @Override
    public void sendJmsMessage(Object obj) {
        send(obj);
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public String getUseQueueName() {
        return useQueueName;
    }

    public void setUseQueueName(String useQueueName) {
        this.useQueueName = useQueueName;
    }

}
