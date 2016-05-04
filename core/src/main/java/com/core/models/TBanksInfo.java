package com.core.models;

import java.io.Serializable;
import java.util.Date;

import com.toolbox.util.DateUtil;

public class TBanksInfo implements Serializable {
    private Long bankid;

    private String bankcode;

    private Long unitid;

    private String bankname;

    private String bankaddr;

    private String bankcontact;

    private String banktele;

    private String bankmail;

    private String bankfax;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public Long getUnitid() {
        return unitid;
    }

    public void setUnitid(Long unitid) {
        this.unitid = unitid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankaddr() {
        return bankaddr;
    }

    public void setBankaddr(String bankaddr) {
        this.bankaddr = bankaddr;
    }

    public String getBankcontact() {
        return bankcontact;
    }

    public void setBankcontact(String bankcontact) {
        this.bankcontact = bankcontact;
    }

    public String getBanktele() {
        return banktele;
    }

    public void setBanktele(String banktele) {
        this.banktele = banktele;
    }

    public String getBankmail() {
        return bankmail;
    }

    public void setBankmail(String bankmail) {
        this.bankmail = bankmail;
    }

    public String getBankfax() {
        return bankfax;
    }

    public void setBankfax(String bankfax) {
        this.bankfax = bankfax;
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
}