package com.bw.application.resourceManager;

/**
 * 配置管理类, 该对象使用Spring RMI注册为服务
 *
 * @author zhYou
 */
public interface ResManager {

    /**
     * 重新加载所有配置
     */
    public void reload();

    /**
     * 更新配置对象
     */
    public boolean updateConfig(Object configObj);
}
