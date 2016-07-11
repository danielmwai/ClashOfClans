package com.bw.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.bw.cache.vo.BwUserCharacterVO;
import com.bw.dao.BwUserCharacterDAO;

/**
 * @author denny zhao 日期:2012-12-10 更新用户兵力信息表
 */
public class BwUserCharacterVOWriter {

    private Logger log = Logger.getLogger(BwUserCharacterVOWriter.class);
    private BwUserCharacterDAO bwUserCharacterDaoImpl;
    public ArrayBlockingQueue<Collection<BwUserCharacterVO>> clq = new ArrayBlockingQueue<Collection<BwUserCharacterVO>>(100000);
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
            log.info("开始批量更新用户兵力信息....");
            long updateCount = 0;
            while (!clq.isEmpty() && updateCount < perRunUpdateCount) {
                Collection<BwUserCharacterVO> c = clq.poll();
                long allcount = c.size();
                if (allcount > 0) {
                    //
                    List<BwUserCharacterVO> listtime1 = new ArrayList<BwUserCharacterVO>();
                    for (BwUserCharacterVO info : c) {
                        listtime1.add(info);
                        updateCount++;
                        if (listtime1.size() >= beishu) {
//								log.info("1000开始批量更新数据库用户表：时间="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+" 记录条数："+listtime1.size());
                            bwUserCharacterDaoImpl.batchUpdate(listtime1);
//								log.info("1000结束批量更新数据库用户表：时间="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+" 记录条数："+listtime1.size());
                            listtime1.clear();
                        }

                    }
                    if (!listtime1.isEmpty()) {
//							log.info("1000开始批量更新数据库用户表：时间="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+" 记录条数："+listtime1.size());
                        bwUserCharacterDaoImpl.batchUpdate(listtime1);
//							log.info("1000结束批量更新数据库用户表：时间="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+" 记录条数："+listtime1.size());
                        listtime1.clear();
                        listtime1 = null;
                    }
                }
                c.clear();
                c = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("后台批量更新用户兵力发生错误:", e);
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

    public ArrayBlockingQueue<Collection<BwUserCharacterVO>> getClq() {
        return clq;
    }

    public void setClq(ArrayBlockingQueue<Collection<BwUserCharacterVO>> clq) {
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

    public BwUserCharacterDAO getBwUserCharacterDaoImpl() {
        return bwUserCharacterDaoImpl;
    }

    public void setBwUserCharacterDaoImpl(BwUserCharacterDAO bwUserCharacterDaoImpl) {
        this.bwUserCharacterDaoImpl = bwUserCharacterDaoImpl;
    }

}
