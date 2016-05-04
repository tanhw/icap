package com.core.models;

import java.io.Serializable;
import java.util.Date;

import com.constant.CommonConstant;

public class TMerchantInfo implements Serializable {
    private Long merseq;

    private String branchid;

    private Long unitid;

    private String bankmerid;

    private String brancheng;

    private String branchchn;

    private String stfid;

    private String branchstate;

    private String branchdesc;

    private String branchadre;

    private String branchphone;

    private Long feeseq;

    private String branlogo;

    private String brancity;

    private String fax;

    private String site;

    private Long poscount;

    private Date signtime;

    private String rsvd;
    
    private Integer acompanyid;

    public Integer getAcompanyid() {
		return acompanyid;
	}

	public void setAcompanyid(Integer acompanyid) {
		this.acompanyid = acompanyid;
	}

	private static final long serialVersionUID = 1L;

    public Long getMerseq() {
        return merseq;
    }

    public void setMerseq(Long merseq) {
        this.merseq = merseq;
    }

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public Long getUnitid() {
        return unitid;
    }

    public void setUnitid(Long unitid) {
        this.unitid = unitid;
    }

    public String getBankmerid() {
        return bankmerid;
    }

    public void setBankmerid(String bankmerid) {
        this.bankmerid = bankmerid;
    }

    public String getBrancheng() {
        return brancheng;
    }

    public void setBrancheng(String brancheng) {
        this.brancheng = brancheng;
    }

    public String getBranchchn() {
        return branchchn;
    }

    public void setBranchchn(String branchchn) {
        this.branchchn = branchchn;
    }

    public String getStfid() {
        return stfid;
    }

    public void setStfid(String stfid) {
        this.stfid = stfid;
    }

    public String getBranchstate() {
        return branchstate;
    }
    
	public String getBranchstateDesc() {
		return CommonConstant.BranchState.getDescByValue(branchstate);
	}
    
    public void setBranchstate(String branchstate) {
        this.branchstate = branchstate;
    }

    public String getBranchdesc() {
        return branchdesc;
    }

    public void setBranchdesc(String branchdesc) {
        this.branchdesc = branchdesc;
    }

    public String getBranchadre() {
        return branchadre;
    }

    public void setBranchadre(String branchadre) {
        this.branchadre = branchadre;
    }

    public String getBranchphone() {
        return branchphone;
    }

    public void setBranchphone(String branchphone) {
        this.branchphone = branchphone;
    }

    public Long getFeeseq() {
        return feeseq;
    }

    public void setFeeseq(Long feeseq) {
        this.feeseq = feeseq;
    }

    public String getBranlogo() {
        return branlogo;
    }

    public void setBranlogo(String branlogo) {
        this.branlogo = branlogo;
    }

    public String getBrancity() {
        return brancity;
    }

    public void setBrancity(String brancity) {
        this.brancity = brancity;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Long getPoscount() {
        return poscount;
    }

    public void setPoscount(Long poscount) {
        this.poscount = poscount;
    }

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }

    public String getRsvd() {
        return rsvd;
    }

    public void setRsvd(String rsvd) {
        this.rsvd = rsvd;
    }
}