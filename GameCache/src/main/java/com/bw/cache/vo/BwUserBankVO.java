package com.bw.cache.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 用户宝石表
 */
public class BwUserBankVO implements Serializable {

    /**
     * gem_total_count
     *
     * 宝石总数量
     */
    private long gemTotalCount;
    /**
     * last_update_time
     *
     * 最好一次更新时间
     */
    private String lastUpdateTime;
    /**
     * mail_address
     *
     *
     */
    private String mailAddress;
    private long id;
    //消费的人们币总数
    private long payTotalMoney;
    //博维ID
    private String boweiId;

    public long getGemtotalcount() {
        return gemTotalCount;
    }

    public void setGemtotalcount(long gemTotalCount) {
        this.gemTotalCount = gemTotalCount;
    }

    public String getLastupdatetime() {
        return lastUpdateTime;
    }

    public void setLastupdatetime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getMailaddress() {
        return mailAddress;
    }

    public void setMailaddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPayTotalMoney() {
        return payTotalMoney;
    }

    public void setPayTotalMoney(long payTotalMoney) {
        this.payTotalMoney = payTotalMoney;
    }

    public String getBoweiId() {
        return boweiId;
    }

    public void setBoweiId(String boweiId) {
        this.boweiId = boweiId;
    }

}
