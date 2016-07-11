package com.bw.baseJar.vo;

import java.io.*;

public class CmSeaDataVO implements Serializable {

    private static final long serialVersionUID = 7563487821997214742L;
    private long id;
    private String pointX;
    private String pointY;
    private int pieceCount;

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getPointX() {
        return this.pointX;
    }

    public void setPointX(final String pointX) {
        this.pointX = pointX;
    }

    public String getPointY() {
        return this.pointY;
    }

    public void setPointY(final String pointY) {
        this.pointY = pointY;
    }

    public int getPieceCount() {
        return this.pieceCount;
    }

    public void setPieceCount(final int pieceCount) {
        this.pieceCount = pieceCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + (int) (this.id ^ this.id >>> 32);
        result = 31 * result + this.pieceCount;
        result = 31 * result + ((this.pointX == null) ? 0 : this.pointX.hashCode());
        result = 31 * result + ((this.pointY == null) ? 0 : this.pointY.hashCode());
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
        final CmSeaDataVO other = (CmSeaDataVO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.pieceCount != other.pieceCount) {
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
        return true;
    }
}
