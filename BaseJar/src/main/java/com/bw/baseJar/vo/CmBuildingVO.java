package com.bw.baseJar.vo;

import java.io.*;

public class CmBuildingVO implements Serializable {

    private static final long serialVersionUID = -5243311434436158901L;
    private long buildingId;
    private String buildingName;
    private int perchaseType;
    private int unlockCastleLevel;
    private int buildTerrain;
    private int floorGrids;
    private int goldPrice;
    private int occupationProperties;
    private boolean isCanUpgrade;
    private long nextLevelId;
    private long incomeCd;
    private long buildCd;
    private int incomeExp;
    private int incomeGold;
    private boolean isFavor;
    private int buildingType;
    private String buildingDescribe;
    private int sellPrice;
    private int unlockRoleLevel;
    private int actionId;
    private int pointWidth;
    private int pointHeight;

    public long getBuildingId() {
        return this.buildingId;
    }

    public void setBuildingId(final long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return this.buildingName;
    }

    public void setBuildingName(final String buildingName) {
        this.buildingName = buildingName;
    }

    public int getPerchaseType() {
        return this.perchaseType;
    }

    public void setPerchaseType(final int perchaseType) {
        this.perchaseType = perchaseType;
    }

    public int getUnlockCastleLevel() {
        return this.unlockCastleLevel;
    }

    public void setUnlockCastleLevel(final int unlockCastleLevel) {
        this.unlockCastleLevel = unlockCastleLevel;
    }

    public int getBuildTerrain() {
        return this.buildTerrain;
    }

    public void setBuildTerrain(final int buildTerrain) {
        this.buildTerrain = buildTerrain;
    }

    public int getFloorGrids() {
        return this.floorGrids;
    }

    public void setFloorGrids(final int floorGrids) {
        this.floorGrids = floorGrids;
    }

    public int getGoldPrice() {
        return this.goldPrice;
    }

    public void setGoldPrice(final int goldPrice) {
        this.goldPrice = goldPrice;
    }

    public int getOccupationProperties() {
        return this.occupationProperties;
    }

    public void setOccupationProperties(final int occupationProperties) {
        this.occupationProperties = occupationProperties;
    }

    public boolean isCanUpgrade() {
        return this.isCanUpgrade;
    }

    public void setCanUpgrade(final boolean isCanUpgrade) {
        this.isCanUpgrade = isCanUpgrade;
    }

    public long getNextLevelId() {
        return this.nextLevelId;
    }

    public void setNextLevelId(final long nextLevelId) {
        this.nextLevelId = nextLevelId;
    }

    public long getIncomeCd() {
        return this.incomeCd;
    }

    public void setIncomeCd(final long incomeCd) {
        this.incomeCd = incomeCd;
    }

    public long getBuildCd() {
        return this.buildCd;
    }

    public void setBuildCd(final long buildCd) {
        this.buildCd = buildCd;
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

    public boolean isFavor() {
        return this.isFavor;
    }

    public void setFavor(final boolean isFavor) {
        this.isFavor = isFavor;
    }

    public int getBuildingType() {
        return this.buildingType;
    }

    public void setBuildingType(final int buildingType) {
        this.buildingType = buildingType;
    }

    public String getBuildingDescribe() {
        return this.buildingDescribe;
    }

    public void setBuildingDescribe(final String buildingDescribe) {
        this.buildingDescribe = buildingDescribe;
    }

    public int getSellPrice() {
        return this.sellPrice;
    }

    public void setSellPrice(final int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getUnlockRoleLevel() {
        return this.unlockRoleLevel;
    }

    public void setUnlockRoleLevel(final int unlockRoleLevel) {
        this.unlockRoleLevel = unlockRoleLevel;
    }

    public int getActionId() {
        return this.actionId;
    }

    public void setActionId(final int actionId) {
        this.actionId = actionId;
    }

    public int getPointWidth() {
        return this.pointWidth;
    }

    public void setPointWidth(final int pointWidth) {
        this.pointWidth = pointWidth;
    }

    public int getPointHeight() {
        return this.pointHeight;
    }

    public void setPointHeight(final int pointHeight) {
        this.pointHeight = pointHeight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.actionId;
        result = 31 * result + (int) (this.buildCd ^ this.buildCd >>> 32);
        result = 31 * result + this.buildTerrain;
        result = 31 * result + ((this.buildingDescribe == null) ? 0 : this.buildingDescribe.hashCode());
        result = 31 * result + (int) (this.buildingId ^ this.buildingId >>> 32);
        result = 31 * result + ((this.buildingName == null) ? 0 : this.buildingName.hashCode());
        result = 31 * result + this.buildingType;
        result = 31 * result + this.floorGrids;
        result = 31 * result + this.goldPrice;
        result = 31 * result + (int) (this.incomeCd ^ this.incomeCd >>> 32);
        result = 31 * result + this.incomeExp;
        result = 31 * result + this.incomeGold;
        result = 31 * result + (this.isCanUpgrade ? 1231 : 1237);
        result = 31 * result + (this.isFavor ? 1231 : 1237);
        result = 31 * result + (int) (this.nextLevelId ^ this.nextLevelId >>> 32);
        result = 31 * result + this.occupationProperties;
        result = 31 * result + this.perchaseType;
        result = 31 * result + this.pointHeight;
        result = 31 * result + this.pointWidth;
        result = 31 * result + this.sellPrice;
        result = 31 * result + this.unlockCastleLevel;
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
        final CmBuildingVO other = (CmBuildingVO) obj;
        if (this.actionId != other.actionId) {
            return false;
        }
        if (this.buildCd != other.buildCd) {
            return false;
        }
        if (this.buildTerrain != other.buildTerrain) {
            return false;
        }
        if (this.buildingDescribe == null) {
            if (other.buildingDescribe != null) {
                return false;
            }
        } else if (!this.buildingDescribe.equals(other.buildingDescribe)) {
            return false;
        }
        if (this.buildingId != other.buildingId) {
            return false;
        }
        if (this.buildingName == null) {
            if (other.buildingName != null) {
                return false;
            }
        } else if (!this.buildingName.equals(other.buildingName)) {
            return false;
        }
        return this.buildingType == other.buildingType && this.floorGrids == other.floorGrids && this.goldPrice == other.goldPrice && this.incomeCd == other.incomeCd && this.incomeExp == other.incomeExp && this.incomeGold == other.incomeGold && this.isCanUpgrade == other.isCanUpgrade && this.isFavor == other.isFavor && this.nextLevelId == other.nextLevelId && this.occupationProperties == other.occupationProperties && this.perchaseType == other.perchaseType && this.pointHeight == other.pointHeight && this.pointWidth == other.pointWidth && this.sellPrice == other.sellPrice && this.unlockCastleLevel == other.unlockCastleLevel && this.unlockRoleLevel == other.unlockRoleLevel;
    }
}
