package com.bw.cache.vo;

import java.io.Serializable;
import java.util.Date;

public class CGUserCurrencyVO implements Serializable {

    private static final long serialVersionUID = 5749108985699780348L;

    private long id;

    private String mailAddress;

    private int tokenCoin;

    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public int getTokenCoin() {
        return tokenCoin;
    }

    public void setTokenCoin(int tokenCoin) {
        this.tokenCoin = tokenCoin;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((mailAddress == null) ? 0 : mailAddress.hashCode());
        result = prime * result + (int) (tokenCoin ^ (tokenCoin >>> 32));
        result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CGUserCurrencyVO other = (CGUserCurrencyVO) obj;
        if (id != other.id) {
            return false;
        }
        if (mailAddress == null) {
            if (other.mailAddress != null) {
                return false;
            }
        } else if (!mailAddress.equals(other.mailAddress)) {
            return false;
        }
        if (tokenCoin != other.tokenCoin) {
            return false;
        }
        if (updateTime == null) {
            if (other.updateTime != null) {
                return false;
            }
        } else if (!updateTime.equals(other.updateTime)) {
            return false;
        }
        return true;
    }

}
