package com.bw.download.config;

import com.commonSocket.net.config.BaseConfig;

public class PathConfig extends BaseConfig {

    public PathConfig() {
        super();
    }

    public String getImagePath() {
        return this.getConfig("imagePath");
    }

    public String getManiFestPath() {
        return this.getConfig("maniFestPath");
    }

    public String getLuaPath() {
        return this.getConfig("luaPath");
    }
}
