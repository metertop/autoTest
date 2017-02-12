package com.dcits.business.message.bean;
// default package

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.dcits.business.user.bean.User;


/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements Serializable{


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer messageId;
     private InterfaceInfo interfaceInfo;
     private User user;
     private Parameter parameter;
     private String messageName;
     private String requestUrl;
     private Timestamp createTime;
     private String status;
     private String lastModifyUser;
     private String parameterJson;

     private Set<MessageScene> scenes = new HashSet<MessageScene>();
     
     private String interfaceName;
     
     private String createUserName;
     
     @SuppressWarnings("unused")
	private Integer sceneNum = this.getSceneNum();

    // Constructors

    /** default constructor */
    public Message() {
    }

    
    /** full constructor */
    public Message(InterfaceInfo interfaceInfo, User user, Parameter parameter, String messageName, String requestUrl, Timestamp createTime, String status, String lastModifyUser,String parameterJson) {
        this.interfaceInfo = interfaceInfo;
        this.user = user;
        this.parameter = parameter;
        this.messageName = messageName;
        this.requestUrl = requestUrl;
        this.createTime = createTime;
        this.status = status;
        this.lastModifyUser = lastModifyUser;
        this.parameterJson = parameterJson;
    }

   
    // Property accessors

    
    
    
    public Integer getMessageId() {
        return this.messageId;
    }
    
    public String getInterfaceName() {
		return interfaceName;
	}


	public void setInterfaceName() {
		this.interfaceName = interfaceInfo.getInterfaceName();
	}


	public String getCreateUserName() {
		return createUserName;
	}


	public void setCreateUserName() {
		this.createUserName = user.getRealName();
	}


	public Integer getSceneNum() {
		return scenes.size();
	}


	public void setSceneNum(Integer sceneNum) {
		this.sceneNum = sceneNum;
	}


	public String getParameterJson() {
		return parameterJson;
	}


	public void setParameterJson(String parameterJson) {
		this.parameterJson = parameterJson;
	}


	public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
	
	@JSON(serialize=false)
    public InterfaceInfo getInterfaceInfo() {
        return this.interfaceInfo;
    }
    
    public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
        this.interfaceInfo = interfaceInfo;
    }

    @JSON(serialize=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @JSON(serialize=false)
    public Parameter getParameter() {
        return this.parameter;
    }
    
    
    
    @JSON(serialize=false)
    public Set<MessageScene> getScenes() {
		return scenes;
	}


	public void setScenes(Set<MessageScene> scenes) {
		this.scenes = scenes;
	}


	public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public String getMessageName() {
        return this.messageName;
    }
    
    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getRequestUrl() {
        return this.requestUrl;
    }
    
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @JSON(format="yyyy-MM-dd HH:mm:ss")
    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public void realStatus(){
    	if(this.status.equals("0")&&this.interfaceInfo.getStatus().equals("0")){
    		this.status = "0";
    	}else{
    		this.status ="1";
    	}
    }
    
    public String getLastModifyUser() {
        return this.lastModifyUser;
    }
    
    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }
   








}