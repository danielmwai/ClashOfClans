package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 用户战斗统计表
 */
public class BwUserBattleStatisticsVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5134789260290478119L;
    /**
     * clans_id
     *
     * 工会ID
     */
    private long clansId;
    /**
     * fail_times
     *
     * 失败次数
     */
    private long failTimes;
    /**
     * get_elixir_count
     *
     * 获取总共药水的数量
     */
    private long getElixirCount;
    /**
     * get_golden_count
     *
     * 获取总共金币的数量
     */
    private long getGoldenCount;
    /**
     * mail_address
     *
     *
     */
    private String mailAddress;
    /**
     * max_pvp_mark
     *
     * 最大pvp分数
     */
    private long maxPvpMark;
    /**
     * win_times
     *
     * 胜利次数
     */
    private long winTimes;

    public long getClansid() {
        return clansId;
    }

    public void setClansid(long clansId) {
        this.clansId = clansId;
    }

    public long getFailtimes() {
        return failTimes;
    }

    public void setFailtimes(long failTimes) {
        this.failTimes = failTimes;
    }

    public long getGetelixircount() {
        return getElixirCount;
    }

    public void setGetelixircount(long getElixirCount) {
        this.getElixirCount = getElixirCount;
    }

    public long getGetgoldencount() {
        return getGoldenCount;
    }

    public void setGetgoldencount(long getGoldenCount) {
        this.getGoldenCount = getGoldenCount;
    }

    public String getMailaddress() {
        return mailAddress;
    }

    public void setMailaddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public long getMaxpvpmark() {
        return maxPvpMark;
    }

    public void setMaxpvpmark(long maxPvpMark) {
        this.maxPvpMark = maxPvpMark;
    }

    public long getWintimes() {
        return winTimes;
    }

    public void setWintimes(long winTimes) {
        this.winTimes = winTimes;
    }
}
