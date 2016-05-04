package com.core.models;

import java.io.Serializable;

public class TRoleMenuInfo implements Serializable {
    
	//序号
	private Long roleMenuSeq;
	
	//角色序号
	private Long roleSeq;

	//菜单序号
	private String menuCode;

	private static final long serialVersionUID = 1L;

	public Long getRoleMenuSeq() {
		return roleMenuSeq;
	}

	public void setRoleMenuSeq(Long roleMenuSeq) {
		this.roleMenuSeq = roleMenuSeq;
	}

	public Long getRoleSeq() {
		return roleSeq;
	}

	public void setRoleSeq(Long roleSeq) {
		this.roleSeq = roleSeq;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

}