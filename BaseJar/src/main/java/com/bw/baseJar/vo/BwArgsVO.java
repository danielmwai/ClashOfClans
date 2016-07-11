package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 游戏服务器常数表
 */
public class BwArgsVO implements Serializable {

    /**
     * id
     *
     *
     */
    private long id;
    /**
     * pvp_max_k
     *
     * 获取pvp分数的最大k
     */
    private int pvpMaxK;
    /**
     * pvp_n
     *
     * 获取pvp分数的n
     */
    private int pvpN;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPvpMaxK() {
        return pvpMaxK;
    }

    public void setPvpMaxK(int pvpMaxK) {
        this.pvpMaxK = pvpMaxK;
    }

    public int getPvpN() {
        return pvpN;
    }

    public void setPvpN(int pvpN) {
        this.pvpN = pvpN;
    }
}
