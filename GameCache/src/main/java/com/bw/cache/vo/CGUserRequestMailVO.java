package com.bw.cache.vo;

import java.io.*;
import java.sql.*;

public class CGUserRequestMailVO implements Serializable {

    private static final long serialVersionUID = 5986993701806251553L;
    private long id;
    private String sender;
    private String receiver;
    private int mailType;
    private Timestamp lastModify;
    private long buildingId;

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public void setReceiver(final String receiver) {
        this.receiver = receiver;
    }

    public int getMailType() {
        return this.mailType;
    }

    public void setMailType(final int mailType) {
        this.mailType = mailType;
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
        result = 31 * result + (int) (this.id ^ this.id >>> 32);
        result = 31 * result + ((this.lastModify == null) ? 0 : this.lastModify.hashCode());
        result = 31 * result + this.mailType;
        result = 31 * result + ((this.receiver == null) ? 0 : this.receiver.hashCode());
        result = 31 * result + ((this.sender == null) ? 0 : this.sender.hashCode());
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
        final CGUserRequestMailVO other = (CGUserRequestMailVO) obj;
        if (this.buildingId != other.buildingId) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.lastModify == null) {
            if (other.lastModify != null) {
                return false;
            }
        } else if (!this.lastModify.equals(other.lastModify)) {
            return false;
        }
        if (this.mailType != other.mailType) {
            return false;
        }
        if (this.receiver == null) {
            if (other.receiver != null) {
                return false;
            }
        } else if (!this.receiver.equals(other.receiver)) {
            return false;
        }
        if (this.sender == null) {
            if (other.sender != null) {
                return false;
            }
        } else if (!this.sender.equals(other.sender)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CGUserRequestMailVO [id=" + this.id + ", sender=" + this.sender + ", receiver=" + this.receiver + ", mailType=" + this.mailType + ", lastModify=" + this.lastModify + ", buildingId=" + this.buildingId + "]";
    }
}
