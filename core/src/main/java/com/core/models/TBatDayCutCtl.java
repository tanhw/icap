package com.core.models;

import java.io.Serializable;

public class TBatDayCutCtl implements Serializable {
    private String prevtxndt;

    private String currtxndt;

    private String sysstat;

    private String lastupddt;

    private String lastupdtm;

    private String reserved;

    private String recordstat;

    private static final long serialVersionUID = 1L;

    public String getPrevtxndt() {
        return prevtxndt;
    }

    public void setPrevtxndt(String prevtxndt) {
        this.prevtxndt = prevtxndt;
    }

    public String getCurrtxndt() {
        return currtxndt;
    }

    public void setCurrtxndt(String currtxndt) {
        this.currtxndt = currtxndt;
    }

    public String getSysstat() {
        return sysstat;
    }

    public void setSysstat(String sysstat) {
        this.sysstat = sysstat;
    }

    public String getLastupddt() {
        return lastupddt;
    }

    public void setLastupddt(String lastupddt) {
        this.lastupddt = lastupddt;
    }

    public String getLastupdtm() {
        return lastupdtm;
    }

    public void setLastupdtm(String lastupdtm) {
        this.lastupdtm = lastupdtm;
    }

    public String getReserved() {
        return reserved;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    public String getRecordstat() {
        return recordstat;
    }

    public void setRecordstat(String recordstat) {
        this.recordstat = recordstat;
    }
}