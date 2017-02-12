package com.dcits.business.message.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.business.base.action.BaseAction;
import com.dcits.business.message.bean.Parameter;
import com.dcits.constant.ReturnCodeConstant;
import com.dcits.util.JsonUtil;

@Controller
@Scope("prototype")
public class ParameterAction extends BaseAction<Parameter>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String paramsJson;
	
	private String attrName; 
	
	private String attrValue;
	
	private Integer interfaceId;
	
	//参看接口的入参
	public String getParams(){
		List<Parameter> ps = new ArrayList<Parameter>();
		ps = parameterService.findByInterfaceId(interfaceId);
		if(ps.size()<1){
			jsonMap.put("returnCode",ReturnCodeConstant.NO_RESULT_CODE);
		}else{
			jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
			jsonMap.put("data", ps);
		}
		return SUCCESS;
	}
	
	//编辑参数属性 
	@Override
	public String edit(){
		parameterService.editProperty(id, attrName, attrValue);
		jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
		return SUCCESS;
	}
	
	
	
	//通过json导入入参节点
	@SuppressWarnings("unchecked")
	public String batchImportParams(){
		Object jsonTree[] = (Object[]) JsonUtil.getJsonList(paramsJson,3);
		if(jsonTree!=null){			
			Map<String,String> valueMap=(Map<String, String>)jsonTree[2];
			List<String> paramList=(List<String>) jsonTree[0];
			List<String> typeList=(List<String>) jsonTree[1];
			Parameter param = null;
			for(int i=0;i<paramList.size();i++){
				param=new Parameter(paramList.get(i),"",valueMap.get(paramList.get(i)),typeList.get(i),interfaceId);
				parameterService.save(param);
			}
			jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
		}else{
			jsonMap.put("returnCode", ReturnCodeConstant.INTERFACE_ILLEGAL_JSON_CODE);
		}
		return SUCCESS;
	}
	
	
	
	public void setParamsJson(String paramsJson) {
		this.paramsJson = paramsJson;
	}
	
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public void setInterfaceId(Integer interfaceId) {
		this.interfaceId = interfaceId;
	}

}
