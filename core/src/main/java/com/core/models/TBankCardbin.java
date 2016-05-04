package com.core.models;

import java.io.Serializable;
import java.util.Date;

import com.toolbox.util.DateUtil;

public class TBankCardbin implements Serializable {
    private Long binseq;

    private String cardbin;

    private Long bankid;

    private String binname;

    private Date createtime;

    private Long unitid;

    private static final long serialVersionUID = 1L;

    public Long getBinseq() {
        return binseq;
    }

    public void setBinseq(Long binseq) {
        this.binseq = binseq;
    }

    public String getCardbin() {
        return cardbin;
    }

    public void setCardbin(String cardbin) {
        this.cardbin = cardbin;
    }

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public String getBinname() {
        return binname;
    }

    public void setBinname(String binname) {
        this.binname = binname;
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

    public Long getUnitid() {
        return unitid;
    }

    public void setUnitid(Long unitid) {
        this.unitid = unitid;
    }
}