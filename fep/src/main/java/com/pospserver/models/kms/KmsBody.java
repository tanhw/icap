package com.pospserver.models.kms;

/**
 * KMS 通讯
 * @author 
 *
 */
public class KmsBody {
	
     private String code; //返回代码 -0000-正常
     
     private String keyCode; //返回密钥代码  00-正常
     
     private String key; //密钥

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}
	
	
}
