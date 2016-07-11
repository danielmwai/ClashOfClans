package com.bw.baseJar.vo;

import java.io.*;

public class CmCropsVO implements Serializable {

    private static final long serialVersionUID = -1274656509174974099L;
    private int id;
    private String name;
    private int goldPrice;
    private int perchaseType;
    private int incomeExp;
    private int incomeGold;
    private long plantCd;
    private long incomeCd;
    private long witherCd;
    private boolean isCare;
    private String plantActionId;
    private int unlockRoleLevel;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getGoldPrice() {
        return this.goldPrice;
    }

    public void setGoldPrice(final int goldPrice) {
        this.goldPrice = goldPrice;
    }

    public int getPerchaseType() {
        return this.perchaseType;
    }

    public void setPerchaseType(final int perchaseType) {
        this.perchaseType = perchaseType;
    }

    public int getIncomeExp() {
        return this.incomeExp;
    }

    public void setIncomeExp(final int incomeExp) {
        this.incomeExp = incomeExp;
    }

    public int getIncomeGold() {
        return this.incomeGold;
    }

    public void setIncomeGold(final int incomeGold) {
        this.incomeGold = incomeGold;
    }

    public long getPlantCd() {
        return this.plantCd;
    }

    public void setPlantCd(final long plantCd) {
        this.plantCd = plantCd;
    }

    public long getIncomeCd() {
        return this.incomeCd;
    }

    public void setIncomeCd(final long incomeCd) {
        this.incomeCd = incomeCd;
    }

    public long getWitherCd() {
        return this.witherCd;
    }

    public void setWitherCd(final long witherCd) {
        this.witherCd = witherCd;
    }

    public boolean isCare() {
        return this.isCare;
    }

    public void setCare(final boolean isCare) {
        this.isCare = isCare;
    }

    public String getPlantActionId() {
        return this.plantActionId;
    }

    public void setPlantActionId(final String plantActionId) {
        this.plantActionId = plantActionId;
    }

    public int getUnlockRoleLevel() {
        return this.unlockRoleLevel;
    }

    public void setUnlockRoleLevel(final int unlockRoleLevel) {
        this.unlockRoleLevel = unlockRoleLevel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.goldPrice;
        result = 31 * result + this.id;
        result = 31 * result + (int) (this.incomeCd ^ this.incomeCd >>> 32);
        result = 31 * result + this.incomeExp;
        result = 31 * result + this.incomeGold;
        result = 31 * result + (this.isCare ? 1231 : 1237);
        result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = 31 * result + this.perchaseType;
        result = 31 * result + ((this.plantActionId == null) ? 0 : this.plantActionId.hashCode());
        result = 31 * result + (int) (this.plantCd ^ this.plantCd >>> 32);
        result = 31 * result + this.unlockRoleLevel;
        result = 31 * result + (int) (this.witherCd ^ this.witherCd >>> 32);
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
        final CmCropsVO other = (CmCropsVO) obj;
        if (this.goldPrice != other.goldPrice) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (this.incomeCd != other.incomeCd) {
            return false;
        }
        if (this.incomeExp != other.incomeExp) {
            return false;
        }
        if (this.incomeGold != other.incomeGold) {
            return false;
        }
        if (this.isCare != other.isCare) {
            return false;
        }
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        if (this.perchaseType != other.perchaseType) {
            return false;
        }
        if (this.plantActionId == null) {
            if (other.plantActionId != null) {
                return false;
            }
        } else if (!this.plantActionId.equals(other.plantActionId)) {
            return false;
        }
        return this.plantCd == other.plantCd && this.unlockRoleLevel == other.unlockRoleLevel && this.witherCd == other.witherCd;
    }

    @Override
    public String toString() {
        return "CmCropsVO [id=" + this.id + ", name=" + this.name + ", goldPrice=" + this.goldPrice + ", perchaseType=" + this.perchaseType + ", incomeExp=" + this.incomeExp + ", incomeGold=" + this.incomeGold + ", plantCd=" + this.plantCd + ", incomeCd=" + this.incomeCd + ", witherCd=" + this.witherCd + ", isCare=" + this.isCare + ", plantActionId=" + this.plantActionId + ", unlockRoleLevel=" + this.unlockRoleLevel + "]";
    }
}
