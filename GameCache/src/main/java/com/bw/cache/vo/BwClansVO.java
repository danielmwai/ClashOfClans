package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 工会表
 */
public class BwClansVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7456237376987105521L;
    /**
     * clans_name
     *
     *
     */
    private String clansName;
    /**
     * clans_owner
     *
     *
     */
    private String clansOwner;
    /**
     * id
     *
     *
     */
    private long id;

    public String getClansname() {
        return clansName;
    }

    public void setClansname(String clansName) {
        this.clansName = clansName;
    }

    public String getClansowner() {
        return clansOwner;
    }

    public void setClansowner(String clansOwner) {
        this.clansOwner = clansOwner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
