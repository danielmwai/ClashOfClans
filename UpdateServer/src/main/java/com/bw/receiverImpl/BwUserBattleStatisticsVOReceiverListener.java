package com.bw.receiverImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwUserBattleStatisticsVO;

public class BwUserBattleStatisticsVOReceiverListener implements MessageListener {

    private static Logger logger = Logger.getLogger(BwUserBattleStatisticsVOReceiverListener.class);
    public IReceiverUtil iReceiverUtil;

    @Override
    public void onMessage(Message arg0) {
        try {
            BwUserBattleStatisticsVO busvo = (BwUserBattleStatisticsVO) (((ObjectMessage) arg0).getObject());
            iReceiverUtil.receiveBwUserBattleStatisticsVO(busvo);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        if (logger.isDebugEnabled()) {
            logger.info("成功接收战斗统计信息");
        }
    }

    public IReceiverUtil getiReceiverUtil() {
        return iReceiverUtil;
    }

    public void setiReceiverUtil(IReceiverUtil iReceiverUtil) {
        this.iReceiverUtil = iReceiverUtil;
    }

}
