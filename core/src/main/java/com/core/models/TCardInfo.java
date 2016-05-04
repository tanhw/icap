package com.core.models;

import com.constant.CommonConstant;
import com.toolbox.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class TCardInfo implements Serializable {
    private Long cardid;

    private String status;

    private String cardno;

    private Date createTime;

    private Long unitid;

    private Long campid;

    private static final long serialVersionUID = 1L;

    public Long getCardid() {
        return cardid;
    }

    public void setCardid(Long cardid) {
        this.cardid = cardid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
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

    public Long getCampid() {
        return campid;
    }

    public void setCampid(Long campid) {
        this.campid = campid;
    }

    /**
     * 格式化时间
     * @return
     * @throws Exception
     */
    public String getCreatetimeDesc() throws Exception {
        return DateUtil.formatDate(createTime, "yyyy-MM-dd HH:mm:ss");
    }

    public String getStatusDesc() {
        return CommonConstant.getDesc("CARDLIST", status);
    }
}