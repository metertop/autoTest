package com.dcits.business.message.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.business.base.action.BaseAction;
import com.dcits.business.message.bean.InterfaceInfo;
import com.dcits.business.message.bean.Parameter;
import com.dcits.business.message.service.ParameterService;
import com.dcits.constant.ReturnCodeConsts;
import com.dcits.util.JsonUtil;

/**
 * 接口自动化
 * 接口参数管理Action
 * @author xuwangcheng
 * @version 1.0.0.0,2017.2.13
 *
 */

@Controller
@Scope("prototype")
public class ParameterAction extends BaseAction<Parameter> {
	
	/**
	 * LOGGER
	 */
	private static Logger LOGGER = Logger.getLogger(ParameterAction.class);

	private static final long serialVersionUID = 1L;
	
	private ParameterService parameterService;
	
	@Autowired
	public void setParameterService(ParameterService parameterService) {
		super.setBaseService(parameterService);
		this.parameterService = parameterService;
	}
	
	
	/**
	 * 通过入参json报文{paramJson}批量导入接口参数
	 */
	private String paramsJson;
	
	/**
	 * 编辑参数属性
	 * 指定属性名
	 */
	private String attrName; 
	
	/**
	 * 编辑参数属性
	 * 指定要更新的属性值
	 */
	private String attrValue;
	
	/**
	 * 参数对应的接口id
	 */
	private Integer interfaceId;
	
	/**
	 * 根据指定的interfaceId接口id来获取下面的所有参数
	 * @return
	 */
	public String getParams() {
		List<Parameter> ps = new ArrayList<Parameter>();
		ps = parameterService.findByInterfaceId(interfaceId);		
		jsonMap.put("returnCode",ReturnCodeConsts.NO_RESULT_CODE);
		
		if (ps.size()>0) {
			jsonMap.put("returnCode", ReturnCodeConsts.SUCCESS_CODE);
			jsonMap.put("data", ps);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 根据传入的参数属性名称和属性值来更新指定参数的指定属性
	 */
	@Override
	public String edit() {
		parameterService.editProperty(id, attrName, attrValue);
		jsonMap.put("returnCode", ReturnCodeConsts.SUCCESS_CODE);
		
		return SUCCESS;
	}
	
	
	
	/**
	 * 根据传入的接口入参报文批量处理导入参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String batchImportParams() {
		Object[] jsonTree = null;
		try {
			jsonTree = (Object[]) JsonUtil.getJsonList(paramsJson, 3);
		} catch (Exception e) {
			LOGGER.error("解析json串失败!", e);			
		}
		jsonMap.put("returnCode", ReturnCodeConsts.INTERFACE_ILLEGAL_JSON_CODE);
		
		if (jsonTree != null) {			
			Map<String,String> valueMap = (Map<String, String>)jsonTree[3];
			List<String> paramList = (List<String>) jsonTree[0];
			List<String> typeList = (List<String>) jsonTree[1];
			List<String> pathList = (List<String>) jsonTree[2];

			Parameter param = null;
			for (int i = 0;i < paramList.size();i++) {
				param = new Parameter(paramList.get(i), "", valueMap.get(paramList.get(i)), pathList.get(i), typeList.get(i));				
				param.setInterfaceInfo((new InterfaceInfo()));
				param.getInterfaceInfo().setInterfaceId(interfaceId);
				parameterService.save(param);
			}
			jsonMap.put("returnCode", ReturnCodeConsts.SUCCESS_CODE);
		} 
		
		return SUCCESS;
	}
	
	
	/***************************************GET-SET****************************************************/
	
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
