package com.core.models;

import java.io.Serializable;

public class TPosKeyInfo implements Serializable {
    private String posid;

    private String tmk;

    private String tmkchkvalue;

    private String kek;

    private String mak;

    private String makchkvalue;

    private String pik;

    private String pikchkvalue;
    
    private String trk;//终端TRK密钥
    
    private String trkchkvalue;//终端TRK密钥校验值
    
    private Long unitid;//机构号
    
    private String branchid;//商户编号
    
    private Long merseq;//商户号

    private static final long serialVersionUID = 1L;

	public String getTrk() {
		return trk;
	}

	public void setTrk(String trk) {
		this.trk = trk;
	}

	public String getTrkchkvalue() {
		return trkchkvalue;
	}

	public void setTrkchkvalue(String trkchkvalue) {
		this.trkchkvalue = trkchkvalue;
	}

	public Long getUnitid() {
		return unitid;
	}

	public void setUnitid(Long unitid) {
		this.unitid = unitid;
	}

	public String getBranchid() {
		return branchid;
	}

	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}

	public Long getMerseq() {
		return merseq;
	}

	public void setMerseq(Long merseq) {
		this.merseq = merseq;
	}

	public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getTmk() {
        return tmk;
    }

    public void setTmk(String tmk) {
        this.tmk = tmk;
    }

    public String getTmkchkvalue() {
        return tmkchkvalue;
    }

    public void setTmkchkvalue(String tmkchkvalue) {
        this.tmkchkvalue = tmkchkvalue;
    }

    public String getKek() {
        return kek;
    }

    public void setKek(String kek) {
        this.kek = kek;
    }

    public String getMak() {
        return mak;
    }

    public void setMak(String mak) {
        this.mak = mak;
    }

    public String getMakchkvalue() {
        return makchkvalue;
    }

    public void setMakchkvalue(String makchkvalue) {
        this.makchkvalue = makchkvalue;
    }

    public String getPik() {
        return pik;
    }

    public void setPik(String pik) {
        this.pik = pik;
    }

    public String getPikchkvalue() {
        return pikchkvalue;
    }

    public void setPikchkvalue(String pikchkvalue) {
        this.pikchkvalue = pikchkvalue;
    }
}