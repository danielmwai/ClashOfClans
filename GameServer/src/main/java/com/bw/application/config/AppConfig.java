package com.bw.application.config;

import com.commonSocket.net.config.BaseConfig;

public class AppConfig extends BaseConfig {

    public AppConfig() {
        super();
    }

    public int getAppId() {
        return Integer.parseInt(getConfig("bw.current.appid"));
    }

    public int getAreaId() {
        return Integer.parseInt(getConfig("bw.area.id"));
    }

    public byte getCreateRoleAble() {
        return Byte.parseByte(getConfig("server.status.createRoleAble"));
    }

    public int getMaxOnlineRole() {
        return Integer.parseInt(getConfig("server.status.maxOnlineRole"));
    }

    public int getMaxPVPGrade() {
        return Integer.parseInt(getConfig("server.status.maxPVPGrade"));
    }

    public String getVersion() {
        return getConfig("server.status.version");
    }

    public String getUpdateAddress() {
        return getConfig("server.status.updateAddress");
    }

    public int getStageOfPer() {
        return Integer.parseInt(getConfig("battle.mapping.stage.per"));
    }
}
