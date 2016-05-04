/**
 * 
 */
package com.core.models.common;

import java.io.Serializable;
import java.util.List;

import com.core.controller.cache.MessageHandler;
import com.toolbox.util.StringUtil;

/**
 * @ author sys
 *
 */
public class JsonDataWrapper<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean success;
	private String message;
	private String url;
	
	private Integer total;
	private Integer page;
	private List<T> rows;	
	
	private String other; //用于传输其他信息，预留
	private T obj;//预留，用于传输各种类型
	
	
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public JsonDataWrapper(){}
	
	public JsonDataWrapper(Boolean success,String message){
		this.success=success;
		if (this.success)
			this.message=MessageHandler.getSuccessMsgByCode(message);
		else{
			if (StringUtil.checkNull(false, message)){
				if (message.equals("E00001")) this.url="login.html";
			}
			if(message != null && (!message.equals("")) && message.length() != 6) {
				message = "E" + message;
			}
			this.message=MessageHandler.getErrorMsgByCode(message);
		}
	}
	
	public JsonDataWrapper(RollPage<T> rollPage){
		this.total=rollPage.getRecordSum();
		this.page=rollPage.getPageNum();
		this.rows=rollPage.getRecordList();
	}
	
	public JsonDataWrapper(List<T> list){
		this.rows=list;   
	}

	public Boolean getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message){
		this.message=message;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getTotal() {
		return total;
	}

	public Integer getPage() {
		return page;
	}

	public List<T> getRows() {
		return rows;
	}

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

}
