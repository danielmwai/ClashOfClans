package com.bw.application.jmsUpdate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwMineCollectorVO;
import com.bw.cache.vo.BwUserBattleStatisticsVO;
import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.jms.sender.SenderUtil;

/**
 * @author denny zhao 日期:2012-12-10 更新用户金矿药水收集信息
 */
public class BwMineCollectorVOUpdateJMS {

    private Logger logger = Logger.getLogger(BwMineCollectorVOUpdateJMS.class);
    private Map<String, BwMineCollectorVO> jmsdataMap = new ConcurrentHashMap<String, BwMineCollectorVO>();
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
            BwMineCollectorVO f = jmsdataMap.remove(k);
            if (f != null) {
                senderUtil.sendBwMineCollectorVO(f);
                logger.info("发送用户金矿药水收集信息成功");
            }

        }
    }

    public void addBwMineCollectorVO(BwMineCollectorVO bwMineCollectorVO) {
        String key = bwMineCollectorVO.getMailAddress() + "_" + bwMineCollectorVO.getUserbuildingdataid();
        jmsdataMap.put(key, bwMineCollectorVO);
        logger.info("成功放入map");
    }

    ;
	/**
	 *  自动发送用户金矿药水信息到jms服务器
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

    public Map<String, BwMineCollectorVO> getJmsdataMap() {
        return jmsdataMap;
    }

    public void setJmsdataMap(Map<String, BwMineCollectorVO> jmsdataMap) {
        this.jmsdataMap = jmsdataMap;
    }

}
