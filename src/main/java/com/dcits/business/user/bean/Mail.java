package com.dcits.business.user.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;

public class Mail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer mailId;
	private User receiveUser;
	private User sendUser;
	private String ifValidate;
	private String mailInfo;
	private String sendStatus;
	private String readStatus;
	private Timestamp sendTime;
	
	private String sendUserName;
	private String receiveUserName;
	
	public Mail() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Mail(User receiveUser, User sendUser, String ifValidate,
			String mailInfo, String sendStatus, String readStatus,
			Timestamp sendTime, String sendUserName, String receiveUserName) {
		super();
		this.receiveUser = receiveUser;
		this.sendUser = sendUser;
		this.ifValidate = ifValidate;
		this.mailInfo = mailInfo;
		this.sendStatus = sendStatus;
		this.readStatus = readStatus;
		this.sendTime = sendTime;
		this.sendUserName = sendUserName;
		this.receiveUserName = receiveUserName;
	}
	
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Timestamp getSendTime() {
		return sendTime;
	}


	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}


	public String getSendUserName() {
		return sendUserName;
	}


	public void setSendUserName() {
		this.sendUserName = "ϵͳ";
		if(this.getSendUser()!=null){
			this.sendUserName = this.getSendUser().getRealName();
		}
		
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName() {
		this.receiveUserName = "";
		if(this.getReceiveUser()!=null){
			this.receiveUserName = this.getReceiveUser().getRealName();
		}
		
	}


	public Integer getMailId() {
		return mailId;
	}


	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	

	@JSON(serialize=false)
	public User getReceiveUser() {
		return receiveUser;
	}





	public void setReceiveUser(User receiveUser) {
		this.receiveUser = receiveUser;
	}




	@JSON(serialize=false)
	public User getSendUser() {
		return sendUser;
	}





	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}





	public String getIfValidate() {
		return ifValidate;
	}


	public void setIfValidate(String ifValidate) {
		this.ifValidate = ifValidate;
	}


	public String getMailInfo() {
		return mailInfo;
	}


	public void setMailInfo(String mailInfo) {
		this.mailInfo = mailInfo;
	}


	public String getSendStatus() {
		return sendStatus;
	}


	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}


	public String getReadStatus() {
		return readStatus;
	}


	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}
	
	
	
}
