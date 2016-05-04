package com.core.models;

import java.io.Serializable;
import java.util.Date;

import com.constant.CommonConstant;
import com.toolbox.util.DateUtil;

public class TAdminInfo implements Serializable {
	
	//序号
    private Long adminseq;
    
    //登录名称
    private String loginname;
    
    //密码
    private String loginpwd;
    
    //创建时间
    private Date createtime;
    
    //真实姓名
    private String realname;
    
    //电话
    private String phone;
    
    //邮箱
    private String mail;
    
    //角色序号
    private Long roeseq;
    
    //是否修改
    private String isactive;
    
    //修改时间
    private Date modifytime;
    
    //上次登录时间
    private Date lastlogtime;
    
    //上次登录IP
    private String lastlogip;
    
    //角色名称
    private String rolename;
    
    //角色描述
    private String roledesc;
    
    //类别
    private String roletype;
    
    //本次登录时间
    private Date nowlogtime;
    
    //密码错误次数
    private Integer pwderrnum;
    
    //锁定时间
    private Date locklogtime;


    private static final long serialVersionUID = 1L;

    public Long getAdminseq() {
        return adminseq;
    }

    public void setAdminseq(Long adminseq) {
        this.adminseq = adminseq;
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
    public String getIsactiveDesc(){
    	return CommonConstant.IsActive.getDescByValue(isactive);
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public Date getModifytime() {
        return modifytime;
    }
    
    public String getModifytimeDesc()throws Exception{
    	return DateUtil.formatDate(modifytime, "yyyy-MM-dd HH:mm:ss");
    }
    
    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Date getLastlogtime() {
        return lastlogtime;
    }
    
    public String getLastlogtimeDesc() throws Exception{
    	return DateUtil.formatDate(lastlogtime, "yyyy-MM-dd HH:mm:ss");
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

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
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