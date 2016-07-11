package com.bw.cache.vo;

import java.io.*;
import java.sql.*;

public class CGUserFavorTimeVO implements Serializable {

    private static final long serialVersionUID = 648998054110971354L;
    private String mailAddress;
    private int favorTime;
    private Timestamp lastModify;

    public String getMailAddress() {
        return this.mailAddress;
    }

    public void setMailAddress(final String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public int getFavorTime() {
        return this.favorTime;
    }

    public void setFavorTime(final int favorTime) {
        this.favorTime = favorTime;
    }

    public Timestamp getLastModify() {
        return this.lastModify;
    }

    public void setLastModify(final Timestamp lastModify) {
        this.lastModify = lastModify;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.favorTime;
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
        final CGUserFavorTimeVO other = (CGUserFavorTimeVO) obj;
        if (this.favorTime != other.favorTime) {
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
        return "CGUserFavorTimeVO [mailAddress=" + this.mailAddress + ", favorTime=" + this.favorTime + ", lastModify=" + this.lastModify + "]";
    }
}
