package com.bw.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwBarrackVO;
import com.bw.cache.vo.BwBattleVO;
import com.bw.dao.BwBarrackDAO;
import com.bw.dao.BwBattleDAO;

/**
 * @author denny zhao 日期:2012-12-10 更新兵营信息
 */
public class BwBarrackVOWriter {

    private Logger log = Logger.getLogger(BwBarrackVOWriter.class);
    private BwBarrackDAO bwBarrackDaoImpl;
    public ArrayBlockingQueue<Collection<BwBarrackVO>> clq = new ArrayBlockingQueue<Collection<BwBarrackVO>>(100000);
    //批量更新的数量
    int beishu = 3;
    //线程间隔时间
    private int periodSecond = 10;
    //开始时间
    private int startTime = 10000;
    //间隔线程每次更新的数量
    private int perRunUpdateCount = 1000;

    public void batchUpdateUserInfor() {
        try {
            log.info("开始批量更新数据库兵营信息....");
            long updateCount = 0;
            while (!clq.isEmpty() && updateCount < perRunUpdateCount) {
                Collection<BwBarrackVO> c = clq.poll();
                long allcount = c.size();
                if (allcount > 0) {
                    //
                    List<BwBarrackVO> listtime1 = new ArrayList<BwBarrackVO>();
                    for (BwBarrackVO info : c) {
                        listtime1.add(info);
                        updateCount++;
                        if (listtime1.size() >= beishu) {
                            bwBarrackDaoImpl.batchUpdate(listtime1);
                            listtime1.clear();
                        }

                    }
                    if (!listtime1.isEmpty()) {
                        bwBarrackDaoImpl.batchUpdate(listtime1);
                        listtime1.clear();
                        listtime1 = null;
                    }
                }
                c.clear();
                c = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台批量更新兵营信息发生错误:", e);
        }
    }

    public void init() {
//		threadPoolExecutor = 
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

    public BwBarrackDAO getBwBarrackDaoImpl() {
        return bwBarrackDaoImpl;
    }

    public void setBwBarrackDaoImpl(BwBarrackDAO bwBarrackDaoImpl) {
        this.bwBarrackDaoImpl = bwBarrackDaoImpl;
    }

    public ArrayBlockingQueue<Collection<BwBarrackVO>> getClq() {
        return clq;
    }

    public void setClq(ArrayBlockingQueue<Collection<BwBarrackVO>> clq) {
        this.clq = clq;
    }

}
