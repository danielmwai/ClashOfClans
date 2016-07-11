package com.bw.baseJar.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 * @creator:denny zhao 宝石兑换资源配置
 */
public class BwExchangeVO implements Serializable {

    /**
     * gem 花费宝石
     */
    private long gem;
    private long count;

    public long getGem() {
        return gem;
    }

    public void setGem(long gem) {
        this.gem = gem;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
