package com.bw.baseJar.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 下载服务器列表
 */
public class BwFileServerVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -753771529032136402L;
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
     * rul
     *
     *
     */
    private String rul;
    /**
     * status
     *
     * 0不可用 1可用
     */
    private int status;

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

    public String getRul() {
        return rul;
    }

    public void setRul(String rul) {
        this.rul = rul;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
