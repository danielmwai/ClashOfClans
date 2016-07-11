package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 用户地图数据表
 */
public class BwUserMapDataVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1892172277042288908L;
    /**
     * build_id
     *
     * 建筑ID
     */
    private int buildId;
    /**
     * build_level
     *
     *
     */
    private int buildLevel;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * mail_address
     *
     * 用户_id
     */
    private String mailAddress;
    /**
     * map_index_x
     *
     * 所在格子的x
     */
    private int mapIndexX;
    /**
     * map_index_y
     *
     * 格子的Y坐标
     */
    private int mapIndexY;
    /**
     * status
     *
     * 土地状态 0未被占用 1被占用
     */
    private int status;
    /**
     * uniqueness_build_id
     *
     * 唯一性建筑ID
     */
    private long uniquenessBuildId;
    /**
     * upgrade_finish_time
     *
     * 建筑升级完成时间
     */
    private String upgradeFinishTime;
    private short deleteFlag;

    public short getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(short deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getBuildid() {
        return buildId;
    }

    public void setBuildid(int buildId) {
        this.buildId = buildId;
    }

    public int getBuildlevel() {
        return buildLevel;
    }

    public void setBuildlevel(int buildLevel) {
        this.buildLevel = buildLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMailaddress() {

        return mailAddress;
    }

    public void setMailaddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public int getMapindexx() {
        return mapIndexX;
    }

    public void setMapindexx(int mapIndexX) {
        this.mapIndexX = mapIndexX;
    }

    public int getMapindexy() {
        return mapIndexY;
    }

    public void setMapindexy(int mapIndexY) {
        this.mapIndexY = mapIndexY;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUniquenessbuildid() {
        return uniquenessBuildId;
    }

    public void setUniquenessbuildid(long uniquenessBuildId) {
        this.uniquenessBuildId = uniquenessBuildId;
    }

    public String getUpgradefinishtime() {
        return upgradeFinishTime;
    }

    public void setUpgradefinishtime(String upgradeFinishTime) {
        this.upgradeFinishTime = upgradeFinishTime;
    }
}
