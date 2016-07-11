package com.bw.cache.vo;

import java.io.*;

public class CGUserBuildingInfoVO implements Serializable {

    private static final long serialVersionUID = -5090669939033322969L;
    private int pointX;
    private int pointY;
    private long buildingUuid;
    private int buildingId;
    private int status;

    public int getPointX() {
        return this.pointX;
    }

    public void setPointX(final int pointX) {
        this.pointX = pointX;
    }

    public int getPointY() {
        return this.pointY;
    }

    public void setPointY(final int pointY) {
        this.pointY = pointY;
    }

    public long getBuildingUuid() {
        return this.buildingUuid;
    }

    public void setBuildingUuid(final long buildingUuid) {
        this.buildingUuid = buildingUuid;
    }

    public int getBuildingId() {
        return this.buildingId;
    }

    public void setBuildingId(final int buildingId) {
        this.buildingId = buildingId;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.buildingId;
        result = 31 * result + (int) (this.buildingUuid ^ this.buildingUuid >>> 32);
        result = 31 * result + this.pointX;
        result = 31 * result + this.pointY;
        result = 31 * result + this.status;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final CGUserBuildingInfoVO other = (CGUserBuildingInfoVO) obj;
        return this.buildingId == other.buildingId && this.buildingUuid == other.buildingUuid && this.pointX == other.pointX && this.pointY == other.pointY && this.status == other.status;
    }

    @Override
    public String toString() {
        return "CGUserBuildingInfoVO [pointX=" + this.pointX + ", pointY=" + this.pointY + ", buildingUuid=" + this.buildingUuid + ", buildingId=" + this.buildingId + ", status=" + this.status + "]";
    }
}
