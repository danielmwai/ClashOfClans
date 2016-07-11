package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 大厅和其他建筑的关系表
 */
public class BwHallBuildsRelationVO implements Serializable {

    /**
     * air_defense
     *
     * 防空塔
     */
    private int airDefense;
    /**
     * alliance_castle
     *
     * 公会城保
     */
    private int allianceCastle;
    /**
     * archer_tower
     *
     * 箭塔
     */
    private int archerTower;
    /**
     * attack_cost
     *
     * PVP检索花费
     */
    private long attackCost;
    /**
     * barrack
     *
     * 兵营数量
     */
    private int barrack;
    /**
     * cannon
     *
     * 加农炮
     */
    private int cannon;
    /**
     * ejector
     *
     * 弹射地雷
     */
    private int ejector;
    /**
     * elixir_pump
     *
     * 水井数量
     */
    private int elixirPump;
    /**
     * elixir_storage
     *
     * 水罐数量
     */
    private int elixirStorage;
    /**
     * gold_mine
     *
     * 金矿数量
     */
    private int goldMine;
    /**
     * golden_storage
     *
     * 金库数量
     */
    private int goldenStorage;
    /**
     * hall_level
     *
     * 大厅等级
     */
    private long hallLevel;
    /**
     * laboratory
     *
     * 科技
     */
    private int laboratory;
    /**
     * mine0
     *
     * 普通地雷
     */
    private int mine;
    /**
     * mortar
     *
     * 巨炮
     */
    private int mortar;
    /**
     * spell_forge
     *
     * 魔法池
     */
    private int spellForge;
    /**
     * superbomb
     *
     *
     */
    private int superbomb;
    /**
     * tesla_tower
     *
     * 电塔
     */
    private int teslaTower;
    /**
     * troop_housing
     *
     * 营火数量
     */
    private int troopHousing;
    /**
     * wall
     *
     * 墙
     */
    private int wall;
    /**
     * wizard_tower
     *
     * 法师塔
     */
    private int wizardTower;
    /**
     * worker_building
     *
     * 工人
     */
    private int workerBuilding;
    // 掠夺等级参数1
    private float plunder_arg1;
    // //掠夺等级参数2
    private float plunder_arg2;

    public int getAirdefense() {
        return airDefense;
    }

    public void setAirdefense(int airDefense) {
        this.airDefense = airDefense;
    }

    public int getAlliancecastle() {
        return allianceCastle;
    }

    public void setAlliancecastle(int allianceCastle) {
        this.allianceCastle = allianceCastle;
    }

    public int getArchertower() {
        return archerTower;
    }

    public void setArchertower(int archerTower) {
        this.archerTower = archerTower;
    }

    public long getAttackcost() {
        return attackCost;
    }

    public void setAttackcost(long attackCost) {
        this.attackCost = attackCost;
    }

    public int getBarrack() {
        return barrack;
    }

    public void setBarrack(int barrack) {
        this.barrack = barrack;
    }

    public int getCannon() {
        return cannon;
    }

    public void setCannon(int cannon) {
        this.cannon = cannon;
    }

    public int getEjector() {
        return ejector;
    }

    public void setEjector(int ejector) {
        this.ejector = ejector;
    }

    public int getElixirpump() {
        return elixirPump;
    }

    public void setElixirpump(int elixirPump) {
        this.elixirPump = elixirPump;
    }

    public int getElixirstorage() {
        return elixirStorage;
    }

    public void setElixirstorage(int elixirStorage) {
        this.elixirStorage = elixirStorage;
    }

    public int getGoldmine() {
        return goldMine;
    }

    public void setGoldmine(int goldMine) {
        this.goldMine = goldMine;
    }

    public int getGoldenstorage() {
        return goldenStorage;
    }

    public void setGoldenstorage(int goldenStorage) {
        this.goldenStorage = goldenStorage;
    }

    public long getHalllevel() {
        return hallLevel;
    }

    public void setHalllevel(long hallLevel) {
        this.hallLevel = hallLevel;
    }

    public int getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(int laboratory) {
        this.laboratory = laboratory;
    }

    public int getMine() {
        return mine;
    }

    public void setMine(int mine) {
        this.mine = mine;
    }

    public int getMortar() {
        return mortar;
    }

    public void setMortar(int mortar) {
        this.mortar = mortar;
    }

    public int getSpellforge() {
        return spellForge;
    }

    public void setSpellforge(int spellForge) {
        this.spellForge = spellForge;
    }

    public int getSuperbomb() {
        return superbomb;
    }

    public void setSuperbomb(int superbomb) {
        this.superbomb = superbomb;
    }

    public int getTeslatower() {
        return teslaTower;
    }

    public void setTeslatower(int teslaTower) {
        this.teslaTower = teslaTower;
    }

    public int getTroophousing() {
        return troopHousing;
    }

    public void setTroophousing(int troopHousing) {
        this.troopHousing = troopHousing;
    }

    public int getWall() {
        return wall;
    }

    public void setWall(int wall) {
        this.wall = wall;
    }

    public int getWizardtower() {
        return wizardTower;
    }

    public void setWizardtower(int wizardTower) {
        this.wizardTower = wizardTower;
    }

    public int getWorkerbuilding() {
        return workerBuilding;
    }

    public void setWorkerbuilding(int workerBuilding) {
        this.workerBuilding = workerBuilding;
    }

    public float getPlunder_arg1() {
        return plunder_arg1;
    }

    public void setPlunder_arg1(float plunderArg1) {
        plunder_arg1 = plunderArg1;
    }

    public float getPlunder_arg2() {
        return plunder_arg2;
    }

    public void setPlunder_arg2(float plunderArg2) {
        plunder_arg2 = plunderArg2;
    }

}
