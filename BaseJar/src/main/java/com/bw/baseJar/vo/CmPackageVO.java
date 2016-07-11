package com.bw.baseJar.vo;

import java.io.*;

public class CmPackageVO implements Serializable {

    private static final long serialVersionUID = -2193747879661847725L;
    private int id;
    private int labelNumber;
    private int expandPrice;

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getLabelNumber() {
        return this.labelNumber;
    }

    public void setLabelNumber(final int labelNumber) {
        this.labelNumber = labelNumber;
    }

    public int getExpandPrice() {
        return this.expandPrice;
    }

    public void setExpandPrice(final int expandPrice) {
        this.expandPrice = expandPrice;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + this.expandPrice;
        result = 31 * result + this.id;
        result = 31 * result + this.labelNumber;
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
        final CmPackageVO other = (CmPackageVO) obj;
        return this.expandPrice == other.expandPrice && this.id == other.id && this.labelNumber == other.labelNumber;
    }
}
