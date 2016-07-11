package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 用户兵种表
 */
public class BwUserCharacterVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8731670335857009669L;
    /**
     * character_count
     *
     * 士兵数量
     */
    private int characterCount;
    /**
     * character_level
     *
     * 兵等级
     */
    private int characterLevel;
    /**
     * chararchter_id
     *
     * 兵ID
     */
    private int chararchterId;
    /**
     * id
     *
     * 主键id
     */
    private long id;
    /**
     * mail_address
     *
     *
     */
    private String mailAddress;
    /**
     * upgrade_character_finish_time
     *
     * 兵种升级完成时间(用于实验室升级用)
     */
    private String upgradeCharacterFinishTime;

    public int getCharactercount() {
        return characterCount;
    }

    public void setCharactercount(int characterCount) {
        this.characterCount = characterCount;
    }

    public int getCharacterlevel() {
        return characterLevel;
    }

    public void setCharacterlevel(int characterLevel) {
        this.characterLevel = characterLevel;
    }

    public int getChararchterid() {
        return chararchterId;
    }

    public void setChararchterid(int chararchterId) {
        this.chararchterId = chararchterId;
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

    public String getUpgradecharacterfinishtime() {
        return upgradeCharacterFinishTime;
    }

    public void setUpgradecharacterfinishtime(String upgradeCharacterFinishTime) {
        this.upgradeCharacterFinishTime = upgradeCharacterFinishTime;
    }
}
