package com.bw.cache.vo;

import java.io.*;

public class CGUserIslandInfoVO implements Serializable {

    private static final long serialVersionUID = -123997834139519424L;
    private int islandId;
    private int gridOpintX;
    private int gridOpintY;

    public int getIslandId() {
        return this.islandId;
    }

    public void setIslandId(final int islandId) {
        this.islandId = islandId;
    }

    public int getGridOpintX() {
        return this.gridOpintX;
    }

    public void setGridOpintX(final int gridOpintX) {
        this.gridOpintX = gridOpintX;
    }

    public int getGridOpintY() {
        return this.gridOpintY;
    }

    public void setGridOpintY(final int gridOpintY) {
        this.gridOpintY = gridOpintY;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.gridOpintX;
        result = 31 * result + this.gridOpintY;
        result = 31 * result + this.islandId;
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
        final CGUserIslandInfoVO other = (CGUserIslandInfoVO) obj;
        return this.gridOpintX == other.gridOpintX && this.gridOpintY == other.gridOpintY && this.islandId == other.islandId;
    }

    @Override
    public String toString() {
        return "CGUserIslandInfoVO [islandId=" + this.islandId + ", gridOpintX=" + this.gridOpintX + ", gridOpintY=" + this.gridOpintY + "]";
    }
}
