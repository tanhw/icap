package com.core.models;

import java.io.Serializable;
import java.util.Date;

import com.constant.CommonConstant;
import com.toolbox.util.DateUtil;

public class TOpLog implements Serializable {
	
	//序号
	private Long logseq;
	
	//操作员ID
    private String oplogname;
    
    //操作员姓名
    private String oprealname;
    
    //日志类型
    private String logtype;
    
    //操作时间
    private Date optime;
    
    //来源IP
    private String opip;
    
    //操作是否成功
    private String opflag;
    
    //日志描述
    private String opdesc;
    
    //机构Id
    private Long unitid;
    
    public Long getUnitid() {
		return unitid;
	}

	public void setUnitid(Long unitid) {
		this.unitid = unitid;
	}

	private static final long serialVersionUID = 1L;

    public Long getLogseq() {
        return logseq;
    }

    public void setLogseq(Long logseq) {
        this.logseq = logseq;
    }

    public String getOplogname() {
        return oplogname;
    }

    public void setOplogname(String oplogname) {
        this.oplogname = oplogname;
    }

    public String getOprealname() {
        return oprealname;
    }

    public void setOprealname(String oprealname) {
        this.oprealname = oprealname;
    }

    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }

    public Date getOptime() {
        return optime;
    }
    
	public String getOptimeDesc() throws Exception {

		return  DateUtil.formatDate(optime, "yyyy-MM-dd HH:mm:ss");
		
	}
    
    public void setOptime(Date optime) {
        this.optime = optime;
    }

    public String getOpip() {
        return opip;
    }

    public void setOpip(String opip) {
        this.opip = opip;
    }

    public String getOpflag() {
        return opflag;
    }
    
    public String getOpflagDesc(){
    	return CommonConstant.SuccessFlag.getDescByValue(opflag);
    }
    
    public void setOpflag(String opflag) {
        this.opflag = opflag;
    }

	public String getOpdesc() {
		return opdesc;
	}

	public void setOpdesc(String opdesc) {
		this.opdesc = opdesc;
	}
    
}