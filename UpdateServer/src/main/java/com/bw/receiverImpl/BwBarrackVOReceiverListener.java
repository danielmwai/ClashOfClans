package com.bw.receiverImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.log4j.Logger;

import com.bw.cache.vo.BwBarrackVO;
import com.bw.cache.vo.BwBattleVO;

public class BwBarrackVOReceiverListener implements MessageListener {

    private static Logger logger = Logger.getLogger(BwBattleVOReceiverListener.class);
    public IReceiverUtil iReceiverUtil;

    @Override
    public void onMessage(Message arg0) {
        BwBarrackVO bbvo;
        try {
            bbvo = (BwBarrackVO) (((ObjectMessage) arg0).getObject());
            iReceiverUtil.receiveBwBarrackVO(bbvo);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        bbvo = null;
        if (logger.isDebugEnabled()) {
            logger.info("成功接收兵营信息");
        }
    }

    public IReceiverUtil getiReceiverUtil() {
        return iReceiverUtil;
    }

    public void setiReceiverUtil(IReceiverUtil iReceiverUtil) {
        this.iReceiverUtil = iReceiverUtil;
    }

}
