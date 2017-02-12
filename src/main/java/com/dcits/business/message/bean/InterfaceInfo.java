package com.dcits.business.message.bean;
// default package

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

import com.dcits.business.user.bean.User;


/**
 * InterfaceInfo entity. @author MyEclipse Persistence Tools
 */

public class InterfaceInfo implements Serializable{


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer interfaceId;
     private User user;
     private String interfaceName;
     private String interfaceCnName;
     private String requestUrlMock;
     private String requestUrlReal;
     private String interfaceType;
     private Timestamp createTime;
     private String status;
     private String lastModifyUser;
     
     private Set<Parameter> parameters=new HashSet<Parameter>();
     private Set<Message> messages=new HashSet<Message>();
    // Constructors

    /** default constructor */
    public InterfaceInfo() {
    }

	/** minimal constructor */
    public InterfaceInfo(String interfaceName) {
        this.interfaceName = interfaceName;
    }
    
    /** full constructor */
    public InterfaceInfo(User user, String interfaceName, String interfaceCnName, String requestUrlMock, String requestUrlReal, String interfaceType, Timestamp createTime, String status, String lastModifyUser) {
        this.user = user;
        this.interfaceName = interfaceName;
        this.interfaceCnName = interfaceCnName;
        this.requestUrlMock = requestUrlMock;
        this.requestUrlReal = requestUrlReal;
        this.interfaceType = interfaceType;
        this.createTime = createTime;
        this.status = status;
        this.lastModifyUser = lastModifyUser;
    }

   
    // Property accessors
    
    
    
    
    
    public Integer getInterfaceId() {
        return this.interfaceId;
    }
    
    @JSON(serialize=false)
    public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@JSON(serialize=false)
    public Set<Parameter> getParameters() {
		return parameters;
	}
    
    
	public void setParameters(Set<Parameter> parameters) {
		this.parameters = parameters;
	}

	public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public String getInterfaceName() {
        return this.interfaceName;
    }
    
    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceCnName() {
        return this.interfaceCnName;
    }
    
    public void setInterfaceCnName(String interfaceCnName) {
        this.interfaceCnName = interfaceCnName;
    }

    public String getRequestUrlMock() {
        return this.requestUrlMock;
    }
    
    public void setRequestUrlMock(String requestUrlMock) {
        this.requestUrlMock = requestUrlMock;
    }

    public String getRequestUrlReal() {
        return this.requestUrlReal;
    }
    
    public void setRequestUrlReal(String requestUrlReal) {
        this.requestUrlReal = requestUrlReal;
    }

    public String getInterfaceType() {
        return this.interfaceType;
    }
    
    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
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

    public String getLastModifyUser() {
        return this.lastModifyUser;
    }
    
    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }


   
    







}