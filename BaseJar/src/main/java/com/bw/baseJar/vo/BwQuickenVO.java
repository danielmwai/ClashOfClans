package com.bw.baseJar.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 加速基础表
 */
public class BwQuickenVO implements Serializable {

    /**
     * id
     *
     *
     */
    private long id;
    /**
     * price
     *
     * 以绿宝石来计价
     */
    private int price;
    /**
     * quicken_time
     *
     * 加速时间
     */
    private int quickenTime;
    /**
     * quicken_type
     *
     * 0分钟 1小时 2 天3 一周
     */
    private int quickenType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuickenTime() {
        return quickenTime;
    }

    public void setQuickenTime(int quickenTime) {
        this.quickenTime = quickenTime;
    }

    public int getQuickenType() {
        return quickenType;
    }

    public void setQuickenType(int quickenType) {
        this.quickenType = quickenType;
    }
}
