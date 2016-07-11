package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 魔法表
 */
public class BwSpellVOSource implements Serializable {

    /**
     * charging_Time_ms
     *
     * 效果准备时间毫秒
     */
    private int chargingTimeMs;
    /**
     * cooldown_s
     *
     * 冷却时间秒
     */
    private int cooldownS;
    /**
     * hit_time_ms
     *
     * 效果发作时间毫秒
     */
    private int hitTimeMs;
    /**
     * housing_space
     *
     * 魔法所占储存空间
     */
    private int housingSpace;
    /**
     * spell_id
     *
     *
     */
    private long spellId;
    /**
     * spell_name
     *
     * 加血
     */
    private String spellName;
    /**
     * training_resource
     *
     * 制造花费资源种类 0 金币 1药水 2绿宝石
     */
    private int trainingResource;
    /**
     * training_time
     *
     * 制造时间 单位秒
     */
    private int trainingTime;
    /**
     * unlock_spell
     *
     * 魔法解锁需要的建筑等级
     */
    private int unlockSpell;
    /**
     * upgrade_resource
     *
     * 升级需要的资源
     */
    private int upgradeResource;

    public int getChargingtimems() {
        return chargingTimeMs;
    }

    public void setChargingtimems(int chargingTimeMs) {
        this.chargingTimeMs = chargingTimeMs;
    }

    public int getCooldowns() {
        return cooldownS;
    }

    public void setCooldowns(int cooldownS) {
        this.cooldownS = cooldownS;
    }

    public int getHittimems() {
        return hitTimeMs;
    }

    public void setHittimems(int hitTimeMs) {
        this.hitTimeMs = hitTimeMs;
    }

    public int getHousingspace() {
        return housingSpace;
    }

    public void setHousingspace(int housingSpace) {
        this.housingSpace = housingSpace;
    }

    public long getSpellid() {
        return spellId;
    }

    public void setSpellid(long spellId) {
        this.spellId = spellId;
    }

    public String getSpellname() {
        return spellName;
    }

    public void setSpellname(String spellName) {
        this.spellName = spellName;
    }

    public int getTrainingresource() {
        return trainingResource;
    }

    public void setTrainingresource(int trainingResource) {
        this.trainingResource = trainingResource;
    }

    public int getTrainingtime() {
        return trainingTime;
    }

    public void setTrainingtime(int trainingTime) {
        this.trainingTime = trainingTime;
    }

    public int getUnlockspell() {
        return unlockSpell;
    }

    public void setUnlockspell(int unlockSpell) {
        this.unlockSpell = unlockSpell;
    }

    public int getUpgraderesource() {
        return upgradeResource;
    }

    public void setUpgraderesource(int upgradeResource) {
        this.upgradeResource = upgradeResource;
    }
}
