package com.dcits.business.message.bean;
// default package

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;


/**
 * MessageScene entity. @author MyEclipse Persistence Tools
 */

public class MessageScene implements Serializable{


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer messageSceneId;
     private Message message;
     private String sceneName;
     private String validateRuleFlag;
     private String mark;
     private Set<TestData> testDatas = new HashSet<TestData>();

     private Set<TestSet> testSets = new HashSet<TestSet>();
     
     private Set<TestResult> testResults = new HashSet<TestResult>();
     
     private Set<SceneValidateRule> rules = new HashSet<SceneValidateRule>();

     private String interfaceName;
     private String messageName;
     private String validateMethodStr;
     
     
     
     
     
     
     
    // Constructors

    /** default constructor */
    public MessageScene() {
    }

    
    /** full constructor */
    public MessageScene(Message message, String sceneName, String validateRuleFlag,String mark) {
        this.message = message;
        this.sceneName = sceneName;
        this.validateRuleFlag = validateRuleFlag;
        this.mark = mark;
    }

   
    // Property accessors
    
    
    public void setTestSets(Set<TestSet> testSets) {
		this.testSets = testSets;
	}
    
    public String getValidateMethodStr() {
		return validateMethodStr;
	}


	public void setValidateMethodStr() {
		switch (this.getValidateRuleFlag()) {
		case "0":
			this.validateMethodStr = "ȫ����֤";
			break;
		case "1":
			this.validateMethodStr = "�ڵ���֤";
			break;
		case "2":
			this.validateMethodStr = "ȫ����֤";
			break;
		}
	}


	@JSON(serialize=false)
    public Set<SceneValidateRule> getRules() {
		return rules;
	}


	public void setRules(Set<SceneValidateRule> rules) {
		this.rules = rules;
	}


	public String getValidateRuleFlag() {
		return validateRuleFlag;
	}


	public void setValidateRuleFlag(String validateRuleFlag) {
		this.validateRuleFlag = validateRuleFlag;
	}


	@JSON(serialize=false)
    public Set<TestResult> getTestResults() {
		return testResults;
	}


	public void setTestResults(Set<TestResult> testResults) {
		this.testResults = testResults;
	}


	public String getInterfaceName() {
		return interfaceName;
	}


	public void setInterfaceName() {
		this.interfaceName = message.getInterfaceInfo().getInterfaceName();
	}


	public String getMessageName() {
		return messageName;
	}


	public void setMessageName() {
		this.messageName = message.getMessageName();
	}


	@JSON(serialize=false)
    public Set<TestSet> getTestSets() {
		return testSets;
	}

    public Integer getMessageSceneId() {
        return this.messageSceneId;
    }
    
    public void setMessageSceneId(Integer messageSceneId) {
        this.messageSceneId = messageSceneId;
    }

    @JSON(serialize=false)
    public Message getMessage() {
        return this.message;
    }
    
    public void setMessage(Message message) {
        this.message = message;
    }

    public String getSceneName() {
        return this.sceneName;
    }
    
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getMark() {
        return this.mark;
    }
    
    public void setMark(String mark) {
        this.mark = mark;
    }

    @JSON(serialize=false)
    public Set<TestData> getTestDatas() {
    	Set<TestData> delTds=new HashSet<TestData>();
    	for(TestData td:testDatas){
    		if(td.getStatus().equals("1")){
    			delTds.add(td);
    		}
    	}
    	this.testDatas.removeAll(delTds);
        return this.testDatas;
    }
    
    public void setTestDatas(Set<TestData> testDatas) {
        this.testDatas = testDatas;
    }
   








}