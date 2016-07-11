package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 农民数量价格表 ,农民的数量每增加一个，农民的价格就会更贵
 */
public class BwWorkerVO implements Serializable {

    /**
     * id
     *
     *
     */
    private long id;
    /**
     * name
     *
     *
     */
    private String name;
    /**
     * next_count
     *
     *
     */
    private int nextCount;
    /**
     * next_price
     *
     *
     */
    private int nextPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNextCount() {
        return nextCount;
    }

    public void setNextCount(int nextCount) {
        this.nextCount = nextCount;
    }

    public int getNextPrice() {
        return nextPrice;
    }

    public void setNextPrice(int nextPrice) {
        this.nextPrice = nextPrice;
    }
}
