package com.core.models;

import java.io.Serializable;
import java.util.Date;

public class TPospInfo implements Serializable {
    private String posid;

    private String bankposid;

    private String postype;

    private Integer unitid;

    private String branchid;

    private String bankbranchid;

    private Integer merseq;

    private String samid;

    private String termseq;

    private String batchno;

    private String postraceno;

    private String status;

    private String physicsno;

    private Date createtime;

    private String keyadownflag;

    private String tmkdownflag;

    private String mainkey;

    private String bankposkey;

    private String workkey;

    private Date lasttimestamp;

    private String posbrand;

    private String termid;

    private String rsvd;

    private static final long serialVersionUID = 1L;

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getBankposid() {
        return bankposid;
    }

    public void setBankposid(String bankposid) {
        this.bankposid = bankposid;
    }

    public String getPostype() {
        return postype;
    }

    public void setPostype(String postype) {
        this.postype = postype;
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

    public String getBankbranchid() {
        return bankbranchid;
    }

    public void setBankbranchid(String bankbranchid) {
        this.bankbranchid = bankbranchid;
    }

    public Integer getMerseq() {
        return merseq;
    }

    public void setMerseq(Integer merseq) {
        this.merseq = merseq;
    }

    public String getSamid() {
        return samid;
    }

    public void setSamid(String samid) {
        this.samid = samid;
    }

    public String getTermseq() {
        return termseq;
    }

    public void setTermseq(String termseq) {
        this.termseq = termseq;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getPostraceno() {
        return postraceno;
    }

    public void setPostraceno(String postraceno) {
        this.postraceno = postraceno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhysicsno() {
        return physicsno;
    }

    public void setPhysicsno(String physicsno) {
        this.physicsno = physicsno;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getKeyadownflag() {
        return keyadownflag;
    }

    public void setKeyadownflag(String keyadownflag) {
        this.keyadownflag = keyadownflag;
    }

    public String getTmkdownflag() {
        return tmkdownflag;
    }

    public void setTmkdownflag(String tmkdownflag) {
        this.tmkdownflag = tmkdownflag;
    }

    public String getMainkey() {
        return mainkey;
    }

    public void setMainkey(String mainkey) {
        this.mainkey = mainkey;
    }

    public String getBankposkey() {
        return bankposkey;
    }

    public void setBankposkey(String bankposkey) {
        this.bankposkey = bankposkey;
    }

    public String getWorkkey() {
        return workkey;
    }

    public void setWorkkey(String workkey) {
        this.workkey = workkey;
    }

    public Date getLasttimestamp() {
        return lasttimestamp;
    }

    public void setLasttimestamp(Date lasttimestamp) {
        this.lasttimestamp = lasttimestamp;
    }

    public String getPosbrand() {
        return posbrand;
    }

    public void setPosbrand(String posbrand) {
        this.posbrand = posbrand;
    }

    public String getTermid() {
        return termid;
    }

    public void setTermid(String termid) {
        this.termid = termid;
    }

    public String getRsvd() {
        return rsvd;
    }

    public void setRsvd(String rsvd) {
        this.rsvd = rsvd;
    }
}