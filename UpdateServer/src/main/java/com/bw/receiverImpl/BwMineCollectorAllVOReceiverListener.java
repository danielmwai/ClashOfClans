package com.bw.receiverImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwMineCollectorAllVO;

public class BwMineCollectorAllVOReceiverListener implements MessageListener {

    private static Logger logger = Logger.getLogger(BwMineCollectorAllVOReceiverListener.class);
    public IReceiverUtil iReceiverUtil;

    @Override
    public void onMessage(Message arg0) {
        try {
            BwMineCollectorAllVO buvo = (BwMineCollectorAllVO) (((ObjectMessage) arg0).getObject());;
            iReceiverUtil.receiveBwMineCollectorAllVO(buvo);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        if (logger.isDebugEnabled()) {
            logger.info("成功接收金库药库信息");
        }
    }

    public IReceiverUtil getiReceiverUtil() {
        return iReceiverUtil;
    }

    public void setiReceiverUtil(IReceiverUtil iReceiverUtil) {
        this.iReceiverUtil = iReceiverUtil;
    }

}
