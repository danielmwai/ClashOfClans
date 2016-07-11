package com.bw.cache.vo;

import java.io.*;

public class CGUserCheat implements Serializable {

    private static final long serialVersionUID = 3595297027380779152L;
    private String mailAddress;
    private int errorType;

    public String getMailAddress() {
        return this.mailAddress;
    }

    public void setMailAddress(final String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public int getErrorType() {
        return this.errorType;
    }

    public void setErrorType(final int errorType) {
        this.errorType = errorType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.errorType;
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
        final CGUserCheat other = (CGUserCheat) obj;
        if (this.errorType != other.errorType) {
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
}
