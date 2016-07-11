package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 建筑等级表
 */
public class BwBuildingPropertiesLevelVO implements Serializable {

    /**
     * build_cost_count
     *
     * 建造消耗的资源数量
     */
    private long buildCostCount;
    /**
     * build_export_name
     *
     * 建筑升级对应的图片
     */
    private String buildExportName;
    /**
     * build_level
     *
     * 建筑等级
     */
    private int buildLevel;
    /**
     * build_time_date
     *
     * 建筑时间-日期
     */
    private long buildTimeDate;
    /**
     * build_time_hour
     *
     *
     */
    private long buildTimeHour;
    /**
     * build_time_minutes
     *
     * 建筑时间--分钟
     */
    private long buildTimeMinutes;
    /**
     * building_id
     *
     * 建筑ID
     */
    private long buildingId;
    /**
     * bw_characters_properties_level_id
     *
     * 防御塔上站着的士兵id(包含等级的)　-1为无效字段
     */
    private long bwCharactersPropertiesLevelId;
    /**
     * characters_count
     *
     * 防御塔上站得士兵数量 -1为无效值
     */
    private String charactersCount;
    /**
     * damage
     *
     * 攻击力
     */
    private long damage;
    /**
     * destruction_XP
     *
     * 破坏后获取的经验
     */
    private long destructionXp;
    /**
     * export_name_triggered
     *
     * 电磁塔触发器名称
     */
    private String exportNameTriggered;
    /**
     * hit_point
     *
     * 生命值
     */
    private long hitPoint;
    /**
     * houseing_space
     *
     * 提供存兵的人口数
     */
    private long houseingSpace;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * max_stored_elixir
     *
     * 存储药水上限
     */
    private long maxStoredElixir;
    /**
     * max_stored_gold
     *
     * 存储金钱上线
     */
    private long maxStoredGold;
    /**
     * produce_resource_per_hour
     *
     * 每小时生成资源的数量
     */
    private long produceResourcePerHour;
    /**
     * projectile_name
     *
     * 对应的子弹类型(文件名字)
     */
    private String projectileName;
    /**
     * regen_time
     *
     * 被拆除后 恢复需要的时间 秒为单位
     */
    private int regenTime;
    /**
     * resource_max
     *
     * 生产后存储资源的上限
     */
    private long resourceMax;
    /**
     * town_hall_level
     *
     * 允许制造该建筑的主基地等级
     */
    private long townHallLevel;
    /**
     * unit_production
     *
     * 兵营中最多可以放多少人口
     */
    private long unitProduction;

    public long getBuildcostcount() {
        return buildCostCount;
    }

    public void setBuildcostcount(long buildCostCount) {
        this.buildCostCount = buildCostCount;
    }

    public String getBuildexportname() {
        return buildExportName;
    }

    public void setBuildexportname(String buildExportName) {
        this.buildExportName = buildExportName;
    }

    public int getBuildlevel() {
        return buildLevel;
    }

    public void setBuildlevel(int buildLevel) {
        this.buildLevel = buildLevel;
    }

    public long getBuildtimedate() {
        return buildTimeDate;
    }

    public void setBuildtimedate(long buildTimeDate) {
        this.buildTimeDate = buildTimeDate;
    }

    public long getBuildtimehour() {
        return buildTimeHour;
    }

    public void setBuildtimehour(long buildTimeHour) {
        this.buildTimeHour = buildTimeHour;
    }

    public long getBuildtimeminutes() {
        return buildTimeMinutes;
    }

    public void setBuildtimeminutes(long buildTimeMinutes) {
        this.buildTimeMinutes = buildTimeMinutes;
    }

    public long getBuildingid() {
        return buildingId;
    }

    public void setBuildingid(long buildingId) {
        this.buildingId = buildingId;
    }

    public long getBwcharacterspropertieslevelid() {
        return bwCharactersPropertiesLevelId;
    }

    public void setBwcharacterspropertieslevelid(long bwCharactersPropertiesLevelId) {
        this.bwCharactersPropertiesLevelId = bwCharactersPropertiesLevelId;
    }

    public String getCharacterscount() {
        return charactersCount;
    }

    public void setCharacterscount(String charactersCount) {
        this.charactersCount = charactersCount;
    }

    public long getDamage() {
        return damage;
    }

    public void setDamage(long damage) {
        this.damage = damage;
    }

    public long getDestructionxp() {
        return destructionXp;
    }

    public void setDestructionxp(long destructionXp) {
        this.destructionXp = destructionXp;
    }

    public String getExportnametriggered() {
        return exportNameTriggered;
    }

    public void setExportnametriggered(String exportNameTriggered) {
        this.exportNameTriggered = exportNameTriggered;
    }

    public long getHitpoint() {
        return hitPoint;
    }

    public void setHitpoint(long hitPoint) {
        this.hitPoint = hitPoint;
    }

    public long getHouseingspace() {
        return houseingSpace;
    }

    public void setHouseingspace(long houseingSpace) {
        this.houseingSpace = houseingSpace;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMaxstoredelixir() {
        return maxStoredElixir;
    }

    public void setMaxstoredelixir(long maxStoredElixir) {
        this.maxStoredElixir = maxStoredElixir;
    }

    public long getMaxstoredgold() {
        return maxStoredGold;
    }

    public void setMaxstoredgold(long maxStoredGold) {
        this.maxStoredGold = maxStoredGold;
    }

    public long getProduceresourceperhour() {
        return produceResourcePerHour;
    }

    public void setProduceresourceperhour(long produceResourcePerHour) {
        this.produceResourcePerHour = produceResourcePerHour;
    }

    public String getProjectilename() {
        return projectileName;
    }

    public void setProjectilename(String projectileName) {
        this.projectileName = projectileName;
    }

    public int getRegentime() {
        return regenTime;
    }

    public void setRegentime(int regenTime) {
        this.regenTime = regenTime;
    }

    public long getResourcemax() {
        return resourceMax;
    }

    public void setResourcemax(long resourceMax) {
        this.resourceMax = resourceMax;
    }

    public long getTownhalllevel() {
        return townHallLevel;
    }

    public void setTownhalllevel(long townHallLevel) {
        this.townHallLevel = townHallLevel;
    }

    public long getUnitproduction() {
        return unitProduction;
    }

    public void setUnitproduction(long unitProduction) {
        this.unitProduction = unitProduction;
    }
}
