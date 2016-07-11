package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 魔法属性等级表
 */
public class BwSpellPropertiesLevelVO implements Serializable {

    /**
     * attack_speed_boost
     *
     * 攻击速度促进量
     */
    private long attackSpeedBoost;
    /**
     * boost_time_ms
     *
     * 加速状态持续时间（毫秒）
     */
    private long boostTimeMs;
    /**
     * damage
     *
     *
     */
    private int damage;
    /**
     * damage_boost_percent
     *
     * 伤害系数改变为 百分比
     */
    private int damageBoostPercent;
    /**
     * icon_export_name
     *
     * 图标输出名称
     */
    private String iconExportName;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * jump_boost_ms
     *
     * 跳跃状态持续时间 毫秒
     */
    private long jumpBoostMs;
    /**
     * jump_housing_limit
     *
     * 跳跃次数限制 -1 代表此字段无效
     */
    private int jumpHousingLimit;
    /**
     * laboratory_level
     *
     * 魔法升级需要的科技建筑等级
     */
    private int laboratoryLevel;
    /**
     * move_speed_boost
     *
     * 移动速度促进量
     */
    private long moveSpeedBoost;
    /**
     * number_of_hits
     *
     * 伤害次数
     */
    private int numberOfHits;
    /**
     * radius
     *
     * 伤害影响范围
     */
    private int radius;
    /**
     * random_radius
     *
     * 随机跳转的范围
     */
    private int randomRadius;
    /**
     * spell_id
     *
     *
     */
    private int spellId;
    /**
     * spell_level
     *
     *
     */
    private int spellLevel;
    /**
     * time_betweenHits_ms
     *
     * 伤害间隔
     */
    private int timeBetweenhitsMs;
    /**
     * training_cost
     *
     * 制造花费资源数量
     */
    private long trainingCost;
    /**
     * upgrade_cost
     *
     * 升级需要资源数量
     */
    private long upgradeCost;
    /**
     * upgrade_time_H
     *
     * 升级时间 以小时为单位
     */
    private int upgradeTimeH;

    public long getAttackspeedboost() {
        return attackSpeedBoost;
    }

    public void setAttackspeedboost(long attackSpeedBoost) {
        this.attackSpeedBoost = attackSpeedBoost;
    }

    public long getBoosttimems() {
        return boostTimeMs;
    }

    public void setBoosttimems(long boostTimeMs) {
        this.boostTimeMs = boostTimeMs;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamageboostpercent() {
        return damageBoostPercent;
    }

    public void setDamageboostpercent(int damageBoostPercent) {
        this.damageBoostPercent = damageBoostPercent;
    }

    public String getIconexportname() {
        return iconExportName;
    }

    public void setIconexportname(String iconExportName) {
        this.iconExportName = iconExportName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJumpboostms() {
        return jumpBoostMs;
    }

    public void setJumpboostms(long jumpBoostMs) {
        this.jumpBoostMs = jumpBoostMs;
    }

    public int getJumphousinglimit() {
        return jumpHousingLimit;
    }

    public void setJumphousinglimit(int jumpHousingLimit) {
        this.jumpHousingLimit = jumpHousingLimit;
    }

    public int getLaboratorylevel() {
        return laboratoryLevel;
    }

    public void setLaboratorylevel(int laboratoryLevel) {
        this.laboratoryLevel = laboratoryLevel;
    }

    public long getMovespeedboost() {
        return moveSpeedBoost;
    }

    public void setMovespeedboost(long moveSpeedBoost) {
        this.moveSpeedBoost = moveSpeedBoost;
    }

    public int getNumberofhits() {
        return numberOfHits;
    }

    public void setNumberofhits(int numberOfHits) {
        this.numberOfHits = numberOfHits;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRandomradius() {
        return randomRadius;
    }

    public void setRandomradius(int randomRadius) {
        this.randomRadius = randomRadius;
    }

    public int getSpellid() {
        return spellId;
    }

    public void setSpellid(int spellId) {
        this.spellId = spellId;
    }

    public int getSpelllevel() {
        return spellLevel;
    }

    public void setSpelllevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }

    public int getTimebetweenhitsms() {
        return timeBetweenhitsMs;
    }

    public void setTimebetweenhitsms(int timeBetweenhitsMs) {
        this.timeBetweenhitsMs = timeBetweenhitsMs;
    }

    public long getTrainingcost() {
        return trainingCost;
    }

    public void setTrainingcost(long trainingCost) {
        this.trainingCost = trainingCost;
    }

    public long getUpgradecost() {
        return upgradeCost;
    }

    public void setUpgradecost(long upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public int getUpgradetimeh() {
        return upgradeTimeH;
    }

    public void setUpgradetimeh(int upgradeTimeH) {
        this.upgradeTimeH = upgradeTimeH;
    }
}
