package com.core.models;

import com.constant.CommonConstant;
import com.toolbox.util.DateUtil;
import com.toolbox.util.FormatPrice;

import java.io.Serializable;
import java.util.Date;

public class TPosInfo implements Serializable {

    private String posid;

    private String postype;

    private Long unitid;

    private String branchid;
    
    private Long merseq;

    private Long busid;

    private String samid;

    private String termseq;

    private String batchno;

    private String postraceno;

    private String status;

    private Long timeout;

    private Long maxmoney;

    private Long totalmoney;

    private Date createtime;

    private String poskey;

    private String posmac;

    private String tmk;

    private String mak;

    private String lmk;

    private String pik;

    private String trk;
    
    private String busiid;
    
    //主密钥下载标志
    private String tmkdownflag;
    
    //keya密钥下载标志
    private String keyadownflag;
    
    private String physicsno;
    
	// 最后密钥下载时间
	private Date lasttimestamp;
	
	// pos品牌类型
	private String posbrand;
	
	//车牌号
	private String tlic;
	
	//机构名称
	private String unitname;
	
	//商户名称
	private String branchchn;
	
	//商户区域
	private String companyname;
	
	private Integer poscollecttype;//清算设备采集方式（0-按页补采，1-按交易序列号补采，2-无线设备）
	
	private String termid; //企业设备编号
	
	private String rsvd;// 保留域
	
	public Integer getPoscollecttype() {
		return poscollecttype;
	}

	public void setPoscollecttype(Integer poscollecttype) {
		this.poscollecttype = poscollecttype;
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

	public String getPosbrand() {
		return posbrand;
	}
	
	public String getPosbrandDesc() {
		return CommonConstant.getDesc("POSBRAND", posbrand);
	}
	
	public void setPosbrand(String posbrand) {
		this.posbrand = posbrand;
	}

	public Date getLasttimestamp() {
		return lasttimestamp;
	}

	public String getLasttimestampDesc() throws Exception {
		return DateUtil.formatDate(lasttimestamp, "yyyy-MM-dd HH:mm:ss");
	}

	public void setLasttimestamp(Date lasttimestamp) {
		this.lasttimestamp = lasttimestamp;
	}

	public String getPhysicsno() {
		return physicsno;
	}

	public void setPhysicsno(String physicsno) {
		this.physicsno = physicsno;
	}

	private static final long serialVersionUID = 1L;
    
    public String getBusiid() {
		return busiid;
	}

	public void setBusiid(String busiid) {
		this.busiid = busiid;
	}

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getPostype() {
        return postype;
    }
    
    /**
     * Pos类型格式化
     * @return
     */
    public String getPostypeDesc(){
    	return CommonConstant.getDesc("POSTYPE",postype);
    }
    
    public void setPostype(String postype) {
        this.postype = postype;
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
    
	public Long getBusid() {
        return busid;
    }

    public void setBusid(Long busid) {
        this.busid = busid;
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
    
    /**
     * Pos状态格式化
     * @return
     */
    public String getStatusDesc(){
    	return CommonConstant.getDesc("POSSTATUS",status);
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Long getMaxmoney() {
        return maxmoney;
    }

    public String getMaxmoneyDesc() {
        if (maxmoney != null) {
            return FormatPrice.formatAMT(maxmoney);
        } else {
            return "0.00";
        }
    }

    public void setMaxmoney(Long maxmoney) {
        this.maxmoney = maxmoney;
    }

    public Long getTotalmoney() {
        return totalmoney;
    }

    public String getTotalmoneyDesc() {
        if (totalmoney != null) {
            return FormatPrice.formatAMT(totalmoney);
        } else {
            return "0.00";
        }
    }

    public void setTotalmoney(Long totalmoney) {
        this.totalmoney = totalmoney;
    }

    public Date getCreatetime() {
        return createtime;
    }
    
    /**
     * 时间格式化
     * @return
     */
    public String getCreatetimeDesc()throws Exception{
    	
    	return DateUtil.formatDate(createtime, "yyyy-MM-dd HH:mm:ss");
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getPoskey() {
        return poskey;
    }

    public void setPoskey(String poskey) {
        this.poskey = poskey;
    }

    public String getPosmac() {
        return posmac;
    }

    public void setPosmac(String posmac) {
        this.posmac = posmac;
    }

    public String getMak() {
        return mak;
    }

    public String getTmk() {
		return tmk;
	}

	public void setTmk(String tmk) {
		this.tmk = tmk;
	}

	public void setMak(String mak) {
        this.mak = mak;
    }

    public String getLmk() {
        return lmk;
    }

    public void setLmk(String lmk) {
        this.lmk = lmk;
    }

    public String getPik() {
        return pik;
    }

    public void setPik(String pik) {
        this.pik = pik;
    }

    public String getTrk() {
        return trk;
    }

    public void setTrk(String trk) {
        this.trk = trk;
    }

	public String getTmkdownflag() {
		return tmkdownflag;
	}
	
	/**
	 * 主密钥下载标志
	 * 
	 * @return
	 */
	public String getTmkdownflagDesc(){
		return CommonConstant.TmkDownFlag.getDescByValue(tmkdownflag);
	}
	
	public void setTmkdownflag(String tmkdownflag) {
		this.tmkdownflag = tmkdownflag;
	}

	public String getKeyadownflag() {
		return keyadownflag;
	}
	
	/**
	 * keya密钥下载标志
	 * 
	 * @return
	 */
	public String getKeyadownflagDesc() {
		return CommonConstant.KeyaDownFlag.getDescByValue(keyadownflag);
	}
	
	public void setKeyadownflag(String keyadownflag) {
		this.keyadownflag = keyadownflag;
	}

	public String getTlic() {
		return tlic;
	}

	public void setTlic(String tlic) {
		this.tlic = tlic;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getBranchchn() {
		return branchchn;
	}

	public void setBranchchn(String branchchn) {
		this.branchchn = branchchn;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
}