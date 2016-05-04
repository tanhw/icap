package com.core.models;

import java.io.Serializable;

public class TBatTask implements Serializable {
    private String taskseq;

	private String taskdate;

	private String tasktype;

	private String reserved;

	private static final long serialVersionUID = 1L;

	public String getTaskseq() {
		return taskseq;
	}

	public void setTaskseq(String taskseq) {
		this.taskseq = taskseq;
	}

	public String getTaskdate() {
		return taskdate;
	}

	public void setTaskdate(String taskdate) {
		this.taskdate = taskdate;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	
}