package com.bw.cache.vo;

import java.io.Serializable;
import java.util.Date;

public class BwBlacklistVO implements Serializable {

    private long id; // 编号
    private String boweiId; // 博为编号
    private String operator; // 操作人
    private Date startTime; // 开始时间
    private Date endTime; // 结束时间
    private String reason; // 封号原因

    /**
     * 判断该用户是否有效
     *
     * @return true, 该用户有效
     */
    public boolean deny() {
        long cur = System.currentTimeMillis();
        return cur > startTime.getTime() && cur < endTime.getTime();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBoweiId() {
        return boweiId;
    }

    public void setBoweiId(String boweiId) {
        this.boweiId = boweiId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BwBlacklistVO [id=");
        builder.append(id);
        builder.append(", boweiId=");
        builder.append(boweiId);
        builder.append(", operator=");
        builder.append(operator);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", endTime=");
        builder.append(endTime);
        builder.append(", reason=");
        builder.append(reason);
        builder.append("]");
        return builder.toString();
    }
}
