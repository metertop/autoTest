package com.dcits.business.message.bean;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;

public class SceneValidateRule implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer validateId;
	private MessageScene messageScene;
	private String parameterName;
	private String validateValue;
	private String getValueMethod;
	private String fullValidateFlag;
	private String complexFlag;
	private String status;
	private String mark;
	
		
	
	public SceneValidateRule(MessageScene messageScene, String parameterName,
			String validateValue, String getValueMethod,
			String fullValidateFlag, String complexFlag, String status,
			String mark) {
		super();
		this.messageScene = messageScene;
		this.parameterName = parameterName;
		this.validateValue = validateValue;
		this.getValueMethod = getValueMethod;
		this.fullValidateFlag = fullValidateFlag;
		this.complexFlag = complexFlag;
		this.status = status;
		this.mark = mark;
	}
	public SceneValidateRule() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getGetValueMethod() {
		return getValueMethod;
	}
	public void setGetValueMethod(String getValueMethod) {
		this.getValueMethod = getValueMethod;
	}
	public Integer getValidateId() {
		return validateId;
	}
	public void setValidateId(Integer validateId) {
		this.validateId = validateId;
	}
	
	@JSON(serialize=false)
	public MessageScene getMessageScene() {
		return messageScene;
	}
	public void setMessageScene(MessageScene messageScene) {
		this.messageScene = messageScene;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getValidateValue() {
		return validateValue;
	}
	public void setValidateValue(String validateValue) {
		this.validateValue = validateValue;
	}
	public String getFullValidateFlag() {
		return fullValidateFlag;
	}
	public void setFullValidateFlag(String fullValidateFlag) {
		this.fullValidateFlag = fullValidateFlag;
	}
	public String getComplexFlag() {
		return complexFlag;
	}
	public void setComplexFlag(String complexFlag) {
		this.complexFlag = complexFlag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	
}
