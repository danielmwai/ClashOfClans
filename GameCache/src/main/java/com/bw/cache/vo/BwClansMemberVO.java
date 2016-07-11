package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 工会成员表
 */
public class BwClansMemberVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5956742029339388812L;
    /**
     * clans_id
     *
     *
     */
    private long clansId;
    /**
     * member_mail_address
     *
     * 成员mail地址
     */
    private String memberMailAddress;
    /**
     * privilege_id
     *
     * 工会特权ID(例如:长老等)
     */
    private int privilegeId;

    public long getClansid() {
        return clansId;
    }

    public void setClansid(long clansId) {
        this.clansId = clansId;
    }

    public String getMembermailaddress() {
        return memberMailAddress;
    }

    public void setMembermailaddress(String memberMailAddress) {
        this.memberMailAddress = memberMailAddress;
    }

    public int getPrivilegeid() {
        return privilegeId;
    }

    public void setPrivilegeid(int privilegeId) {
        this.privilegeId = privilegeId;
    }
}
