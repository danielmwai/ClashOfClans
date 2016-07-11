package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 财富表
 */
public class BwTreasureVO implements Serializable {

    /**
     * count
     *
     *
     */
    private long count;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * imagePath
     *
     *
     */
    private String imagepath;
    /**
     * name
     *
     *
     */
    private String name;
    /**
     * price
     *
     *
     */
    private long price;
    /**
     * price_type
     *
     * 0 美元 1 绿宝石
     */
    private int priceType;
    /**
     * treasure_type
     *
     * 0 金币 1药水，2绿宝石
     */
    private int treasureType;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

    public int getTreasureType() {
        return treasureType;
    }

    public void setTreasureType(int treasureType) {
        this.treasureType = treasureType;
    }
}
