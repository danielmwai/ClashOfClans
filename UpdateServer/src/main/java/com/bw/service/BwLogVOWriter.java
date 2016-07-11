package com.bw.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwLogVO;
import com.bw.dao.BwLogDAO;

/**
 * @author zhYou
 */
public class BwLogVOWriter {

    private static final Logger log = Logger.getLogger(BwLogVOWriter.class);
    private BwLogDAO bwLogDaoImpl;
    public ArrayBlockingQueue<Collection<BwLogVO>> clq = new ArrayBlockingQueue<Collection<BwLogVO>>(100000);
    int beishu = 3; // 批量更新的数量
    private int periodSecond = 10; // 线程间隔时间
    private int startTime = 10000; // 开始时间
    private int perRunUpdateCount = 1000; // 间隔线程每次更新的数量

    public void batchUpdateUserInfor() {
        try {
            log.info("insert action log start");
            long updateCount = 0;
            while (!clq.isEmpty() && updateCount < perRunUpdateCount) {
                Collection<BwLogVO> logs = clq.poll();
                if (logs.size() > 0) {
                    List<BwLogVO> insertLogs = new ArrayList<BwLogVO>();
                    for (BwLogVO vo : logs) {
                        if (insertLogs.size() < beishu) {
                            insertLogs.add(vo);
                            updateCount++;
                        } else {
                            bwLogDaoImpl.batchInsert(insertLogs);
                            insertLogs.clear();
                        }
                    }
                    if (!insertLogs.isEmpty()) {
                        bwLogDaoImpl.batchInsert(insertLogs);
                        insertLogs.clear();
                    }
                }
            }
        } catch (Exception e) {
            log.error("insert action log error", e);
        }
    }

    public void init() {
        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
        es.scheduleAtFixedRate(new Runnable() {
            public void run() {
                batchUpdateUserInfor();
            }
        }, startTime, periodSecond, TimeUnit.MILLISECONDS);
    }

    public int getBeishu() {
        return beishu;
    }

    public void setBeishu(int beishu) {
        this.beishu = beishu;
    }

    public ArrayBlockingQueue<Collection<BwLogVO>> getClq() {
        return clq;
    }

    public void setClq(ArrayBlockingQueue<Collection<BwLogVO>> clq) {
        this.clq = clq;
    }

    public int getPeriodSecond() {
        return periodSecond;
    }

    public void setPeriodSecond(int periodSecond) {
        this.periodSecond = periodSecond;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getPerRunUpdateCount() {
        return perRunUpdateCount;
    }

    public void setPerRunUpdateCount(int perRunUpdateCount) {
        this.perRunUpdateCount = perRunUpdateCount;
    }

    public BwLogDAO getBwLogDaoImpl() {
        return bwLogDaoImpl;
    }

    public void setBwLogDaoImpl(BwLogDAO bwLogDaoImpl) {
        this.bwLogDaoImpl = bwLogDaoImpl;
    }
}
