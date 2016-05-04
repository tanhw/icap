package com.pospserver.models.kms;

/**
 * KMS 通讯
 * 
 * @author
 *
 */
public class KmsHeader {
	private String unitId; // 机构ID

	private String serviceId; // 接口ID

	private String keyId; // 密钥ID

	private String encryId; // 加密机ID

	private String txnType; // 交易类型
	
	private String mainKey; //主密钥
	
	private String workKey;//工作密钥
	
	private String mac;//	MAC
	
	private String macData; //mac数据 


	public String getMainKey() {
		return mainKey;
	}


	public void setMainKey(String mainKey) {
		this.mainKey = mainKey;
	}

	/** KMS报文体 **/
	private KmsBody body;

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public String getEncryId() {
		return encryId;
	}

	public void setEncryId(String encryId) {
		this.encryId = encryId;
	}


	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public KmsBody getBody() {
		return body;
	}

	public void setBody(KmsBody body) {
		this.body = body;
	}
	public String getWorkKey() {
		return workKey;
	}

	public void setWorkKey(String workKey) {
		this.workKey = workKey;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	

	public String getMacData() {
		return macData;
	}


	public void setMacData(String macData) {
		this.macData = macData;
	}

	
}
