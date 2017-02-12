package com.dcits.business.message.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.dcits.business.user.bean.User;



public class TestSet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer setId;
	private String setName;
	private User user;
	private Timestamp createTime;
	private String status;
	private String mark;
	
	private String userName;	
	private Integer sceneNum;
	
	
	private Set<MessageScene> ms = new HashSet<MessageScene>();

	public TestSet(String setName, User user, Timestamp createTime,
			String status, String mark) {
		super();
		this.setName = setName;
		this.user = user;
		this.createTime = createTime;
		this.status = status;
		this.mark = mark;
	}

	public TestSet() {
	}

	
	
	
	public Integer getSceneNum() {
		return sceneNum;
	}

	public void setSceneNum() {
		this.sceneNum = this.getMs().size();
	}

	public void setUserName() {
		this.userName = user.getRealName();
	}
	
	public String getUserName() {
		return userName;
	}
	
	public Integer getSetId() {
		return setId;
	}

	public void setSetId(Integer setId) {
		this.setId = setId;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	@JSON(serialize=false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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

	@JSON(serialize=false)
	public Set<MessageScene> getMs() {
		return ms;
	}

	public void setMs(Set<MessageScene> ms) {
		this.ms = ms;
	}
	
	
}
