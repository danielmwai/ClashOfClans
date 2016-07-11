package com.bw.application.jmsUpdate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwUserVO;
import com.bw.jms.sender.SenderUtil;

/**
 * @author denny zhao 日期:2012-12-10 更新用户信息
 */
public class BwUserVOUpdateJMS {

    private Logger logger = Logger.getLogger(BwUserVOUpdateJMS.class);
    private Map<String, BwUserVO> jmsdataMap = new ConcurrentHashMap<String, BwUserVO>();
    public SenderUtil senderUtil;
    private long period;

    public SenderUtil getSenderUtil() {
        return senderUtil;
    }

    public void setSenderUtil(SenderUtil senderUtil) {
        this.senderUtil = senderUtil;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    private void doSend() {
        for (String k : jmsdataMap.keySet()) {
            BwUserVO f = jmsdataMap.remove(k);
            if (f != null) {
                senderUtil.sendUserVO(f);
                logger.info("发送成功");
            }

        }
    }

    public void addBwUserVO(BwUserVO bwUserVO) {
        String key = bwUserVO.getMailaddress() + "_";
        jmsdataMap.put(key, bwUserVO);
        logger.info("成功放入map");
    }

    ;
	/**
	 *  自动发送用户信息到jms服务器
	 */
	public void init() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                doSend();
            }
        }, 10, period, TimeUnit.SECONDS);
    }

    public Map<String, BwUserVO> getJmsdataMap() {
        return jmsdataMap;
    }

    public void setJmsdataMap(Map<String, BwUserVO> jmsdataMap) {
        this.jmsdataMap = jmsdataMap;
    }

}
