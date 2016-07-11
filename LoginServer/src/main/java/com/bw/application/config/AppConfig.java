package com.bw.application.config;

import com.commonSocket.net.config.*;

public class AppConfig extends BaseConfig {

    public int getAppId() {
        return Integer.parseInt(this.getConfig("current.appid"));
    }

    public byte getCreateRoleAble() {
        return Byte.parseByte(this.getConfig("server.status.createRoleAble"));
    }

    public int getMaxOnlineRole() {
        return Integer.parseInt(this.getConfig("server.status.maxOnlineRole"));
    }

    public int getMaxRoleLevel() {
        return Integer.parseInt(this.getConfig("server.status.maxRoleLevel"));
    }

    public String getVersion() {
        return this.getConfig("server.status.version");
    }

    public String getUpdateAddress() {
        return this.getConfig("server.status.updateAddress");
    }
}
