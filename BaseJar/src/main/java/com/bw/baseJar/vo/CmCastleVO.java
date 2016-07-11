package com.bw.baseJar.vo;

import java.io.*;

public class CmCastleVO implements Serializable {

    private static final long serialVersionUID = -7482456624739203479L;
    private int id;
    private String castleName;
    private int castleLevel;
    private int unlockRoleLevel;
    private int unlockBuildingNumber;
    private int occupationLevel;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getCastleName() {
        return this.castleName;
    }

    public void setCastleName(final String castleName) {
        this.castleName = castleName;
    }

    public int getCastleLevel() {
        return this.castleLevel;
    }

    public void setCastleLevel(final int castleLevel) {
        this.castleLevel = castleLevel;
    }

    public int getUnlockRoleLevel() {
        return this.unlockRoleLevel;
    }

    public void setUnlockRoleLevel(final int unlockRoleLevel) {
        this.unlockRoleLevel = unlockRoleLevel;
    }

    public int getUnlockBuildingNumber() {
        return this.unlockBuildingNumber;
    }

    public void setUnlockBuildingNumber(final int unlockBuildingNumber) {
        this.unlockBuildingNumber = unlockBuildingNumber;
    }

    public int getOccupationLevel() {
        return this.occupationLevel;
    }

    public void setOccupationLevel(final int occupationLevel) {
        this.occupationLevel = occupationLevel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.castleLevel;
        result = 31 * result + ((this.castleName == null) ? 0 : this.castleName.hashCode());
        result = 31 * result + this.id;
        result = 31 * result + this.occupationLevel;
        result = 31 * result + this.unlockBuildingNumber;
        result = 31 * result + this.unlockRoleLevel;
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
        final CmCastleVO other = (CmCastleVO) obj;
        if (this.castleLevel != other.castleLevel) {
            return false;
        }
        if (this.castleName == null) {
            if (other.castleName != null) {
                return false;
            }
        } else if (!this.castleName.equals(other.castleName)) {
            return false;
        }
        return this.id == other.id && this.occupationLevel == other.occupationLevel && this.unlockBuildingNumber == other.unlockBuildingNumber && this.unlockRoleLevel == other.unlockRoleLevel;
    }
}
