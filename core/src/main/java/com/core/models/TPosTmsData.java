package com.core.models;

import java.io.Serializable;

import com.constant.CommonConstant;

public class TPosTmsData implements Serializable {
    private String filename;

    private Integer unitid;

    private String branchid;

    private Integer merseq;

    private String filepath;

    private String version;

    private String filesize;

    private String filefunc;
    
    private String posbrand;

    public String getPosbrand() {
		return posbrand;
	}
    
    public String getPosbrandDesc(){
    	return CommonConstant.getDesc("POSBRAND", posbrand);
    }
    
	public void setPosbrand(String posbrand) {
		this.posbrand = posbrand;
	}

	private static final long serialVersionUID = 1L;

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

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getFilefunc() {
        return filefunc;
    }

    public void setFilefunc(String filefunc) {
        this.filefunc = filefunc;
    }
}