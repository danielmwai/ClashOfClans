package com.bw.application.jmsUpdate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwUserMapDataVO;
import com.bw.cache.vo.BwUserSpellVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.jms.sender.SenderUtil;

/**
 * @author denny zhao 日期:2012-12-10 更新用户地图信息
 */
public class BwUserMapDataVOUpdateJMS {

    private Logger logger = Logger.getLogger(BwUserMapDataVOUpdateJMS.class);
    private Map<String, BwUserMapDataVO> jmsdataMap = new ConcurrentHashMap<String, BwUserMapDataVO>();
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
            BwUserMapDataVO f = jmsdataMap.remove(k);
            if (f != null) {
                senderUtil.sendBwUserMapDataVO(f);
                logger.info("发送用户地图成功:mailAddress:" + f.getMailaddress() + " uuid:" + f.getUniquenessbuildid());
            }

        }
    }

    public void addBwUserMapDataVO(BwUserMapDataVO bwUserMapDataVO) {
        String key = bwUserMapDataVO.getMailaddress() + "_" + bwUserMapDataVO.getUniquenessbuildid();
        jmsdataMap.put(key, bwUserMapDataVO);
        logger.info("成功放入map");
    }

    ;
	/**
	 *  自动发送用户地图信息到jms服务器
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

    public Map<String, BwUserMapDataVO> getJmsdataMap() {
        return jmsdataMap;
    }

    public void setJmsdataMap(Map<String, BwUserMapDataVO> jmsdataMap) {
        this.jmsdataMap = jmsdataMap;
    }

}
