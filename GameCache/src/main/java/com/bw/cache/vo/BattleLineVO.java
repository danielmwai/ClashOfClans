package com.bw.cache.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author denny zhao 判断用户状态 0在线 1下线 2 战斗
 *
 */
public class BattleLineVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5995137528610155364L;
    /**
     *
     */
    public String mailAddress;
    //用于memcached cas 时的一个标记
    public long casflag;
    //战斗开始时间 包含30秒的时间
    public Date battleStartTime;
    //30秒等待时间
    public int waitSelectedTime;

    //0在线 1下线 2战斗,3等待状态
    private int lineStatus;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public long getCasflag() {
        return casflag;
    }

    public void setCasflag(long casflag) {
        this.casflag = casflag;
    }

    public int getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(int lineStatus) {
        this.lineStatus = lineStatus;
    }

    public int getWaitSelectedTime() {
        return waitSelectedTime;
    }

    public void setWaitSelectedTime(int waitSelectedTime) {
        this.waitSelectedTime = waitSelectedTime;
    }

    public Date getBattleStartTime() {
        return battleStartTime;
    }

    public void setBattleStartTime(Date battleStartTime) {
        this.battleStartTime = battleStartTime;
    }

}
