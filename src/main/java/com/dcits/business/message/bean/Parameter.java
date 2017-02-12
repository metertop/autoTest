package com.dcits.business.message.bean;

import java.io.Serializable;

import org.apache.struts2.json.annotations.JSON;


// default package



/**
 * Parameter entity. @author MyEclipse Persistence Tools
 */

public class Parameter implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    

     private Integer parameterId;
     private String parameterIdentify;
     private String parameterName;
     private String defaultValue;
     private String type;
     private InterfaceInfo interfaceInfo;

     
    // Constructors

    /** default constructor */
    public Parameter() {
    }

	/** minimal constructor */
    public Parameter(String parameterIdentify) {
        this.parameterIdentify = parameterIdentify;
    }
    
    /** full constructor */
    public Parameter(String parameterIdentify, String parameterName, String defaultValue, String type , Integer interfaceId) {
        this.parameterIdentify = parameterIdentify;
        this.parameterName = parameterName;
        this.defaultValue = defaultValue;
        this.interfaceInfo.setInterfaceId(interfaceId);
    }

   
    // Property accessors

    public Integer getParameterId() {
        return this.parameterId;
    }
    
    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }

    public String getParameterIdentify() {
        return this.parameterIdentify;
    }
    
    public void setParameterIdentify(String parameterIdentify) {
        this.parameterIdentify = parameterIdentify;
    }

    public String getParameterName() {
        return this.parameterName;
    }
    
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }
    
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public void setInterfaceInfo(InterfaceInfo interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}
   
    @JSON(serialize=false)
    public InterfaceInfo getInterfaceInfo() {
		return interfaceInfo;
	}

	@Override
	public String toString() {
		return "Parameter [parameterId=" + parameterId + ", parameterIdentify="
				+ parameterIdentify + ", parameterName=" + parameterName
				+ ", defaultValue=" + defaultValue + ", type=" + type
				+ ", interfaceInfo=" + interfaceInfo + "]";
	}
    
   
    

}