package com.dcits.business.message.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.struts2.json.annotations.JSON;

import com.dcits.business.user.bean.User;

public class InterfaceMock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer mockId;
	private String interfaceName;
	private String mockUrl;
	private String requestJson;
	private String responseJson;
	private Timestamp createTime;
	private User user;
	private String ifValidate;
	private Integer callCount;
	private String status;
	
	private String createUserName;
	
	public InterfaceMock(String interfaceName, String mockUrl,
			String requestJson, String responseJson, Timestamp createTime,
			User user, String ifValidate, Integer callCount, String status) {
		super();
		this.interfaceName = interfaceName;
		this.mockUrl = mockUrl;
		this.requestJson = requestJson;
		this.responseJson = responseJson;
		this.createTime = createTime;
		this.user = user;
		this.ifValidate = ifValidate;
		this.callCount = callCount;
		this.status = status;
	}

	
	
	public Integer getCallCount() {
		return callCount;
	}



	public void setCallCount(Integer callCount) {
		this.callCount = callCount;
	}



	public String getCreateUserName() {
		return createUserName;
	}



	public void setCreateUserName() {
		this.createUserName = user.getRealName();
	}



	public InterfaceMock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getMockId() {
		return mockId;
	}

	public void setMockId(Integer mockId) {
		this.mockId = mockId;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getMockUrl() {
		return mockUrl;
	}

	public void setMockUrl(String mockUrl) {
		this.mockUrl = mockUrl;
	}

	public String getRequestJson() {
		return requestJson;
	}

	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

	public String getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@JSON(serialize=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIfValidate() {
		return ifValidate;
	}

	public void setIfValidate(String ifValidate) {
		this.ifValidate = ifValidate;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
