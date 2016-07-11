package com.bw.cache.vo;

import java.io.Serializable;

/**
 * @Company: 博维远景
 *
 * @creator:denny zhao
 *
 *
 * 用户兵营(Barrack) 状态表
 */
public class BwBarrackVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7541740873872577628L;
    /**
     * end_time
     *
     * 开始生产兵或者实验室升级兵的结束时间( 如果是营地/工会 此字段就无用)
     */
    private String endTime;
    /**
     * id
     *
     *
     */
    private long id;
    /**
     * produce_count
     *
     * 要生产得数量(兵营)/对实验室来说是没用的
     */
    private int produceCount;
    /**
     * user_character_id
     *
     * 生产兵的ID，
     */
    private long userCharacterId;
    /**
     * user_map_data_id
     *
     * 用户建筑数据表的ID
     */
    private long userMapDataId;

    /**
     * 造兵开始时间
     *
     * @return
     */
    private String startTime;

    /**
     * 阵地满的状态
     *
     * @return
     */
    private int produceStatus;
    //邮箱地址
    private String mailAddress;
    //建兵的索引对了
    private int index;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getProduceStatus() {
        return produceStatus;
    }

    public void setProduceStatus(int produceStatus) {
        this.produceStatus = produceStatus;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getEndtime() {
        return endTime;
    }

    public void setEndtime(String endTime) {
        this.endTime = endTime;
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

    public long getUsercharacterid() {
        return userCharacterId;
    }

    public void setUsercharacterid(long userCharacterId) {
        this.userCharacterId = userCharacterId;
    }

    public long getUsermapdataid() {
        return userMapDataId;
    }

    public void setUsermapdataid(long userMapDataId) {
        this.userMapDataId = userMapDataId;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
