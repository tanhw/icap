package com.pospserver.models;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 报文实体类
 * 
 * @author rain
 *
 */
@Service
public class ServerMessage {
	
	
	/**  应用类别**/
	private String appType;

	/** 软件软件版本号 **/
	private String msgVersion;

	/** 报文加密标志 **/
	private String msgEnc;

	/** 发送日期时间 **/
	private String reserve;
	
	/**  上送报文体内容 **/
	private Map<String,Object> requestBody;
	
	
	/**  返回报文体内容 **/
	private List<String> responseBody;
	
	/** 返回码**/
	private String RespCode;

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getMsgVersion() {
		return msgVersion;
	}

	public void setMsgVersion(String msgVersion) {
		this.msgVersion = msgVersion;
	}

	public String getMsgEnc() {
		return msgEnc;
	}

	public void setMsgEnc(String msgEnc) {
		this.msgEnc = msgEnc;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public String getRespCode() {
		return RespCode;
	}

	public void setRespCode(String respCode) {
		RespCode = respCode;
	}

	public Map<String, Object> getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(Map<String, Object> requestBody) {
		this.requestBody = requestBody;
	}

	public List<String> getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(List<String> responseBody) {
		this.responseBody = responseBody;
	}
	
}
