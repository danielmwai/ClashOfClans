package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 金矿和药水收集状态
 */
public class BwMineCollectorVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5032374635248457253L;
    /**
     * harvest_start_time
     *
     * 收获开始时间
     */
    private String harvestStartTime;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * produce_count
     *
     * 生产数量
     */
    private long produceCount;
    /**
     * user_building_data_id
     *
     * 用户建筑数据表的ID
     */
    private long userBuildingDataId;
    private String mailAddress;
    //0未满  1已经满了(用户的最大储量) 停止收集
    private int status;

    public String getHarveststarttime() {
        return harvestStartTime;
    }

    public void setHarveststarttime(String harvestStartTime) {
        this.harvestStartTime = harvestStartTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProducecount() {
        return produceCount;
    }

    public void setProducecount(long produceCount) {
        this.produceCount = produceCount;
    }

    public long getUserbuildingdataid() {
        return userBuildingDataId;
    }

    public void setUserbuildingdataid(long userBuildingDataId) {
        this.userBuildingDataId = userBuildingDataId;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
