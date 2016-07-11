package com.bw.application.manager.impl;

import java.util.Arrays;

import com.bw.application.config.AppConfig;
import com.bw.dao.CommonDAO;
import com.commonSocket.net.onlinemanage.support.DefaultOnlineManage;

/**
 * @author denny zhao 操作用户在线数量
 */
public class ServerOnlineManage extends DefaultOnlineManage {

    public CommonDAO commonCacheDAOImpl;
    private AppConfig appConfig;

    public void addOnlineRole(String roleId, Object role) {
        this.onlineMap.put(roleId, role);
        String key = appConfig.getAreaId() + "_" + appConfig.getAppId();
        commonCacheDAOImpl.saveServerUserList(Arrays.asList(this.onlineIdSet()), key);
    }

    public CommonDAO getCommonCacheDAOImpl() {
        return commonCacheDAOImpl;
    }

    public void setCommonCacheDAOImpl(CommonDAO commonCacheDAOImpl) {
        this.commonCacheDAOImpl = commonCacheDAOImpl;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

}
