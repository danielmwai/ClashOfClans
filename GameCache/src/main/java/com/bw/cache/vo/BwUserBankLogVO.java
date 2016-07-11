package com.bw.cache.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 用户充值日志系统
 */
public class BwUserBankLogVO implements Serializable {

    /**
     * buy_gem_count
     *
     * 购买的宝石数量
     */
    private long buyGemCount;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * mail_address
     *
     * 用户的账号
     */
    private String mailAddress;
    /**
     * old_gem_total_count
     *
     * 当前的绿宝石总数量
     */
    private long oldGemTotalCount;
    /**
     * pay_money
     *
     * 花费
     */
    private int payMoney;
    /**
     * pay_time
     *
     * 充值时间
     */
    private String payTime;
    /**
     * treasure_id
     *
     * 财富类型
     */
    private String treasureId;
    //购买状态0 成功 1失败
    private int buyStatus;
    //博维ID
    private String boweiId;
    //app_id
    private int appId;
    //plant_type
    private int plantType;
    //order_sn
    private String orderSn;
    //苹果的收入数据
    private String receipt;
    //创建时间
    private String createTime;

    public long getBuygemcount() {
        return buyGemCount;
    }

    public void setBuygemcount(long buyGemCount) {
        this.buyGemCount = buyGemCount;
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

    public long getOldgemtotalcount() {
        return oldGemTotalCount;
    }

    public void setOldgemtotalcount(long oldGemTotalCount) {
        this.oldGemTotalCount = oldGemTotalCount;
    }

    public int getPaymoney() {
        return payMoney;
    }

    public void setPaymoney(int payMoney) {
        this.payMoney = payMoney;
    }

    public String getPaytime() {
        return payTime;
    }

    public void setPaytime(String payTime) {
        this.payTime = payTime;
    }

    public String getTreasureid() {
        return treasureId;
    }

    public void setTreasureid(String treasureId) {
        this.treasureId = treasureId;
    }

    public int getBuyStatus() {
        return buyStatus;
    }

    public void setBuyStatus(int buyStatus) {
        this.buyStatus = buyStatus;
    }

    public String getBoweiId() {
        return boweiId;
    }

    public void setBoweiId(String boweiId) {
        this.boweiId = boweiId;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getPlantType() {
        return plantType;
    }

    public void setPlantType(int plantType) {
        this.plantType = plantType;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
