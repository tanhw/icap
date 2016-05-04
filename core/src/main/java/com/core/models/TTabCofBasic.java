package com.core.models;

import java.io.Serializable;

import com.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TTabCofBasic implements Serializable {
    private Integer confid;

    private String confname;

    private String title;// 标题

    private String exptitle;// 导出标题

    private String isactive;

    private String busi;

    private String sql;

    private String filed;

    private String fileddesc;

    private String collectfiled;

    private boolean checked;

    private static final long serialVersionUID = 1L;

    public String getExptitle() {
        return exptitle;
    }

    public void setExptitle(String exptitle) {
        this.exptitle = exptitle;
    }

	@JsonProperty("id")
	public Integer getId() {
		return confid;
	}
    
	@JsonProperty("text")
	public String getText() {
		return confname;
	}
	
    public Integer getConfid() {
        return confid;
    }
    
    public void setConfid(Integer confid) {
        this.confid = confid;
    }
    
    public String getConfname() {
        return confname;
    }

    public void setConfname(String confname) {
        this.confname = confname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsactive() {
        return isactive;
    }
    
	public String getIsactiveDesc() {
		return CommonConstant.IsActive.getDescByValue(isactive);
	}
    
    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getBusi() {
        return busi;
    }

    public void setBusi(String busi) {
        this.busi = busi;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public String getFileddesc() {
        return fileddesc;
    }

    public void setFileddesc(String fileddesc) {
        this.fileddesc = fileddesc;
    }

    public String getCollectfiled() {
        return collectfiled;
    }

    public void setCollectfiled(String collectfiled) {
        this.collectfiled = collectfiled;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}