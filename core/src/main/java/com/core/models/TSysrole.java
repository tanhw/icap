package com.core.models;

import java.io.Serializable;

import com.constant.CommonConstant;
import com.core.controller.common.SessionHandler;

import javax.jms.Session;

public class TSysrole implements Serializable {
	
	//序号
    private Long roleseq;
    
    //角色名称
    private String rolename;
    
    //角色描述
    private String roledesc;
    
    //机构ID
    private Long unitid;
    
    //类别
    private String roletype;

    private static final long serialVersionUID = 1L;

    public Long getRoleseq() {
        return roleseq;
    }

    public void setRoleseq(Long roleseq) {
        this.roleseq = roleseq;
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

	public Long getUnitid() {
		return unitid;
	}

	public void setUnitid(Long unitid) {
		this.unitid = unitid;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}
	
    public String getRoletypeDesc() {
        if (CommonConstant.RoleType.UnitChildNormalRole.getValue().equals(roletype)) {
            TUnitInfo unitid = SessionHandler.getUnit();
            if (unitid.getUnitkind().equals("1")) {
                return "商户";
            }
            if (unitid.getUnitkind().equals("2")) {
                return "园区";
            }
        }

        return CommonConstant.RoleType.getDescByValue(roletype);
    }

}