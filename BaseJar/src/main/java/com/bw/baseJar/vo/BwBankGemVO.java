package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 宝石价格
 */
public class BwBankGemVO implements Serializable {

    /**
     * gem_count
     *
     *
     */
    private long gemCount;
    /**
     * price
     *
     *
     */
    private long price;
    /**
     * product_id
     *
     *
     */
    private String productId;
    /**
     * status
     *
     * 0正常 1无效
     */
    private int status;

    public long getGemCount() {
        return gemCount;
    }

    public void setGemCount(long gemCount) {
        this.gemCount = gemCount;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
