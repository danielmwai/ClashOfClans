/**
 * o(-"-)o
 * CopyRight(C) by chma
 * QQ:402879660
 * MSN:mycoolmc2008@hotmail.com
 * Email:mycoolmc@gmail.com
 */
package com.bw.application.config;

import com.commonSocket.net.config.BaseConfig;

public class PathConfig extends BaseConfig {

    public PathConfig() {
        super();
    }

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
