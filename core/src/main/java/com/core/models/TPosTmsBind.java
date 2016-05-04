package com.core.models;

import java.io.Serializable;

import com.constant.CommonConstant;

public class TPosTmsBind implements Serializable {
    private String posid;

    private String filename;

    private Integer unitid;

    private String branchid;

    private Integer merseq;
    
    private String postype;

    private static final long serialVersionUID = 1L;

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public Integer getMerseq() {
        return merseq;
    }

    public void setMerseq(Integer merseq) {
        this.merseq = merseq;
    }

	public String getPostype() {
		return postype;
	}

	public void setPostype(String postype) {
		this.postype = postype;
	}
	
   public String getPostypeDesc(){
	   return CommonConstant.getDesc("POSTYPE",postype);
    }
    
}