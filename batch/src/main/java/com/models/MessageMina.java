package com.models;

public class MessageMina {

	/* 消息包的长度 */
	private int length;

	/* 消息包的内容，不含头部的长度 */
	private byte[] messagbody;

	/* 消息包的对端ip */
	private String remoteip;

	/* 消息包的对端port */
	private int remoteport;

	/* TPDU 头 */
	private byte[] tpdu;

	/* TPDU 头长度 */
	private int tpduLength;
	
	/* 请求响应类型 */
	private Boolean type;
	
	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public int getTpduLength() {
		return tpduLength;
	}

	public void setTpduLength(int tpduLength) {
		this.tpduLength = tpduLength;
	}

	public byte[] getTpdu() {
		return tpdu;
	}

	public void setTpdu(byte[] tpdu) {
		this.tpdu = tpdu;
	}

	public String getRemoteip() {
		return remoteip;
	}

	public void setRemoteip(String remoteip) {
		this.remoteip = remoteip;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte[] getMessagbody() {
		return messagbody;
	}

	public void setMessagbody(byte[] messagbody) {
		if (messagbody != null) {
			this.messagbody = new byte[messagbody.length];
			System.arraycopy(messagbody, 0, this.messagbody, 0, messagbody.length);
		}
	}

	public int getRemoteport() {
		return remoteport;
	}

	public void setRemoteport(int remoteport) {
		this.remoteport = remoteport;
	}
}
