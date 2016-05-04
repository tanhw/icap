/**
 * 
 */
package com.core.models.common;

import com.core.controller.cache.ColumnCache;

/**
 * 排序类
 * 
 * @ author sys
 * 
 */
public class Order implements java.io.Serializable {

	Class<?> model;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String column;
	private String orderType;

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	private Order(Class<?> t, String column, String orderType) {
		this.model = t;
		// this.modelName = model.getName();
		this.column = column;
		this.orderType = orderType;
	}

	public static Order desc(Class<?> t, String column) {
		Order order = new Order(t, column, "desc");
		return order;
	}

	public static Order asc(Class<?> t, String column) {
		Order order = new Order(t, column, "asc");
		return order;
	}

	public static Order desc(String column) {
		Order order = new Order(null, column, "desc");
		return order;
	}

	public static Order asc(String column) {
		Order order = new Order(null, column, "asc");
		return order;
	}

	public String toString() {
		String orderColumn = null;
		if (model == null) {
			orderColumn = ColumnCache.getColumnByProperty(this.column);
		} else {
			orderColumn = ColumnCache.getColumnByProperty(model, this.column);
		}
		if (orderColumn == null)
			return "";

		StringBuffer sb = new StringBuffer();
		sb.append(orderColumn).append(" ").append(this.orderType);

		return sb.toString();
	}

	public String getOrderField() {
		String orderColumn = null;
		if (model == null) {
			orderColumn = ColumnCache.getColumnByProperty(this.column);
		} else {
			orderColumn = ColumnCache.getColumnByProperty(model, this.column);
		}
		return orderColumn;
	}

	public String getModelName() {
		if (model == null)
			return null;
		return model.getSimpleName();
	}

}
