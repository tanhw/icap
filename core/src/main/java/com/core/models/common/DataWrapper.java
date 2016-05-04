/**
 * 
 */
package com.core.models.common;

import java.io.Serializable;


/**
 * 数据通讯返回类
 * @ author sys
 *
 */
public class DataWrapper<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String resultCode;
	
	private T resultData;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}

	
}
