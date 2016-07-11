package com.bw.download.config;

import com.commonSocket.net.config.BaseConfig;

/**
 * @author zhaoqingyou
 *
 */
public class AppConfig extends BaseConfig {

    public AppConfig() {
        super();
    }

    public int getAppId() {
        return Integer.parseInt(getConfig("current.appid"));
    }

    public String getVersion() {
        return getConfig("server.status.version");
    }
}
