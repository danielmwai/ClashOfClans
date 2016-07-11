package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 *
 */
public class BwUserSpellVO implements Serializable {

    /**
     *
     */
    /**
     *
     */
    private static final long serialVersionUID = 6367443584244156774L;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * mail_address
     *
     * 用户建筑信息ID
     */
    private String mailAddress;
    /**
     * spell_count
     *
     *
     */
    private int spellCount;
    /**
     * spell_level
     *
     *
     */
    private int spellLevel;
    /**
     * spell_type_id
     *
     * 魔法类型ID
     */
    private int spellTypeId;
    /**
     * spell_upgrade_end_time
     *
     * 魔法升级结束时间
     */
    private String spellUpgradeEndTime;

    private long usermapdataid;

    public long getUsermapdataid() {
        return usermapdataid;
    }

    public void setUsermapdataid(long usermapdataid) {
        this.usermapdataid = usermapdataid;
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

    public int getSpellcount() {
        return spellCount;
    }

    public void setSpellcount(int spellCount) {
        this.spellCount = spellCount;
    }

    public int getSpelllevel() {
        return spellLevel;
    }

    public void setSpelllevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }

    public int getSpelltypeid() {
        return spellTypeId;
    }

    public void setSpelltypeid(int spellTypeId) {
        this.spellTypeId = spellTypeId;
    }

    public String getSpellupgradeendtime() {
        return spellUpgradeEndTime;
    }

    public void setSpellupgradeendtime(String spellUpgradeEndTime) {
        this.spellUpgradeEndTime = spellUpgradeEndTime;
    }
}
