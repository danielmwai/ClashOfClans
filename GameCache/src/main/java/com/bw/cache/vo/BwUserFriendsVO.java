package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 用户好友表
 */
public class BwUserFriendsVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1802113901598596465L;
    /**
     * friend_address
     *
     *
     */
    private String friendAddress;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * mail_address
     *
     *
     */
    private String mailAddress;

    public String getFriendaddress() {
        return friendAddress;
    }

    public void setFriendaddress(String friendAddress) {
        this.friendAddress = friendAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMailaddress() {
        return mailAddress;
    }

    public void setMailaddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
