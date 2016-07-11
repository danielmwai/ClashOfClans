package com.bw.receiverImpl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwUserMapDataVO;

public class BwUserMapDataVOReceiverListener implements MessageListener {

    private static Logger logger = Logger.getLogger(BwUserMapDataVOReceiverListener.class);
    public IReceiverUtil iReceiverUtil;

    @Override
    public void onMessage(Message arg0) {
        BwUserMapDataVO busvo;
        try {
            busvo = (BwUserMapDataVO) (((ObjectMessage) arg0).getObject());
            iReceiverUtil.receiveBwUserMapDataVO(busvo);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (logger.isDebugEnabled()) {
            logger.info("成功接收用户地图信息");
        }
    }

    public IReceiverUtil getiReceiverUtil() {
        return iReceiverUtil;
    }

    public void setiReceiverUtil(IReceiverUtil iReceiverUtil) {
        this.iReceiverUtil = iReceiverUtil;
    }

}
