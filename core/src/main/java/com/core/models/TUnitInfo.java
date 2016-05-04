package com.core.models;

import java.io.Serializable;
import java.util.Date;

import com.constant.CommonConstant;
import com.toolbox.util.DateUtil;

public class TUnitInfo implements Serializable {
    
	//机构ID
	private Long unitid;
	
	//机构名称
	private String unitname;
	
	//机构描述
	private String unitdesc;
	
	//机构地址
	private String unitaddr;
	
	//机构联系人
	private String unitcontact;
	
	//机构联系电话
	private String unittele;
	
	//机构邮箱
	private String unitmail;
	
	//创建时间
	private Date createtime;
	
	//机构传真
	private String unitfax;
	
	//父级机构
	private String parentid;
	
	
	//机构签名信息
	private String signinfo;
	
	//机构种类
	private String unitkind;
	
    //机构英文名
	private String unitename;
	
	//机构状态
	private String status;
	
	//保留域
	private String rsvd;

	private String bankid;

	private String bankorgid;

	private String bankposoffseq;

	public String getBankposoffseq() {
		return bankposoffseq;
	}

	public void setBankposoffseq(String bankposoffseq) {
		this.bankposoffseq = bankposoffseq;
	}
	
	public String getUnitename() {
		return unitename;
	}

	public void setUnitename(String unitename) {
		this.unitename = unitename;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRsvd() {
		return rsvd;
	}

	public void setRsvd(String rsvd) {
		this.rsvd = rsvd;
	}

	public String getUnitkind() {
		return unitkind;
	}
	
	public String getUnitkindDesc(){
		return CommonConstant.getDesc("BUSIKIND", unitkind);
	}
	
	public void setUnitkind(String unitkind) {
		this.unitkind = unitkind;
	}


	public String getSigninfo() {
		return signinfo;
	}

	public void setSigninfo(String signinfo) {
		this.signinfo = signinfo;
	}

	private static final long serialVersionUID = 1L;

	public Long getUnitid() {
		return unitid;
	}

	public void setUnitid(Long unitid) {
		this.unitid = unitid;
	}
	
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getUnitdesc() {
		return unitdesc;
	}

	public void setUnitdesc(String unitdesc) {
		this.unitdesc = unitdesc;
	}

	public String getUnitaddr() {
		return unitaddr;
	}

	public void setUnitaddr(String unitaddr) {
		this.unitaddr = unitaddr;
	}

	public String getUnitcontact() {
		return unitcontact;
	}

	public void setUnitcontact(String unitcontact) {
		this.unitcontact = unitcontact;
	}

	public String getUnittele() {
		return unittele;
	}

	public void setUnittele(String unittele) {
		this.unittele = unittele;
	}

	public String getUnitmail() {
		return unitmail;
	}

	public void setUnitmail(String unitmail) {
		this.unitmail = unitmail;
	}

	public Date getCreatetime() {
		return createtime;
	}
	
	/**
	 * 格式化时间
	 * @return
	 * @throws Exception
	 */
	public String getCreatetimeDesc()throws Exception{
		return DateUtil.formatDate(createtime, "yyyy-MM-dd HH:mm:ss");
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUnitfax() {
		return unitfax;
	}

	public void setUnitfax(String unitfax) {
		this.unitfax = unitfax;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getBankorgid() {
		return bankorgid;
	}

	public void setBankorgid(String bankorgid) {
		this.bankorgid = bankorgid;
	}
}