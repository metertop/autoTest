package com.dcits.business.message.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.dcits.business.user.bean.User;

public class TestReport implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reportId;
	private String testMode;
	private Integer sceneNum;
	private Integer successNum;
	private Integer failNum;
	private Integer stopNum;
	private String finishFlag;
	private Timestamp startTime;
	private Timestamp finishTime;
	private User user;
	
	private String createUserName;
	
	private String setName;
	
	private Set<TestResult> trs = new HashSet<TestResult>();
	
	public TestReport(String testMode, String finishFlag,
			Timestamp startTime, User user) {
		super();
		this.testMode = testMode;
		this.finishFlag = finishFlag;
		this.startTime = startTime;
		this.user = user;
	}
	public TestReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
	public String getSetName() {
		return setName;
	}
	public void setSetName(String setName) {
		this.setName = setName;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName() {
		this.createUserName = user.getRealName();
	}
	@JSON(serialize=false)
	public Set<TestResult> getTrs() {
		return trs;
	}
	public void setTrs(Set<TestResult> trs) {
		this.trs = trs;
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public String getTestMode() {
		return testMode;
	}
	public void setTestMode(String testMode) {
		this.testMode = testMode;
	}
	
	////////////////////////////////////
	public void setSceneNum(){
		this.sceneNum=this.trs.size();
		this.successNum=0;
		this.failNum=0;
		this.stopNum=0;
		for(TestResult tr:this.trs){
			switch (tr.getRunStatus()) {
			case "0":
				this.successNum++;
				break;
			case "1":
				this.failNum++;
				break;
			case "2":
				this.stopNum++;
				break;
			}
		}
	}	
	public Integer getSceneNum() {
		return sceneNum;
	}

	public Integer getSuccessNum() {
		return successNum;
	}

	public Integer getFailNum() {
		return failNum;
	}

	public Integer getStopNum() {
		return stopNum;
	}

	///////////////////////////////////
	
	
	public String getFinishFlag() {
		return finishFlag;
	}
	public void setFinishFlag(String finishFlag) {
		this.finishFlag = finishFlag;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}
	
	@JSON(serialize=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
