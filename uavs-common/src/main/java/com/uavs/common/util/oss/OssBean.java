package com.uavs.common.util.oss;

public class OssBean {
	public String filePath;
	public String message;
	public boolean success;
	
	public OssBean(String filePath, String message, boolean success) {
		super();
		this.filePath = filePath;
		this.message = message;
		this.success = success;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
