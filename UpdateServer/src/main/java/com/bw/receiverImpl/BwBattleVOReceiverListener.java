package com.bw.receiverImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;
import com.bw.cache.vo.BwBattleVO;

public class BwBattleVOReceiverListener implements MessageListener {

    private static Logger logger = Logger.getLogger(BwBattleVOReceiverListener.class);
    public IReceiverUtil iReceiverUtil;

    @Override
    public void onMessage(Message arg0) {
        BwBattleVO bbvo;
        try {
            bbvo = (BwBattleVO) (((ObjectMessage) arg0).getObject());
            iReceiverUtil.receiveBwBattleVO(bbvo);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        bbvo = null;
        if (logger.isDebugEnabled()) {
            logger.info("成功接收战斗信息");
        }
    }

    public IReceiverUtil getiReceiverUtil() {
        return iReceiverUtil;
    }

    public void setiReceiverUtil(IReceiverUtil iReceiverUtil) {
        this.iReceiverUtil = iReceiverUtil;
    }

}
