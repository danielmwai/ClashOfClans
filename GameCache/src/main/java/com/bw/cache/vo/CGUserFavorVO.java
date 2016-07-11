package com.bw.cache.vo;

import java.io.*;
import java.sql.*;

public class CGUserFavorVO implements Serializable {

    private static final long serialVersionUID = 4369441189004993040L;
    private String mailAddress;
    private Timestamp lastModify;
    private String favorMailAddress;

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

    public String getFavorMailAddress() {
        return this.favorMailAddress;
    }

    public void setFavorMailAddress(final String favorMailAddress) {
        this.favorMailAddress = favorMailAddress;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.favorMailAddress == null) ? 0 : this.favorMailAddress.hashCode());
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
        final CGUserFavorVO other = (CGUserFavorVO) obj;
        if (this.favorMailAddress == null) {
            if (other.favorMailAddress != null) {
                return false;
            }
        } else if (!this.favorMailAddress.equals(other.favorMailAddress)) {
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
        return "CGUserFavorVO [mailAddress=" + this.mailAddress + ", lastModify=" + this.lastModify + ", favorMailAddress=" + this.favorMailAddress + "]";
    }
}
