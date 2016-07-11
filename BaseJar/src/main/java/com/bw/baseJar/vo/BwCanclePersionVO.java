package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 封号表
 */
public class BwCanclePersionVO implements Serializable {

    /**
     * bowei_id
     *
     *
     */
    private String boweiId;
    /**
     * create_time
     *
     * 创建时间
     */
    private String createTime;
    /**
     * id
     *
     *
     */
    private long id;

    public String getBoweiId() {
        return boweiId;
    }

    public void setBoweiId(String boweiId) {
        this.boweiId = boweiId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
