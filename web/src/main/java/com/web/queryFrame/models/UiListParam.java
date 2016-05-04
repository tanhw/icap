/**
 * 
 */
package com.web.queryFrame.models;

/**
 * 列表参数
 * @ author sys
 *
 */
public class UiListParam {

	private String url;
	private String sortName;
	private String sortOrder;
	private boolean checkbox;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public boolean isCheckbox() {
		return checkbox;
	}
	public void setCheckbox(String checkbox) {
		if (checkbox==null || !checkbox.trim().equals("true"))
			this.checkbox = false;
		else
			this.checkbox=true;
	}
	
	
	
}
