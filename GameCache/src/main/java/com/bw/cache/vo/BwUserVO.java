package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 用户表
 */
public class BwUserVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8597283477025467871L;
    /**
     * elixir_count
     *
     * 药水数量
     */
    private long elixirCount;
    /**
     * exp
     *
     *
     */
    private long exp;
    /**
     * golden_count
     *
     * 金币数量
     */
    private long goldenCount;
    /**
     * id
     *
     * 主键自动增长ID
     */
    private long id;
    /**
     * last_login_time
     *
     * 最好一次登录时间
     */
    private String lastLoginTime;
    /**
     * level
     *
     * 用户等级
     */
    private int level;
    /**
     * mail_address
     *
     * 这里的mailAddress等于bw_plant_user 里面的bowei_id
     */
    private String mailAddress;
    /**
     * nick_name
     *
     * 用户昵称
     */
    private String nickName;
    /**
     * pvp_mark
     *
     * pvp 分数
     */
    private long pvpMark;
    //用户绿宝石表
    private BwUserBankVO userBankVO;
    //保护结束时间
    private String shieldEndTime;
    //0正常 1需要有全部建筑的回复动画
    private boolean battleStatus;
    //用户等待状态 0未等待 1正在等待
    private int userWaitFlag;
    //mac地址
    private String macAddress;
    //农民数量
    private int workCount;
    //空闲的数量
    private int free_worker_count;
    //area_id
    private int areaId = 1;
    //最大金币数量
    private int maxGoldenCount;
    //最大药水数量
    private int maxElixirCount;
    //pvpGrade排行
    private String PvpGradeOrderSQL;
    //用户类型 0自定义 1机器人
    private int userType;

    public long getElixircount() {
        return elixirCount;
    }

    public void setElixircount(long elixirCount) {
        this.elixirCount = elixirCount;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getGoldencount() {
        return goldenCount;
    }

    public void setGoldencount(long goldenCount) {
        this.goldenCount = goldenCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastlogintime() {
        return lastLoginTime;
    }

    public void setLastlogintime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMailaddress() {
        return mailAddress;
    }

    public void setMailaddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getNickname() {
        return nickName;
    }

    public void setNickname(String nickName) {
        this.nickName = nickName;
    }

    public long getPvpmark() {
        return pvpMark;
    }

    public void setPvpmark(long pvpMark) {
        this.pvpMark = pvpMark;
    }

    public BwUserBankVO getUserBankVO() {
        return userBankVO;
    }

    public void setUserBankVO(BwUserBankVO userBankVO) {
        this.userBankVO = userBankVO;
    }

    public String getShieldEndTime() {
        return shieldEndTime;
    }

    public void setShieldEndTime(String shieldEndTime) {
        this.shieldEndTime = shieldEndTime;
    }

    public boolean isBattleStatus() {
        return battleStatus;
    }

    public void setBattleStatus(boolean battleStatus) {
        this.battleStatus = battleStatus;
    }

    public int getUserWaitFlag() {
        return userWaitFlag;
    }

    public void setUserWaitFlag(int userWaitFlag) {
        this.userWaitFlag = userWaitFlag;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getWorkCount() {
        return workCount;
    }

    public void setWorkCount(int workCount) {
        this.workCount = workCount;
    }

    public int getFree_worker_count() {
        return free_worker_count;
    }

    public void setFree_worker_count(int freeWorkerCount) {
        free_worker_count = freeWorkerCount;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getMaxGoldenCount() {
        return maxGoldenCount;
    }

    public void setMaxGoldenCount(int maxGoldenCount) {
        this.maxGoldenCount = maxGoldenCount;
    }

    public int getMaxElixirCount() {
        return maxElixirCount;
    }

    public void setMaxElixirCount(int maxElixirCount) {
        this.maxElixirCount = maxElixirCount;
    }

    public String getPvpGradeOrderSQL() {
        return PvpGradeOrderSQL;
    }

    public void setPvpGradeOrderSQL(String pvpGradeOrderSQL) {
        PvpGradeOrderSQL = pvpGradeOrderSQL;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
