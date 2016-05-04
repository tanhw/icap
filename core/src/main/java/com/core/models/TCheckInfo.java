package com.core.models;

import java.io.Serializable;
import java.util.Date;

public class TCheckInfo implements Serializable {
    private Long id;

    private String code;

    private Date createtime;

    private static final long serialVersionUID = 1L;

    public TCheckInfo() {

    }
    public TCheckInfo(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}