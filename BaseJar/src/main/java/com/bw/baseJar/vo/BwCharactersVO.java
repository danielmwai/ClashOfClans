package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * base role table
 */
public class BwCharactersVO implements Serializable {

    /**
     * UpgradeResource
     *
     * 升级消耗资源的类型 0 金币 1 药水 2 绿宝石
     */
    private int upgraderesource;
    /**
     * air_target
     *
     * 是否可以对空中单位进行攻击 0不可以 1可以
     */
    private int airTarget;
    /**
     * attack_count
     *
     * 攻击几次后死亡
     */
    private int attackCount;
    /**
     * attack_prefered_target
     *
     * 优先攻击的目标 -1此值无效 0任何类型 1资源类型 2 防御类型(炮塔，箭楼) 3wall
     */
    private int attackPreferedTarget;
    /**
     * attack_rang
     *
     * 攻击范围
     */
    private long attackRang;
    /**
     * attack_speed
     *
     * 攻击间隔
     */
    private long attackSpeed;
    /**
     * barrack_level
     *
     * 解锁兵种需要的兵营等级
     */
    private long barrackLevel;
    /**
     * big_picture
     *
     * 对应大尺寸图片
     */
    private String bigPicture;
    /**
     * character_id
     *
     * 角色ID
     */
    private long characterId;
    /**
     * character_name
     *
     * 角色名称
     */
    private String characterName;
    /**
     * damage_mod
     *
     * 对特定目标的攻击倍率
     */
    private long damageMod;
    /**
     * deploy_effect
     *
     * 兵放入地图时的特效
     */
    private String deployEffect;
    /**
     * die_effect
     *
     * 死亡效果
     */
    private String dieEffect;
    /**
     * ground_targets
     *
     * 是否可以攻击地面单位 0 不可以 1可以
     */
    private int groundTargets;
    /**
     * housing_space
     *
     * 所占人口
     */
    private long housingSpace;
    /**
     * icon_name
     *
     * 对应的图标名称（造兵时显示的）
     */
    private String iconName;
    /**
     * is_flying
     *
     * 0地面单位 1 飞行单位
     */
    private int isFlying;
    /**
     * resource_type
     *
     * 造兵需要消耗的资源类型0金币 1药水
     */
    private int resourceType;
    /**
     * speed
     *
     * 移动速度
     */
    private long speed;
    /**
     * swf
     *
     * 动画文件名称
     */
    private String swf;
    /**
     * training_time
     *
     * 造兵需要的时间 以秒为单位
     */
    private long trainingTime;
    /**
     * ui_name
     *
     * 对应UI图标
     */
    private String uiName;

    public int getUpgraderesource() {
        return upgraderesource;
    }

    public void setUpgraderesource(int upgraderesource) {
        this.upgraderesource = upgraderesource;
    }

    public int getAirtarget() {
        return airTarget;
    }

    public void setAirtarget(int airTarget) {
        this.airTarget = airTarget;
    }

    public int getAttackcount() {
        return attackCount;
    }

    public void setAttackcount(int attackCount) {
        this.attackCount = attackCount;
    }

    public int getAttackpreferedtarget() {
        return attackPreferedTarget;
    }

    public void setAttackpreferedtarget(int attackPreferedTarget) {
        this.attackPreferedTarget = attackPreferedTarget;
    }

    public long getAttackrang() {
        return attackRang;
    }

    public void setAttackrang(long attackRang) {
        this.attackRang = attackRang;
    }

    public long getAttackspeed() {
        return attackSpeed;
    }

    public void setAttackspeed(long attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public long getBarracklevel() {
        return barrackLevel;
    }

    public void setBarracklevel(long barrackLevel) {
        this.barrackLevel = barrackLevel;
    }

    public String getBigpicture() {
        return bigPicture;
    }

    public void setBigpicture(String bigPicture) {
        this.bigPicture = bigPicture;
    }

    public long getCharacterid() {
        return characterId;
    }

    public void setCharacterid(long characterId) {
        this.characterId = characterId;
    }

    public String getCharactername() {
        return characterName;
    }

    public void setCharactername(String characterName) {
        this.characterName = characterName;
    }

    public long getDamagemod() {
        return damageMod;
    }

    public void setDamagemod(long damageMod) {
        this.damageMod = damageMod;
    }

    public String getDeployeffect() {
        return deployEffect;
    }

    public void setDeployeffect(String deployEffect) {
        this.deployEffect = deployEffect;
    }

    public String getDieeffect() {
        return dieEffect;
    }

    public void setDieeffect(String dieEffect) {
        this.dieEffect = dieEffect;
    }

    public int getGroundtargets() {
        return groundTargets;
    }

    public void setGroundtargets(int groundTargets) {
        this.groundTargets = groundTargets;
    }

    public long getHousingspace() {
        return housingSpace;
    }

    public void setHousingspace(long housingSpace) {
        this.housingSpace = housingSpace;
    }

    public String getIconname() {
        return iconName;
    }

    public void setIconname(String iconName) {
        this.iconName = iconName;
    }

    public int getIsflying() {
        return isFlying;
    }

    public void setIsflying(int isFlying) {
        this.isFlying = isFlying;
    }

    public int getResourcetype() {
        return resourceType;
    }

    public void setResourcetype(int resourceType) {
        this.resourceType = resourceType;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public String getSwf() {
        return swf;
    }

    public void setSwf(String swf) {
        this.swf = swf;
    }

    public long getTrainingtime() {
        return trainingTime;
    }

    public void setTrainingtime(long trainingTime) {
        this.trainingTime = trainingTime;
    }

    public String getUiname() {
        return uiName;
    }

    public void setUiname(String uiName) {
        this.uiName = uiName;
    }
}
