package com.core.models;

import java.io.Serializable;
import java.util.Date;

import com.constant.CommonConstant;
import com.toolbox.util.DateUtil;

public class TBlackInfo implements Serializable {
    private Long blackseq;

    private String cardno;

    private String name;

    private String mark;

    private String version;

    private Date createTime;

    private Long unitid;
    
    private Long companyid;
    
    private Long merseq;
    
    private static final long serialVersionUID = 1L;

    public Long getBlackseq() {
        return blackseq;
    }

    public void setBlackseq(Long blackseq) {
        this.blackseq = blackseq;
    }
    
    public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public Long getMerseq() {
		return merseq;
	}

	public void setMerseq(Long merseq) {
		this.merseq = merseq;
	}

	public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }
    
    public String getMarkDesc(){
    	return CommonConstant.Mark.getDescByValue(mark);
    }
    
    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUnitid() {
        return unitid;
    }

    public void setUnitid(Long unitid) {
        this.unitid = unitid;
    }
    
    /**
     * 格式化时间
     * @return
     * @throws Exception
     */
	public String getCreatetimeDesc() throws Exception {
		return DateUtil.formatDate(createTime, "yyyy-MM-dd HH:mm:ss");
	}
}