/**
 * 
 */
package com.web.queryFrame.models;

/**
 * UI页面显示字段
 * @ author sys
 */
public class UiColumn {
	private String field;
	private String width;
	private String columnName;
	private String align;
	
	private Boolean isSort;
	private String formatter;
	private String styler;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		try{
			Integer w =Integer.parseInt(width);
			if (w>0 && w<1000) 
				this.width=width;
			else
				this.width="100";
		}
		catch (Exception e) {
			this.width = "100";
		}
		
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		if (align==null || align.trim().equals(""))
			this.align="left";
		else
			this.align = align;
	}
	public Boolean getIsSort() {
		return isSort;
	}
	public void setIsSort(String isSort) {
		if (isSort==null || !isSort.trim().equals("true"))
			this.isSort = false;
		else
			this.isSort=true;
	}
	public String getFormatter() {
		return formatter;
	}
	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}
	public String getStyler() {
		return styler;
	}
	public void setStyler(String styler) {
		this.styler = styler;
	}
	
	public UiColumn(String columnName, String field){
		this.columnName = columnName;
		this.field = field;
	}
	
	public UiColumn(){
	}
	
	
}
