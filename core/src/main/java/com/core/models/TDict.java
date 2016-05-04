package com.core.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.constant.CommonConstant;

public class TDict implements Serializable {
	
	//字典编号
    private String ccode;

    //内容值
    private String cvalue;
    
    //内容描述
    private String cdesc;
    
    //字典类型
    private String ctype;
    
    //是否有效
    private String isactive;
    
    //父级编号
    private String cupcode;
    
    //父级编号
    private String corder;
    
    //机构
    private Long unitid;

    private static final long serialVersionUID = 1L;

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    public String getCtype() {
        return ctype;
    }
    
    public String getCorder() {
		return corder;
	}

	public void setCorder(String corder) {
		this.corder = corder;
	}

	public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getActiveDesc(){
    	return CommonConstant.IsActive.getDescByValue(isactive);
    }
    
    public String getCupcode() {
        return cupcode;
    }

    public void setCupcode(String cupcode) {
        this.cupcode = cupcode;
    }

	public String getCdesc() {
		return cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}
	
	public Long getUnitid() {
		return unitid;
	}

	public void setUnitid(Long unitid) {
		this.unitid = unitid;
	}

	public Map<String, String> getInfo(){
		Map<String, String> map = new HashMap<String, String>();
		map.put(ccode, cvalue);
		return  map;
	}
    
}