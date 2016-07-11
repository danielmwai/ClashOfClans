package com.bw.baseJar.vo;

import java.io.*;

public class CityServerChannleVO implements Serializable {

    private static final long serialVersionUID = -7526013874162837595L;
    private long id;
    private String channleName;
    private String serviceUrl;
    private String serviceInterface;
    private int userCount;
    private int maxUserCount;
    private String address;

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getChannleName() {
        return this.channleName;
    }

    public void setChannleName(final String channleName) {
        this.channleName = channleName;
    }

    public String getServiceUrl() {
        return this.serviceUrl;
    }

    public void setServiceUrl(final String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServiceInterface() {
        return this.serviceInterface;
    }

    public void setServiceInterface(final String serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    public int getUserCount() {
        return this.userCount;
    }

    public void setUserCount(final int userCount) {
        this.userCount = userCount;
    }

    public int getMaxUserCount() {
        return this.maxUserCount;
    }

    public void setMaxUserCount(final int maxUserCount) {
        this.maxUserCount = maxUserCount;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + ((this.address == null) ? 0 : this.address.hashCode());
        result = 31 * result + ((this.channleName == null) ? 0 : this.channleName.hashCode());
        result = 31 * result + (int) (this.id ^ this.id >>> 32);
        result = 31 * result + this.maxUserCount;
        result = 31 * result + ((this.serviceInterface == null) ? 0 : this.serviceInterface.hashCode());
        result = 31 * result + ((this.serviceUrl == null) ? 0 : this.serviceUrl.hashCode());
        result = 31 * result + this.userCount;
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
        final CityServerChannleVO other = (CityServerChannleVO) obj;
        if (this.address == null) {
            if (other.address != null) {
                return false;
            }
        } else if (!this.address.equals(other.address)) {
            return false;
        }
        if (this.channleName == null) {
            if (other.channleName != null) {
                return false;
            }
        } else if (!this.channleName.equals(other.channleName)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.maxUserCount != other.maxUserCount) {
            return false;
        }
        if (this.serviceInterface == null) {
            if (other.serviceInterface != null) {
                return false;
            }
        } else if (!this.serviceInterface.equals(other.serviceInterface)) {
            return false;
        }
        if (this.serviceUrl == null) {
            if (other.serviceUrl != null) {
                return false;
            }
        } else if (!this.serviceUrl.equals(other.serviceUrl)) {
            return false;
        }
        return this.userCount == other.userCount;
    }
}
