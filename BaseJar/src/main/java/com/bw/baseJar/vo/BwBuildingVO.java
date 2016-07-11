package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 建筑信息表
 */
public class BwBuildingVO implements Serializable {

    /**
     * air_targets
     *
     * 对空攻击 -1 无效 0 false 1 ture
     */
    private int airTargets;
    /**
     * attack_range
     *
     * 攻击范围
     */
    private long attackRange;
    /**
     * attack_speed
     *
     * 攻击速度
     */
    private long attackSpeed;
    /**
     * build_resource_type
     *
     * 建筑消耗的资源0 金币 1 药水 2绿宝石
     */
    private int buildResourceType;
    /**
     * build_time_date
     *
     * 建筑时间-日期
     */
    private long buildTimeDate;
    /**
     * build_time_hour
     *
     * 建筑时间-小时
     */
    private long buildTimeHour;
    /**
     * build_time_minutes
     *
     * 建筑时间--分钟
     */
    private long buildTimeMinutes;
    /**
     * building_animation
     *
     * 建造中动画
     */
    private String buildingAnimation;
    /**
     * building_crash_h
     *
     * 建筑的碰撞范围 高
     */
    private int buildingCrashH;
    /**
     * building_crash_w
     *
     * 建筑的碰撞范围 宽
     */
    private int buildingCrashW;
    /**
     * building_id
     *
     *
     */
    private long buildingId;
    /**
     * building_name
     *
     * 建筑名称
     */
    private String buildingName;
    /**
     * building_type
     *
     * 0 army 1 town hall 2 resource 3 defense 4 wall 5 worker 6 npc\n
     */
    private long buildingType;
    /**
     * damage_radius
     *
     * 溅射范围 -1 为无效值
     */
    private long damageRadius;
    /**
     * ground_targets
     *
     * 对地攻击 -1 无效 0 false 1 ture
     */
    private int groundTargets;
    /**
     * height
     *
     * 占地范围高
     */
    private int height;
    /**
     * is_bunker
     *
     * 是否是堡垒0不是 1是
     */
    private int isBunker;
    /**
     * is_hidden
     *
     * 是否需要隐身 0不需要　1需要
     */
    private int isHidden;
    /**
     * is_locked
     *
     * 是否需要解锁　堡垒的专有功能　0不需要解锁　1需要解锁
     */
    private int isLocked;
    /**
     * is_sell
     *
     * 建筑是否可以出售 0 不可以出售　1可以出售
     */
    private int isSell;
    /**
     * min_attack_range
     *
     * 最小攻击范围
     */
    private long minAttackRange;
    /**
     * produces_resource_type
     *
     * 生成资源的种类 0 金币 1 药水 2 绿宝石
     */
    private int producesResourceType;
    /**
     * push_back
     *
     * 攻击附带击退效果　-1 无效值　0 无击退效果　1有击退效果
     */
    private int pushBack;
    /**
     * swf
     *
     * 建筑动画
     */
    private String swf;
    /**
     * trigger_radius
     *
     * 触发范围　(地雷 隐身，有触发范围)
     */
    private long triggerRadius;
    /**
     * upgrade_units
     *
     * 是否可以升级士兵 0不可以升级士兵 1可以升级士兵
     */
    private int upgradeUnits;
    /**
     * width
     *
     * 占地范围宽
     */
    private int width;

    public int getAirTargets() {
        return airTargets;
    }

    public void setAirTargets(int airTargets) {
        this.airTargets = airTargets;
    }

    public long getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(long attackRange) {
        this.attackRange = attackRange;
    }

    public long getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(long attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getBuildResourceType() {
        return buildResourceType;
    }

    public void setBuildResourceType(int buildResourceType) {
        this.buildResourceType = buildResourceType;
    }

    public long getBuildTimeDate() {
        return buildTimeDate;
    }

    public void setBuildTimeDate(long buildTimeDate) {
        this.buildTimeDate = buildTimeDate;
    }

    public long getBuildTimeHour() {
        return buildTimeHour;
    }

    public void setBuildTimeHour(long buildTimeHour) {
        this.buildTimeHour = buildTimeHour;
    }

    public long getBuildTimeMinutes() {
        return buildTimeMinutes;
    }

    public void setBuildTimeMinutes(long buildTimeMinutes) {
        this.buildTimeMinutes = buildTimeMinutes;
    }

    public String getBuildingAnimation() {
        return buildingAnimation;
    }

    public void setBuildingAnimation(String buildingAnimation) {
        this.buildingAnimation = buildingAnimation;
    }

    public int getBuildingCrashH() {
        return buildingCrashH;
    }

    public void setBuildingCrashH(int buildingCrashH) {
        this.buildingCrashH = buildingCrashH;
    }

    public int getBuildingCrashW() {
        return buildingCrashW;
    }

    public void setBuildingCrashW(int buildingCrashW) {
        this.buildingCrashW = buildingCrashW;
    }

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public long getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(long buildingType) {
        this.buildingType = buildingType;
    }

    public long getDamageRadius() {
        return damageRadius;
    }

    public void setDamageRadius(long damageRadius) {
        this.damageRadius = damageRadius;
    }

    public int getGroundTargets() {
        return groundTargets;
    }

    public void setGroundTargets(int groundTargets) {
        this.groundTargets = groundTargets;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getIsBunker() {
        return isBunker;
    }

    public void setIsBunker(int isBunker) {
        this.isBunker = isBunker;
    }

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }

    public int getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(int isLocked) {
        this.isLocked = isLocked;
    }

    public int getIsSell() {
        return isSell;
    }

    public void setIsSell(int isSell) {
        this.isSell = isSell;
    }

    public long getMinAttackRange() {
        return minAttackRange;
    }

    public void setMinAttackRange(long minAttackRange) {
        this.minAttackRange = minAttackRange;
    }

    public int getProducesResourceType() {
        return producesResourceType;
    }

    public void setProducesResourceType(int producesResourceType) {
        this.producesResourceType = producesResourceType;
    }

    public int getPushBack() {
        return pushBack;
    }

    public void setPushBack(int pushBack) {
        this.pushBack = pushBack;
    }

    public String getSwf() {
        return swf;
    }

    public void setSwf(String swf) {
        this.swf = swf;
    }

    public long getTriggerRadius() {
        return triggerRadius;
    }

    public void setTriggerRadius(long triggerRadius) {
        this.triggerRadius = triggerRadius;
    }

    public int getUpgradeUnits() {
        return upgradeUnits;
    }

    public void setUpgradeUnits(int upgradeUnits) {
        this.upgradeUnits = upgradeUnits;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
