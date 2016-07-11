package com.bw.cache.vo;

import java.io.*;
import java.util.*;

public class CGUserSystemMailVO implements Serializable {

    private static final long serialVersionUID = 3713214167614584174L;
    private long id;
    private String title;
    private String mailContent;
    private String mailTo;
    private boolean isTop;
    private Date lastModify;

    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getMailContent() {
        return this.mailContent;
    }

    public void setMailContent(final String mailContent) {
        this.mailContent = mailContent;
    }

    public String getMailTo() {
        return this.mailTo;
    }

    public void setMailTo(final String mailTo) {
        this.mailTo = mailTo;
    }

    public boolean isTop() {
        return this.isTop;
    }

    public void setTop(final boolean isTop) {
        this.isTop = isTop;
    }

    public Date getLastModify() {
        return this.lastModify;
    }

    public void setLastModify(final Date lastModify) {
        this.lastModify = lastModify;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = 31 * result + (int) (this.id ^ this.id >>> 32);
        result = 31 * result + (this.isTop ? 1231 : 1237);
        result = 31 * result + ((this.lastModify == null) ? 0 : this.lastModify.hashCode());
        result = 31 * result + ((this.mailContent == null) ? 0 : this.mailContent.hashCode());
        result = 31 * result + ((this.mailTo == null) ? 0 : this.mailTo.hashCode());
        result = 31 * result + ((this.title == null) ? 0 : this.title.hashCode());
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
        final CGUserSystemMailVO other = (CGUserSystemMailVO) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.isTop != other.isTop) {
            return false;
        }
        if (this.lastModify == null) {
            if (other.lastModify != null) {
                return false;
            }
        } else if (!this.lastModify.equals(other.lastModify)) {
            return false;
        }
        if (this.mailContent == null) {
            if (other.mailContent != null) {
                return false;
            }
        } else if (!this.mailContent.equals(other.mailContent)) {
            return false;
        }
        if (this.mailTo == null) {
            if (other.mailTo != null) {
                return false;
            }
        } else if (!this.mailTo.equals(other.mailTo)) {
            return false;
        }
        if (this.title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!this.title.equals(other.title)) {
            return false;
        }
        return true;
    }
}
