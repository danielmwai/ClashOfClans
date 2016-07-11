package com.bw.cache.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 平台用户数据
 */
public class BwPlantUserVO implements Serializable {

    /**
     * bowei_id
     *
     *
     */
    private String boweiId;
    /**
     * mac_address
     *
     *
     */
    private String macAddress;
    /**
     * mail_address
     *
     * 如果mailAddress为空 用mac地址来替代
     */
    private String mailAddress;
    /**
     * nick_name
     *
     *
     */
    private String nickName;
    /**
     * pass_word
     *
     *
     */
    private String passWord;
    /**
     * platform_type
     *
     *
     */
    private int platformType;
    //分区
    private int areaId;
    private long sessionId;

    public String getBoweiid() {
        return boweiId;
    }

    public void setBoweiid(String boweiId) {
        this.boweiId = boweiId;
    }

    public String getMacaddress() {
        return macAddress;
    }

    public void setMacaddress(String macAddress) {
        this.macAddress = macAddress;
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

    public String getPassword() {
        return passWord;
    }

    public void setPassword(String passWord) {
        this.passWord = passWord;
    }

    public int getPlatformtype() {
        return platformType;
    }

    public void setPlatformtype(int platformType) {
        this.platformType = platformType;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

}
