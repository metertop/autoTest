package com.dcits.business.message.bean;
// default package

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;


/**
 * 接口自动化
 * 报文场景
 * @author xuwangcheng
 * @version 1.0.0.0,2017.3.6
 */

public class MessageScene implements Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 场景id
	 */
	private Integer messageSceneId;
	/**
	 * 所属报文
	 */
	private Message message;
	/**
	 * 场景名
	 */
	private String sceneName;
	/**
	 * 验证规则标志
	 * 0   默认验证使用测试配置中的默认验证字符串
	 * 1  使用自定义的验证配置,需要验证多个参数具体的值
	 * 2  使用自定义的验证规则,直接验证整个返回串
	 */
	private String validateRuleFlag;
	/**
	 * 备注
	 */
	private String mark;
	/**
	 * 该测试场景对应的测试数据
	 */
	private Set<TestData> testDatas = new HashSet<TestData>();
	/**
	 * 对应测试集
	 */
	private Set<TestSet> testSets = new HashSet<TestSet>();
	 /**
	  * 测试结果
	  */
	private Set<TestResult> testResults = new HashSet<TestResult>();
	 /**
	  * 验证规则
	  */
	private Set<SceneValidateRule> rules = new HashSet<SceneValidateRule>();
	
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
			this.validateMethodStr = "全局默认";
			break;
		case "1":
			this.validateMethodStr = "入参验证";
			break;
		case "2":
			this.validateMethodStr = "全文验证";
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
    	for (TestData td:testDatas) {
    		if (td.getStatus().equals("1")) {
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