package com.bw.baseJar.vo;

import java.io.*;
import java.util.*;

public class CmIslandDataVO implements Serializable {

    private static final long serialVersionUID = -372435514094208352L;
    private int id;
    private List<Integer> pointX;
    private List<Integer> pointY;
    private int moduleOccupy;
    private int islandLevel;
    private int roleLevel;
    private int perchase_type;
    private int goldPrice;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public List<Integer> getPointX() {
        return this.pointX;
    }

    public void setPointX(final List<Integer> pointX) {
        this.pointX = pointX;
    }

    public List<Integer> getPointY() {
        return this.pointY;
    }

    public void setPointY(final List<Integer> pointY) {
        this.pointY = pointY;
    }

    public int getModuleOccupy() {
        return this.moduleOccupy;
    }

    public void setModuleOccupy(final int moduleOccupy) {
        this.moduleOccupy = moduleOccupy;
    }

    public int getIslandLevel() {
        return this.islandLevel;
    }

    public void setIslandLevel(final int islandLevel) {
        this.islandLevel = islandLevel;
    }

    public int getRoleLevel() {
        return this.roleLevel;
    }

    public void setRoleLevel(final int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public int getPerchase_type() {
        return this.perchase_type;
    }

    public void setPerchase_type(final int perchase_type) {
        this.perchase_type = perchase_type;
    }

    public int getGoldPrice() {
        return this.goldPrice;
    }

    public void setGoldPrice(final int goldPrice) {
        this.goldPrice = goldPrice;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.goldPrice;
        result = 31 * result + this.id;
        result = 31 * result + this.islandLevel;
        result = 31 * result + this.moduleOccupy;
        result = 31 * result + this.perchase_type;
        result = 31 * result + ((this.pointX == null) ? 0 : this.pointX.hashCode());
        result = 31 * result + ((this.pointY == null) ? 0 : this.pointY.hashCode());
        result = 31 * result + this.roleLevel;
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
        final CmIslandDataVO other = (CmIslandDataVO) obj;
        if (this.goldPrice != other.goldPrice) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.islandLevel != other.islandLevel) {
            return false;
        }
        if (this.moduleOccupy != other.moduleOccupy) {
            return false;
        }
        if (this.perchase_type != other.perchase_type) {
            return false;
        }
        if (this.pointX == null) {
            if (other.pointX != null) {
                return false;
            }
        } else if (!this.pointX.equals(other.pointX)) {
            return false;
        }
        if (this.pointY == null) {
            if (other.pointY != null) {
                return false;
            }
        } else if (!this.pointY.equals(other.pointY)) {
            return false;
        }
        return this.roleLevel == other.roleLevel;
    }

    @Override
    public String toString() {
        return "CmIslandDataVO [id=" + this.id + ", pointX=" + this.pointX + ", pointY=" + this.pointY + ", moduleOccupy=" + this.moduleOccupy + ", islandLevel=" + this.islandLevel + ", roleLevel=" + this.roleLevel + ", perchase_type=" + this.perchase_type + ", goldPrice=" + this.goldPrice + "]";
    }
}
