package com.bw.application.config;

import com.commonSocket.net.config.*;

public class PathConfig extends BaseConfig {

    public String getItemIconResPath() {
        return this.getConfig("itemIconResPath");
    }

    public String getItemShowResPath() {
        return this.getConfig("itemShowResPath");
    }

    public String getAvatarResPath() {
        return this.getConfig("avatarResPath");
    }

    public String getItemFilePath() {
        return this.getConfig("ItemFilePath");
    }

    public String getManiFestPath() {
        return this.getConfig("maniFestPath");
    }
}
