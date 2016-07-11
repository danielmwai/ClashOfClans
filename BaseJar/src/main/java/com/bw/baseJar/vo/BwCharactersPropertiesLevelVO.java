package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 角色等级属性表
 */
public class BwCharactersPropertiesLevelVO implements Serializable {

    /**
     * animation
     *
     * 动画名称
     */
    private String animation;
    /**
     * attack_effect
     *
     * 士兵攻击特效
     */
    private String attackEffect;
    /**
     * character_id
     *
     * 角色ID
     */
    private long characterId;
    /**
     * character_level
     *
     * 角色等级
     */
    private long characterLevel;
    /**
     * damage
     *
     * 攻击力
     */
    private long damage;
    /**
     * damage_radius
     *
     * 攻击的溅射范围(范围伤害型)
     */
    private long damageRadius;
    /**
     * hit_points
     *
     * 生命值
     */
    private long hitPoints;
    /**
     * hited_effect
     *
     * 被攻击效果
     */
    private String hitedEffect;
    /**
     * id
     *
     * 角色等级表ID
     */
    private long id;
    /**
     * laboratory_level
     *
     * 升级需要的科技建筑等级
     */
    private long laboratoryLevel;
    /**
     * projectile_name
     *
     * 子弹名称(弓箭兵发射出去的箭)
     */
    private String projectileName;
    /**
     * training_resource_cost
     *
     * 消耗的资源数量
     */
    private long trainingResourceCost;
    /**
     * upgrade_resource_cose
     *
     * 升级需要消耗的资源数量
     */
    private long upgradeResourceCose;
    /**
     * upgrade_time
     *
     * 升级需要的时间，以小时为单位
     */
    private long upgradeTime;

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

    public String getAttackeffect() {
        return attackEffect;
    }

    public void setAttackeffect(String attackEffect) {
        this.attackEffect = attackEffect;
    }

    public long getCharacterid() {
        return characterId;
    }

    public void setCharacterid(long characterId) {
        this.characterId = characterId;
    }

    public long getCharacterlevel() {
        return characterLevel;
    }

    public void setCharacterlevel(long characterLevel) {
        this.characterLevel = characterLevel;
    }

    public long getDamage() {
        return damage;
    }

    public void setDamage(long damage) {
        this.damage = damage;
    }

    public long getDamageradius() {
        return damageRadius;
    }

    public void setDamageradius(long damageRadius) {
        this.damageRadius = damageRadius;
    }

    public long getHitpoints() {
        return hitPoints;
    }

    public void setHitpoints(long hitPoints) {
        this.hitPoints = hitPoints;
    }

    public String getHitedeffect() {
        return hitedEffect;
    }

    public void setHitedeffect(String hitedEffect) {
        this.hitedEffect = hitedEffect;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLaboratorylevel() {
        return laboratoryLevel;
    }

    public void setLaboratorylevel(long laboratoryLevel) {
        this.laboratoryLevel = laboratoryLevel;
    }

    public String getProjectilename() {
        return projectileName;
    }

    public void setProjectilename(String projectileName) {
        this.projectileName = projectileName;
    }

    public long getTrainingresourcecost() {
        return trainingResourceCost;
    }

    public void setTrainingresourcecost(long trainingResourceCost) {
        this.trainingResourceCost = trainingResourceCost;
    }

    public long getUpgraderesourcecose() {
        return upgradeResourceCose;
    }

    public void setUpgraderesourcecose(long upgradeResourceCose) {
        this.upgradeResourceCose = upgradeResourceCose;
    }

    public long getUpgradetime() {
        return upgradeTime;
    }

    public void setUpgradetime(long upgradeTime) {
        this.upgradeTime = upgradeTime;
    }
}
