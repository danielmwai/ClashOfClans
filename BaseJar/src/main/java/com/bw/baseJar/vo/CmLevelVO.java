package com.bw.baseJar.vo;

import java.io.*;

public class CmLevelVO implements Serializable {

    private static final long serialVersionUID = 9014564133820218627L;
    private int id;
    private int userLevel;
    private int exp;
    private long total_exp;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getUserLevel() {
        return this.userLevel;
    }

    public void setUserLevel(final int userLevel) {
        this.userLevel = userLevel;
    }

    public int getExp() {
        return this.exp;
    }

    public void setExp(final int exp) {
        this.exp = exp;
    }

    public long getTotal_exp() {
        return this.total_exp;
    }

    public void setTotal_exp(final long total_exp) {
        this.total_exp = total_exp;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.exp;
        result = 31 * result + this.id;
        result = 31 * result + (int) (this.total_exp ^ this.total_exp >>> 32);
        result = 31 * result + this.userLevel;
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
        final CmLevelVO other = (CmLevelVO) obj;
        return this.exp == other.exp && this.id == other.id && this.total_exp == other.total_exp && this.userLevel == other.userLevel;
    }
}
