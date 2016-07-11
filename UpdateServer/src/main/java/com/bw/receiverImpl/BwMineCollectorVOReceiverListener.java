package com.bw.receiverImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwMineCollectorVO;

public class BwMineCollectorVOReceiverListener implements MessageListener {

    private static Logger logger = Logger.getLogger(BwMineCollectorVOReceiverListener.class);
    public IReceiverUtil iReceiverUtil;

    @Override
    public void onMessage(Message arg0) {
        try {
            BwMineCollectorVO buvo = (BwMineCollectorVO) (((ObjectMessage) arg0).getObject());
            iReceiverUtil.receiveBwMineCollectorVO(buvo);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        if (logger.isDebugEnabled()) {
            logger.info("成功接收 金库药水信息");
        }
    }

    public IReceiverUtil getiReceiverUtil() {
        return iReceiverUtil;
    }

    public void setiReceiverUtil(IReceiverUtil iReceiverUtil) {
        this.iReceiverUtil = iReceiverUtil;
    }

}
