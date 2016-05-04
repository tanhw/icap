package com.core.models;

import java.io.Serializable;

public class TUnitTabConf implements Serializable {
    private Integer confunitid;

    private Integer unitid;

    private Integer confid;

    private static final long serialVersionUID = 1L;

    public Integer getConfunitid() {
        return confunitid;
    }

    public void setConfunitid(Integer confunitid) {
        this.confunitid = confunitid;
    }

    public Integer getUnitid() {
        return unitid;
    }

    public void setUnitid(Integer unitid) {
        this.unitid = unitid;
    }

    public Integer getConfid() {
        return confid;
    }

    public void setConfid(Integer confid) {
        this.confid = confid;
    }
}