package com.bw.cache.vo;

import java.io.Serializable;

public class PVPGradeOrderVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5836555508677810257L;
    //用户pvp分数排名
    private int pvpGradeOrder;
    //用户pvp分数名次标记(-1 下降 0 不变 1 上升)
    private int pvpOrderChangeFlag;
    //用户pvp分数名次变化量
    private int pvpOrderChangeCount;
    private int level;//用户等级
    private String nickname;//用户昵称
    private int pvpGrade;//pvp分数
    private String boweiId;
    //排序时间
    private java.util.Date orderTime;

    public int getPvpGradeOrder() {
        return pvpGradeOrder;
    }

    public void setPvpGradeOrder(int pvpGradeOrder) {
        this.pvpGradeOrder = pvpGradeOrder;
    }

    public int getPvpOrderChangeFlag() {
        return pvpOrderChangeFlag;
    }

    public void setPvpOrderChangeFlag(int pvpOrderChangeFlag) {
        this.pvpOrderChangeFlag = pvpOrderChangeFlag;
    }

    public int getPvpOrderChangeCount() {
        return pvpOrderChangeCount;
    }

    public void setPvpOrderChangeCount(int pvpOrderChangeCount) {
        this.pvpOrderChangeCount = pvpOrderChangeCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPvpGrade() {
        return pvpGrade;
    }

    public void setPvpGrade(int pvpGrade) {
        this.pvpGrade = pvpGrade;
    }

    public String getBoweiId() {
        return boweiId;
    }

    public void setBoweiId(String boweiId) {
        this.boweiId = boweiId;
    }

    public java.util.Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(java.util.Date orderTime) {
        this.orderTime = orderTime;
    }

}
