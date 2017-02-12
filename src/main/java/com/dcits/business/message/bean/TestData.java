package com.dcits.business.message.bean;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;
// default package



/**
 * TestData entity. @author MyEclipse Persistence Tools
 */

public class TestData implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

     private Integer dataId;
     private MessageScene messageScene;
     private String paramsData;
     private String status;
     private String dataDiscr;


    // Constructors

    /** default constructor */
    public TestData() {
    }

    
    /** full constructor */
    public TestData(MessageScene messageScene, String paramsData, String status,String dataDiscr) {
        this.messageScene = messageScene;
        this.paramsData = paramsData;
        this.status = status;
        this.dataDiscr = dataDiscr;
    }

   
    // Property accessors

    
    
    public Integer getDataId() {
        return this.dataId;
    }
    
    public String getDataDiscr() {
		return dataDiscr;
	}


	public void setDataDiscr(String dataDiscr) {
		this.dataDiscr = dataDiscr;
	}


	public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }
    
    @JSON(serialize=false)
    public MessageScene getMessageScene() {
        return this.messageScene;
    }
    
    public void setMessageScene(MessageScene messageScene) {
        this.messageScene = messageScene;
    }

    public String getParamsData() {
        return this.paramsData;
    }
    
    public void setParamsData(String paramsData) {
        this.paramsData = paramsData;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
   








}