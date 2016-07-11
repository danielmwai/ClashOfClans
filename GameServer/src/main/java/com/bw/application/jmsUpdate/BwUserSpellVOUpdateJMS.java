package com.bw.application.jmsUpdate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwUserSpellVO;
import com.bw.cache.vo.BwUserVO;
import com.bw.jms.sender.SenderUtil;

/**
 * @author denny zhao 日期:2012-12-10 更新用户魔法信息
 */
public class BwUserSpellVOUpdateJMS {

    private Logger logger = Logger.getLogger(BwUserSpellVOUpdateJMS.class);
    private Map<String, BwUserSpellVO> jmsdataMap = new ConcurrentHashMap<String, BwUserSpellVO>();
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
            BwUserSpellVO f = jmsdataMap.remove(k);
            if (f != null) {
                senderUtil.sendBwUserSpellVO(f);
                logger.info("发送用户魔法成功");
            }

        }
    }

    public void addBwUserSpellVO(BwUserSpellVO bwUserSpellVO) {
        String key = bwUserSpellVO.getMailaddress() + "_" + bwUserSpellVO.getSpelltypeid();
        jmsdataMap.put(key, bwUserSpellVO);
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

    public Map<String, BwUserSpellVO> getJmsdataMap() {
        return jmsdataMap;
    }

    public void setJmsdataMap(Map<String, BwUserSpellVO> jmsdataMap) {
        this.jmsdataMap = jmsdataMap;
    }

}
