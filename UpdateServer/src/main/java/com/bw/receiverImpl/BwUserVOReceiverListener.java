package com.bw.receiverImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwUserVO;

/**
 * @author denny zhao 日期:2012-12-10
 */
public class BwUserVOReceiverListener implements MessageListener {

    private static Logger logger = Logger.getLogger(BwUserVOReceiverListener.class);
    public IReceiverUtil iReceiverUtil;

    @Override
    public void onMessage(Message arg0) {
        try {
            BwUserVO busvo = (BwUserVO) (((ObjectMessage) arg0).getObject());
            iReceiverUtil.receiveBwUserVO(busvo);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (logger.isDebugEnabled()) {
            logger.info("成功接收用户信息");
        }
    }

    public IReceiverUtil getiReceiverUtil() {
        return iReceiverUtil;
    }

    public void setiReceiverUtil(IReceiverUtil iReceiverUtil) {
        this.iReceiverUtil = iReceiverUtil;
    }

}
