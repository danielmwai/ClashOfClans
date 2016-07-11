package com.bw.cache.vo;

import java.io.*;

public class CGUserCropInfoVO implements Serializable {

    private static final long serialVersionUID = 5859603559486696465L;
    private int corpId;
    private int corpStatus;
    private long buildingUuid;

    public int getCorpId() {
        return this.corpId;
    }

    public void setCorpId(final int corpId) {
        this.corpId = corpId;
    }

    public int getCorpStatus() {
        return this.corpStatus;
    }

    public void setCorpStatus(final int corpStatus) {
        this.corpStatus = corpStatus;
    }

    public long getBuildingUuid() {
        return this.buildingUuid;
    }

    public void setBuildingUuid(final long buildingUuid) {
        this.buildingUuid = buildingUuid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + (int) (this.buildingUuid ^ this.buildingUuid >>> 32);
        result = 31 * result + this.corpId;
        result = 31 * result + this.corpStatus;
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
        final CGUserCropInfoVO other = (CGUserCropInfoVO) obj;
        return this.buildingUuid == other.buildingUuid && this.corpId == other.corpId && this.corpStatus == other.corpStatus;
    }

    @Override
    public String toString() {
        return "CGUserCropInfoVO [corpId=" + this.corpId + ", corpStatus=" + this.corpStatus + ", buildingUuid=" + this.buildingUuid + "]";
    }
}
