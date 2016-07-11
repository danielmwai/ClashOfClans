package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 工会成员请求兵表
 */
public class BwClansCharacterRequestVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7408341165840857072L;
    /**
     * clans_id
     *
     * 工会id
     */
    private long clansId;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * produce_count
     *
     * 被放入的士兵数量
     */
    private int produceCount;
    /**
     * reply_mail_address
     *
     *
     */
    private String replyMailAddress;
    /**
     * request_mail_address
     *
     * 用户建筑数据表的ID
     */
    private long requestMailAddress;
    /**
     * request_time
     *
     * 请求的时间
     */
    private String requestTime;
    /**
     * user_character_id
     *
     * 用户士兵ID
     */
    private long userCharacterId;

    public long getClansid() {
        return clansId;
    }

    public void setClansid(long clansId) {
        this.clansId = clansId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProducecount() {
        return produceCount;
    }

    public void setProducecount(int produceCount) {
        this.produceCount = produceCount;
    }

    public String getReplymailaddress() {
        return replyMailAddress;
    }

    public void setReplymailaddress(String replyMailAddress) {
        this.replyMailAddress = replyMailAddress;
    }

    public long getRequestmailaddress() {
        return requestMailAddress;
    }

    public void setRequestmailaddress(long requestMailAddress) {
        this.requestMailAddress = requestMailAddress;
    }

    public String getRequesttime() {
        return requestTime;
    }

    public void setRequesttime(String requestTime) {
        this.requestTime = requestTime;
    }

    public long getUsercharacterid() {
        return userCharacterId;
    }

    public void setUsercharacterid(long userCharacterId) {
        this.userCharacterId = userCharacterId;
    }
}
