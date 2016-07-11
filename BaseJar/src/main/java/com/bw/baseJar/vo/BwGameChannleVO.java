package com.bw.baseJar.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 游戏服务器列表
 */
public class BwGameChannleVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4814695857695978893L;
    /**
     * address
     *
     * 服务器对外暴露的IP地址和端口号
     */
    private String address;
    /**
     * channle_name
     *
     * 频道名称
     */
    private String channleName;
    /**
     * id
     *
     * 服务器ID列表
     */
    private int id;
    private int userCount;
    /**
     * max_user_count
     *
     * 该频道用户最大数量
     */
    private long maxUserCount;
    /**
     * service_interface
     *
     * rmi接口
     */
    private String serviceInterface;
    /**
     * service_url
     *
     * rmi url
     */
    private String serviceUrl;
    /**
     * status
     *
     * 服务器状态 0 不可以 1可用
     */
    private int status;
    // 隶属的区域ID
    private int areaId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChannlename() {
        return channleName;
    }

    public void setChannlename(String channleName) {
        this.channleName = channleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMaxusercount() {
        return maxUserCount;
    }

    public void setMaxusercount(long maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public String getServiceinterface() {
        return serviceInterface;
    }

    public void setServiceinterface(String serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public String getServiceurl() {
        return serviceUrl;
    }

    public void setServiceurl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

}
