package com.core.models;

import java.io.Serializable;

public class TNoncontxnCollect implements Serializable {
    private String txntype;

    private String txndate;

    private Integer unitid;

    private String branchid;

    private String posid;

    private String crdkind;

    private String cardmodel;

    private Long txncount;

    private Long txnsum;

    private static final long serialVersionUID = 1L;

    public String getTxntype() {
        return txntype;
    }

    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }

    public String getTxndate() {
        return txndate;
    }

    public void setTxndate(String txndate) {
        this.txndate = txndate;
    }

    public Integer getUnitid() {
        return unitid;
    }

    public void setUnitid(Integer unitid) {
        this.unitid = unitid;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getCrdkind() {
        return crdkind;
    }

    public void setCrdkind(String crdkind) {
        this.crdkind = crdkind;
    }

    public String getCardmodel() {
        return cardmodel;
    }

    public void setCardmodel(String cardmodel) {
        this.cardmodel = cardmodel;
    }

    public Long getTxncount() {
        return txncount;
    }

    public void setTxncount(Long txncount) {
        this.txncount = txncount;
    }

    public Long getTxnsum() {
        return txnsum;
    }

    public void setTxnsum(Long txnsum) {
        this.txnsum = txnsum;
    }
}