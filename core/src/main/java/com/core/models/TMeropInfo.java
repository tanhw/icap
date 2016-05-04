package com.core.models;

import java.io.Serializable;
import java.util.Date;

import com.constant.CommonConstant;
import com.toolbox.util.DateUtil;

public class TMeropInfo implements Serializable {
    private Long meropseq;

    private Long merseq;

    private String loginname;

    private String loginpwd;

    private Date createtime;

    private String realname;

    private String phone;

    private String mail;

    private Long roeseq;

    private String isactive;

    private Date modifytime;

    private Date lastlogtime;

    private String lastlogip;

    private Long unitid;
    
    private Long storeseq;
    
    //本次登录时间
    private Date nowlogtime;
    
    //密码错误次数
    private Integer pwderrnum;
    
    //锁定时间
    private Date locklogtime;
    
    public Long getStoreseq() {
		return storeseq;
	}

	public void setStoreseq(Long storeseq) {
		this.storeseq = storeseq;
	}

	private static final long serialVersionUID = 1L;

    public Long getMeropseq() {
        return meropseq;
    }

    public void setMeropseq(Long meropseq) {
        this.meropseq = meropseq;
    }

    public Long getMerseq() {
        return merseq;
    }

    public void setMerseq(Long merseq) {
        this.merseq = merseq;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpwd() {
        return loginpwd;
    }

    public void setLoginpwd(String loginpwd) {
        this.loginpwd = loginpwd;
    }

    public Date getCreatetime() {
        return createtime;
    }
    
    /**
     * 格式化时间
     * @return
     */
    public String getCreatetimeDesc()throws Exception{
    	return DateUtil.formatDate(createtime, "yyyy-MM-dd HH:mm:ss");
    }
    
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getRoeseq() {
        return roeseq;
    }

    public void setRoeseq(Long roeseq) {
        this.roeseq = roeseq;
    }
    
    public String getIsactive() {
        return isactive;
    }
    
    /**
     * 是否有效
     * @return
     */
    public String getIsactiveDesc(){
    	return CommonConstant.IsActive.getDescByValue(isactive);
    }
    
    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public Date getModifytime() {
        return modifytime;
    }
    
    /**
     * 修改时间
     * @return
     * @throws Exception
     */
    public String getModifytimeDesc()throws Exception{
    	return DateUtil.formatDate(modifytime, "yyyy-MM-dd HH:mm:ss");
    }
    
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }
    
    /**
     * 最后登录时间
     * @return
     */
    public String getLastlogtimeDesc()throws Exception{
    	return DateUtil.formatDate(modifytime, "yyyy-MM-dd HH:mm:ss");
    }
    
    public Date getLastlogtime() {
        return lastlogtime;
    }

    public void setLastlogtime(Date lastlogtime) {
        this.lastlogtime = lastlogtime;
    }

    public String getLastlogip() {
        return lastlogip;
    }

    public void setLastlogip(String lastlogip) {
        this.lastlogip = lastlogip;
    }

    public Long getUnitid() {
        return unitid;
    }

    public void setUnitid(Long unitid) {
        this.unitid = unitid;
    }

	public Date getNowlogtime() {
		return nowlogtime;
	}

	public void setNowlogtime(Date nowlogtime) {
		this.nowlogtime = nowlogtime;
	}

	public Integer getPwderrnum() {
		return pwderrnum;
	}

	public void setPwderrnum(Integer pwderrnum) {
		this.pwderrnum = pwderrnum;
	}

	public Date getLocklogtime() {
		return locklogtime;
	}

	public void setLocklogtime(Date locklogtime) {
		this.locklogtime = locklogtime;
	}
    
}