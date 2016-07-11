package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 区域表
 */
public class BwAreaVO implements Serializable {

    /**
     * area_id
     *
     * 区域ID
     */
    private long areaId;
    /**
     * area_name
     *
     * 区域名称
     */
    private String areaName;
    /**
     * status
     *
     * 0不可用 1可用 2停区维护
     */
    private int status;
    //每个区域里面的游戏服务器
    private ConcurrentHashMap<Long, BwGameChannleVO> gameChannleHashMap;

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ConcurrentHashMap<Long, BwGameChannleVO> getGameChannleHashMap() {
        return gameChannleHashMap;
    }

    public void setGameChannleHashMap(
            ConcurrentHashMap<Long, BwGameChannleVO> gameChannleHashMap) {
        this.gameChannleHashMap = gameChannleHashMap;
    }

}
