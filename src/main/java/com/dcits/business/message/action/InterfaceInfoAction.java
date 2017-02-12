package com.dcits.business.message.action;

import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.business.base.action.BaseAction;
import com.dcits.business.message.bean.InterfaceInfo;
import com.dcits.business.user.bean.User;
import com.dcits.constant.ReturnCodeConstant;
import com.dcits.util.StrutsUtils;

@Controller
@Scope("prototype")
public class InterfaceInfoAction extends BaseAction<InterfaceInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String checkNameFlag;
	
	//更新接口
	@Override
	public String edit(){
		User user=(User)StrutsUtils.getSessionMap().get("user");
		//判断接口名是否重复
		checkInterfaceName();
		if(!checkNameFlag.equals("true")){
			jsonMap.put("returnCode", ReturnCodeConstant.NAME_EXIST_CODE);
			jsonMap.put("msg", "该接口名已存在,请更换!");
			return SUCCESS;
		}
		if(model.getInterfaceId() == null){
			//增加									
			model.setCreateTime(new Timestamp(System.currentTimeMillis()));
			model.setUser(user);				
		}
		model.setLastModifyUser(user.getRealName());	
		interfaceInfoService.edit(model);
		jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);					
		return SUCCESS;
	}
	
	public String checkName(){
		checkInterfaceName();
		return "check";
	}
	
	
	
	public void checkInterfaceName(){
		InterfaceInfo info = interfaceInfoService.findInterfaceByName(model.getInterfaceName());
		if(model.getInterfaceId() == null){
			checkNameFlag = (info == null)?"true":"重复的接口名";
		}else{
			checkNameFlag = (info != null && info.getInterfaceId() != model.getInterfaceId())?"重复的接口名":"true";
		}
	}
	
	
	public String getCheckNameFlag() {
		return checkNameFlag;
	}
}
