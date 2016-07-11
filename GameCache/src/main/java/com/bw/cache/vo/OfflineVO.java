package com.bw.cache.vo;

import java.io.Serializable;

public class OfflineVO implements Serializable {
    //mai地址

    private String mailAddress;
    //用户等级
    private int user_level;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public int getUser_level() {
        return user_level;
    }

    public void setUser_level(int userLevel) {
        user_level = userLevel;
    }

}
