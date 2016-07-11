package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 用户初始化数据
 */
public class BwInitUserVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7878309639989159847L;
    /**
     * elixir_count
     *
     *
     */
    private int elixirCount;
    /**
     * exp
     *
     *
     */
    private int exp;
    /**
     * gem_count
     *
     *
     */
    private int gemCount;
    /**
     * golden_count
     *
     *
     */
    private int goldenCount;
    /**
     * id
     *
     *
     */
    private long id;

    private int oneMinuteCost;
    private int oneHourCost;
    private int oneDayCost;
    private int oneWeekCost;

    public int getElixirCount() {
        return elixirCount;
    }

    public void setElixirCount(int elixirCount) {
        this.elixirCount = elixirCount;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getGemCount() {
        return gemCount;
    }

    public void setGemCount(int gemCount) {
        this.gemCount = gemCount;
    }

    public int getGoldenCount() {
        return goldenCount;
    }

    public void setGoldenCount(int goldenCount) {
        this.goldenCount = goldenCount;
    }

    public int getOneMinuteCost() {
        return oneMinuteCost;
    }

    public void setOneMinuteCost(int oneMinuteCost) {
        this.oneMinuteCost = oneMinuteCost;
    }

    public int getOneHourCost() {
        return oneHourCost;
    }

    public void setOneHourCost(int oneHourCost) {
        this.oneHourCost = oneHourCost;
    }

    public int getOneDayCost() {
        return oneDayCost;
    }

    public void setOneDayCost(int oneDayCost) {
        this.oneDayCost = oneDayCost;
    }

    public int getOneWeekCost() {
        return oneWeekCost;
    }

    public void setOneWeekCost(int oneWeekCost) {
        this.oneWeekCost = oneWeekCost;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
