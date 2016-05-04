/**
 * 
 */
package com.constant;

/**
 * 返回代码 常量
 * @ author sys
 *
 */
public enum RespCodeConstant {

	/**
	 * 成功
	 */
	Success("00000"),
	
	/**
	 * 失败、错误
	 */
	Error("99999")
	;
	
	String code;
	
	RespCodeConstant(String code){
		this.code=code;
	}
	
	@Override
	public String toString() {
		return this.code;
	}
	
	public String toString(String errMessage) {
		if (errMessage == null || errMessage.equals("")) errMessage="E00000";
		if (errMessage.length()!=6) errMessage="E99999";
		
		errMessage=errMessage.replaceFirst("E", "");
		
		return errMessage;
	}
	
}
