package com.dcits.business.message.bean;

import java.io.Serializable;

public class TestConfig implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer configId;
	private Integer userId;
	private String requestUrlFlag;
	private Integer connectTimeOut;
	private Integer readTimeOut;
	private String httpMethodFlag;
	private String validateString;
	private String checkDataFlag;
	private String backgroundExecFlag;
	
	public TestConfig(Integer userId, String requestUrlFlag,
			Integer connectTimeOut, Integer readTimeOut, String httpMethodFlag,
			String validateString, String checkDataFlag,
			String backgroundExecFlag) {
		super();
		this.userId = userId;
		this.requestUrlFlag = requestUrlFlag;
		this.connectTimeOut = connectTimeOut;
		this.readTimeOut = readTimeOut;
		this.httpMethodFlag = httpMethodFlag;
		this.validateString = validateString;
		this.checkDataFlag = checkDataFlag;
		this.backgroundExecFlag = backgroundExecFlag;
	}

	public TestConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRequestUrlFlag() {
		return requestUrlFlag;
	}

	public void setRequestUrlFlag(String requestUrlFlag) {
		this.requestUrlFlag = requestUrlFlag;
	}

	public Integer getConnectTimeOut() {
		return connectTimeOut;
	}

	public void setConnectTimeOut(Integer connectTimeOut) {
		this.connectTimeOut = connectTimeOut;
	}

	public Integer getReadTimeOut() {
		return readTimeOut;
	}

	public void setReadTimeOut(Integer readTimeOut) {
		this.readTimeOut = readTimeOut;
	}

	public String getHttpMethodFlag() {
		return httpMethodFlag;
	}

	public void setHttpMethodFlag(String httpMethodFlag) {
		this.httpMethodFlag = httpMethodFlag;
	}

	public String getValidateString() {
		return validateString;
	}

	public void setValidateString(String validateString) {
		this.validateString = validateString;
	}

	public String getCheckDataFlag() {
		return checkDataFlag;
	}

	public void setCheckDataFlag(String checkDataFlag) {
		this.checkDataFlag = checkDataFlag;
	}

	public String getBackgroundExecFlag() {
		return backgroundExecFlag;
	}

	public void setBackgroundExecFlag(String backgroundExecFlag) {
		this.backgroundExecFlag = backgroundExecFlag;
	}

	@Override
	public String toString() {
		return "TestConfig [configId=" + configId + ", userId=" + userId
				+ ", requestUrlFlag=" + requestUrlFlag + ", connectTimeOut="
				+ connectTimeOut + ", readTimeOut=" + readTimeOut
				+ ", httpMethodFlag=" + httpMethodFlag + ", validateString="
				+ validateString + ", checkDataFlag=" + checkDataFlag
				+ ", backgroundExecFlag=" + backgroundExecFlag + "]";
	}
	
	
	
	
}
