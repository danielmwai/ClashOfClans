package com.bw.receiverImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwUserBattleStatisticsVO;
import com.bw.cache.vo.BwUserCharacterVO;

public class BwUserCharacterVOReceiverListener implements MessageListener {

    private static Logger logger = Logger.getLogger(BwUserCharacterVOReceiverListener.class);
    public IReceiverUtil iReceiverUtil;

    @Override
    public void onMessage(Message arg0) {
        try {
            BwUserCharacterVO busvo = (BwUserCharacterVO) (((ObjectMessage) arg0).getObject());
            iReceiverUtil.receiveBwUserCharacterVO(busvo);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        if (logger.isDebugEnabled()) {
            logger.info("成功接收用户兵力信息");
        }
    }

    public IReceiverUtil getiReceiverUtil() {
        return iReceiverUtil;
    }

    public void setiReceiverUtil(IReceiverUtil iReceiverUtil) {
        this.iReceiverUtil = iReceiverUtil;
    }

}
