package com.bw.receiverImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.cache.vo.BwUserSpellVO;

public class BwUserSpellVOReceiverListener implements MessageListener {

    private static Logger logger = Logger.getLogger(BwUserSpellVOReceiverListener.class);
    public IReceiverUtil iReceiverUtil;

    @Override
    public void onMessage(Message arg0) {
        try {
            BwUserSpellVO busvo = (BwUserSpellVO) (((ObjectMessage) arg0).getObject());
            iReceiverUtil.receiveBwUserSpellVO(busvo);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        if (logger.isDebugEnabled()) {
            logger.info("成功接收用户魔法信息");
        }
    }

    public IReceiverUtil getiReceiverUtil() {
        return iReceiverUtil;
    }

    public void setiReceiverUtil(IReceiverUtil iReceiverUtil) {
        this.iReceiverUtil = iReceiverUtil;
    }

}
