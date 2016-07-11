package com.bw.cache.vo;

import java.io.*;

public class CGFriendRelationVO implements Serializable {

    private static final long serialVersionUID = -2388654462379585686L;
    private String userMailAddress;
    private String friendMailAddress;

    public String getUserMailAddress() {
        return this.userMailAddress;
    }

    public void setUserMailAddress(final String userMailAddress) {
        this.userMailAddress = userMailAddress;
    }

    public String getFriendMailAddress() {
        return this.friendMailAddress;
    }

    public void setFriendMailAddress(final String friendMailAddress) {
        this.friendMailAddress = friendMailAddress;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.friendMailAddress == null) ? 0 : this.friendMailAddress.hashCode());
        result = 31 * result + ((this.userMailAddress == null) ? 0 : this.userMailAddress.hashCode());
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
        final CGFriendRelationVO other = (CGFriendRelationVO) obj;
        if (this.friendMailAddress == null) {
            if (other.friendMailAddress != null) {
                return false;
            }
        } else if (!this.friendMailAddress.equals(other.friendMailAddress)) {
            return false;
        }
        if (this.userMailAddress == null) {
            if (other.userMailAddress != null) {
                return false;
            }
        } else if (!this.userMailAddress.equals(other.userMailAddress)) {
            return false;
        }
        return true;
    }
}
