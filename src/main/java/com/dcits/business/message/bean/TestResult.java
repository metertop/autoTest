package com.dcits.business.message.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;

public class TestResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer resultId;
	private MessageScene messageScene;
	private TestReport testReport;
	private String requestUrl;
	private String requestMessage;
	private String responseMessage;
	private Integer useTime;
	private String runStatus;
	private String statusCode;
	private Timestamp opTime;
	private String messageInfo;
	private String mark;
	
	public TestResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestResult(MessageScene messageScene,String messageInfo,
			String requestUrl, String requestMessage, String responseMessage,
			Integer useTime, String runStatus, String statusCode,
			Timestamp opTime,String mark) {
		super();
		this.messageScene = messageScene;
		this.messageInfo = messageInfo;
		this.requestUrl = requestUrl;
		this.requestMessage = requestMessage;
		this.responseMessage = responseMessage;
		this.useTime = useTime;
		this.runStatus = runStatus;
		this.statusCode = statusCode;
		this.opTime = opTime;
		this.mark = mark;
	}

	
	public String getMark() {
		return mark;
	}
	
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	public String getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}

	public Integer getResultId() {
		return resultId;
	}

	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	
	@JSON(serialize=false)
	public MessageScene getMessageScene() {
		return messageScene;
	}

	public void setMessageScene(MessageScene messageScene) {
		this.messageScene = messageScene;
	}

	@JSON(serialize=false)
	public TestReport getTestReport() {
		return testReport;
	}

	public void setTestReport(TestReport testReport) {
		this.testReport = testReport;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Integer getUseTime() {
		return useTime;
	}

	public void setUseTime(Integer useTime) {
		this.useTime = useTime;
	}

	public String getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
		this.runStatus = runStatus;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getOpTime() {
		return opTime;
	}

	public void setOpTime(Timestamp opTime) {
		this.opTime = opTime;
	}
	
	
	
}
