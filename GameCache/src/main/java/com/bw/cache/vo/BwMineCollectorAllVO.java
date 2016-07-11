package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 金币和药水收集的总数 表
 */
public class BwMineCollectorAllVO implements Serializable,
        Comparable<BwMineCollectorAllVO> {

    /**
     *
     */
    private static final long serialVersionUID = -3974701981440391851L;
    /**
     * collect_count
     *
     * 收集到的金币/或者药水的数量
     */
    private long collectCount;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * user_building_data_id
     *
     * 用户建筑数据表的ID
     */
    private long userBuildingDataId;

    private long secondElixirCount;

    private String mailAddress;

    public long getSecondElixirCount() {
        return secondElixirCount;
    }

    public void setSecondElixirCount(long secondElixirCount) {
        this.secondElixirCount = secondElixirCount;
    }

    public long getCollectcount() {
        return collectCount;
    }

    public void setCollectcount(long collectCount) {
        this.collectCount = collectCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserbuildingdataid() {
        return userBuildingDataId;
    }

    public void setUserbuildingdataid(long userBuildingDataId) {
        this.userBuildingDataId = userBuildingDataId;
    }

    @Override
    public int compareTo(BwMineCollectorAllVO o) {
        Integer cCount = (int) this.getCollectcount();
        Integer newCCount = (int) this.getCollectcount();
        return cCount.compareTo(newCCount);
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

}
