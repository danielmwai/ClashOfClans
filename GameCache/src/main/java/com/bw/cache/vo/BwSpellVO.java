package com.bw.cache.vo;

import java.io.Serializable;

public class BwSpellVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6517584500692515128L;

    /**
     *
     */
    public long id;
    public long usermapdataid;
    public int spelltypeid;
    public int spelllevel;
    public int spellcount;
    public String spellcreatestarttime;
    public short spellstatus;
    public String spellcreateendtime;
    public String mailaddress;

    public String getMailaddress() {
        return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

    public long getUsermapdataid() {
        return usermapdataid;
    }

    public void setUsermapdataid(long usermapdataid) {
        this.usermapdataid = usermapdataid;
    }

    public int getSpelltypeid() {
        return spelltypeid;
    }

    public void setSpelltypeid(int spelltypeid) {
        this.spelltypeid = spelltypeid;
    }

    public int getSpelllevel() {
        return spelllevel;
    }

    public void setSpelllevel(int spelllevel) {
        this.spelllevel = spelllevel;
    }

    public int getSpellcount() {
        return spellcount;
    }

    public void setSpellcount(int spellcount) {
        this.spellcount = spellcount;
    }

    public String getSpellcreatestarttime() {
        return spellcreatestarttime;
    }

    public void setSpellcreatestarttime(String spellcreatestarttime) {
        this.spellcreatestarttime = spellcreatestarttime;
    }

    public short getSpellstatus() {
        return spellstatus;
    }

    public void setSpellstatus(short spellstatus) {
        this.spellstatus = spellstatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpellcreateendtime() {
        return spellcreateendtime;
    }

    public void setSpellcreateendtime(String spellcreateendtime) {
        this.spellcreateendtime = spellcreateendtime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
