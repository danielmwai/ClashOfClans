package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 用户等级要求表
 */
public class BwUserLevelReqVO implements Serializable {

    /**
     * exp_req
     *
     * 要求的经验
     */
    private long expReq;
    /**
     * level_id
     *
     * 等级
     */
    private long levelId;

    public long getExpreq() {
        return expReq;
    }

    public void setExpreq(long expReq) {
        this.expReq = expReq;
    }

    public long getLevelid() {
        return levelId;
    }

    public void setLevelid(long levelId) {
        this.levelId = levelId;
    }
}
