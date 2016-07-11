package com.bw.application.jmsUpdate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwMineCollectorAllVO;
import com.bw.jms.sender.SenderUtil;

/**
 * @author denny zhao 日期:2012-12-10 更新用户金库和药库收集信息
 */
public class BwMineCollectorAllVOUpdateJMS {

    private Logger logger = Logger.getLogger(BwMineCollectorAllVOUpdateJMS.class);
    private Map<String, BwMineCollectorAllVO> jmsdataMap = new ConcurrentHashMap<String, BwMineCollectorAllVO>();
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
            BwMineCollectorAllVO f = jmsdataMap.remove(k);
            if (f != null) {
                senderUtil.sendBwMineCollectorAllVO(f);
                logger.info("发送用户金库和药库信息成功");
            }

        }
    }

    public void addBwMineCollectorAllVO(BwMineCollectorAllVO bwMineCollectorAllVO) {
        String key = bwMineCollectorAllVO.getMailAddress() + "_" + bwMineCollectorAllVO.getUserbuildingdataid();
        jmsdataMap.put(key, bwMineCollectorAllVO);
        logger.info("成功放入map");
    }

    ;
	/**
	 *  自动发送用户金库和药库信息到jms服务器
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

    public Map<String, BwMineCollectorAllVO> getJmsdataMap() {
        return jmsdataMap;
    }

    public void setJmsdataMap(Map<String, BwMineCollectorAllVO> jmsdataMap) {
        this.jmsdataMap = jmsdataMap;
    }

}
