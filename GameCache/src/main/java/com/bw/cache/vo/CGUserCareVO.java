package com.bw.cache.vo;

import java.io.*;
import java.sql.*;

public class CGUserCareVO implements Serializable {

    private static final long serialVersionUID = -6088754485943840532L;
    private String mailAddress;
    private Timestamp lastModify;
    private long buildingId;

    public String getMailAddress() {
        return this.mailAddress;
    }

    public void setMailAddress(final String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public Timestamp getLastModify() {
        return this.lastModify;
    }

    public void setLastModify(final Timestamp lastModify) {
        this.lastModify = lastModify;
    }

    public long getBuildingId() {
        return this.buildingId;
    }

    public void setBuildingId(final long buildingId) {
        this.buildingId = buildingId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + (int) (this.buildingId ^ this.buildingId >>> 32);
        result = 31 * result + ((this.lastModify == null) ? 0 : this.lastModify.hashCode());
        result = 31 * result + ((this.mailAddress == null) ? 0 : this.mailAddress.hashCode());
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
        final CGUserCareVO other = (CGUserCareVO) obj;
        if (this.buildingId != other.buildingId) {
            return false;
        }
        if (this.lastModify == null) {
            if (other.lastModify != null) {
                return false;
            }
        } else if (!this.lastModify.equals(other.lastModify)) {
            return false;
        }
        if (this.mailAddress == null) {
            if (other.mailAddress != null) {
                return false;
            }
        } else if (!this.mailAddress.equals(other.mailAddress)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CGUserCareVO [mailAddress=" + this.mailAddress + ", lastModify=" + this.lastModify + ", buildingId=" + this.buildingId + "]";
    }
}
