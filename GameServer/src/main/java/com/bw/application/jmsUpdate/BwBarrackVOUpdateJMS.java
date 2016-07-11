package com.bw.application.jmsUpdate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwBarrackVO;
import com.bw.jms.sender.SenderUtil;

/**
 * @author denny zhao 日期:2012-12-10 更新用户兵营信息
 */
public class BwBarrackVOUpdateJMS {

    private Logger logger = Logger.getLogger(BwBarrackVOUpdateJMS.class);
    private Map<String, BwBarrackVO> jmsdataMap = new ConcurrentHashMap<String, BwBarrackVO>();
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
            BwBarrackVO f = jmsdataMap.remove(k);
            if (f != null) {
                senderUtil.sendBwBarrackVO(f);
                logger.info("发送用户兵营信息成功");
            }

        }
    }

    public void addBwBarrackVO(BwBarrackVO bwMineCollectorAllVO) {
        String key = bwMineCollectorAllVO.getMailAddress() + "_" + bwMineCollectorAllVO.getUsermapdataid() + "_" + bwMineCollectorAllVO.getUsercharacterid();
        jmsdataMap.put(key, bwMineCollectorAllVO);
        logger.info("成功放入map");
    }

    ;
	/**
	 *  自动发送用户战斗信息信息到jms服务器
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

    public Map<String, BwBarrackVO> getJmsdataMap() {
        return jmsdataMap;
    }

    public void setJmsdataMap(Map<String, BwBarrackVO> jmsdataMap) {
        this.jmsdataMap = jmsdataMap;
    }

}
