package com.dcits.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class InterceptorAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(InterceptorAction.class);
	//ajax调用返回的map
	private Map<String,Object> jsonMap=new HashMap<String,Object>();
	
		
	public String noLogin(){
		jsonMap.put("returnCode", 7);
		jsonMap.put("msg", "你还没有登陆或者登陆已失效,请重新登陆");
		logger.info("用户没有登录,请求不通过!");
		return SUCCESS;
		
	}
		
	public String noPower(){
		jsonMap.put("returnCode", 8);
		jsonMap.put("msg", "你没有权限进行此操作");
		logger.info("用户权限不够,请求不通过!");
		return SUCCESS;
	}
		
	public String error(){
		jsonMap.put("returnCode", 1);
		jsonMap.put("msg", "系统内部错误,请稍后再试");
		logger.error(ActionContext.getContext().getValueStack().findValue("exception"));
		logger.error("系统内部错误,请求失败!");
		return SUCCESS;
	}
	
	public String opDisable(){
		jsonMap.put("returnCode", 11);
		jsonMap.put("msg", "该操作接口已被设置禁止调用!");
		logger.info("该操作接口已被设置禁止调用!");
		return SUCCESS;
	}
	
	public String opNotfound(){
		jsonMap.put("returnCode", 13);
		jsonMap.put("msg", "未定义的操作接口");
		logger.info("不存在该接口!");
		return SUCCESS;
	}
	
	public String scriptUpload(){
		jsonMap.put("returnCode", 10);
		jsonMap.put("msg", "文件上传成功!");
		return SUCCESS;
	}
	
	public Map<String, Object> getJsonMap() {		
		return jsonMap;
	}	
}
