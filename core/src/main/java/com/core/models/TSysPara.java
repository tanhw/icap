package com.core.models;

import java.io.Serializable;

import com.constant.CommonConstant;

public class TSysPara implements Serializable {
    private String uname;

    private String paraName;

    private String paraNo;

    private String paraValue;

    private String paraDesc;

    private String recordStat;

    private static final long serialVersionUID = 1L;
    
    public String getMainkeyinfo(){
    	return uname + "," + paraName + "," + paraNo;
    }
    
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public String getParaNo() {
        return paraNo;
    }

    public void setParaNo(String paraNo) {
        this.paraNo = paraNo;
    }

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
    }

    public String getParaDesc() {
        return paraDesc;
    }

    public void setParaDesc(String paraDesc) {
        this.paraDesc = paraDesc;
    }

    public String getRecordStat() {
        return recordStat;
    }
    
    public String getRecordStatDesc(){
    	return CommonConstant.RecordStat.getDescByValue(recordStat);
    }
    
    public void setRecordStat(String recordStat) {
        this.recordStat = recordStat;
    }
}