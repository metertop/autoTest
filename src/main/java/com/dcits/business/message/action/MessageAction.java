package com.dcits.business.message.action;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.business.base.action.BaseAction;
import com.dcits.business.base.bean.PageModel;
import com.dcits.business.message.bean.Message;
import com.dcits.business.message.bean.Parameter;
import com.dcits.business.message.service.MessageService;
import com.dcits.business.message.service.ParameterService;
import com.dcits.business.user.bean.User;
import com.dcits.constant.ReturnCodeConsts;
import com.dcits.util.JsonUtil;
import com.dcits.util.PracticalUtils;
import com.dcits.util.StrutsUtils;

/**
 * 接口报文Action
 * 
 * @author xuwangcheng
 * @version 1.0.0.0,2017.2.17
 */

@Controller
@Scope("prototype")
public class MessageAction extends BaseAction<Message>{

	private static final long serialVersionUID = 1L;	
	
	private static final Logger LOGGER = Logger.getLogger(MessageAction.class.getName());
	
	/**报文对应的接口id*/
	private Integer interfaceId;
	
	private MessageService messageService;
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		super.setBaseService(messageService);
		this.messageService = messageService;
	}
	
	@Autowired
	private ParameterService parameterService;
	
	/**
	 * 分页获取指定接口下的报文列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String list() {

		Map<String,Object>  dt = StrutsUtils.getDTParameters();
		PageModel<Message> pu = null;
		if (interfaceId == null) {
			pu = messageService.findByPager(start, length
					, (String)dt.get("orderDataName"), (String)dt.get("orderType")
					, (String)dt.get("searchValue"), (List<String>)dt.get("dataParams"));
		} else {
			pu = messageService.findByPager(start, length
					, (String)dt.get("orderDataName"), (String)dt.get("orderType")
					, (String)dt.get("searchValue"), (List<String>)dt.get("dataParams"), interfaceId);
		}		
		
		jsonMap.put("draw", draw);
		jsonMap.put("data", processListData(pu.getDatas()));
		jsonMap.put("recordsTotal", pu.getRecordCount());		
		jsonMap.put("recordsFiltered", pu.getRecordCount());
		
		if (!((String)dt.get("searchValue")).equals("")) {
			jsonMap.put("recordsFiltered", pu.getDatas().size());
		}
		
		jsonMap.put("returnCode", ReturnCodeConsts.SUCCESS_CODE);
		
		return SUCCESS;
	}


	/**
	 * 格式化报文的入参json串
	 * @return
	 */
	public String format() {
		String returnJson = PracticalUtils.formatJsonStr(model.getParameterJson());		
		jsonMap.put("returnCode", ReturnCodeConsts.INTERFACE_ILLEGAL_JSON_CODE);
		if (returnJson != null) {
			jsonMap.put("returnCode", ReturnCodeConsts.SUCCESS_CODE);
			jsonMap.put("returnJson", returnJson);
		}	
		
		return SUCCESS;
	}
	
	
	/**
	 * 验证报文入参的正确性
	 * 判断依据是：报文中的所有节点是否都存在于对应接口的参数列表中
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String validateJson() {
		
		List<String> parameters = null;
		try {
			parameters = (List<String>) JsonUtil.getJsonList(model.getParameterJson(), 1);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("解析json失败", e);
			jsonMap.put("returnCode", ReturnCodeConsts.SYSTEM_ERROR_CODE);
			jsonMap.put("msg", "系统解析json出现错误,请稍后再试 !");
			return SUCCESS;
		}

		List<Parameter> ps = parameterService.findByInterfaceId(interfaceId);
		jsonMap.put("returnCode", ReturnCodeConsts.INTERFACE_ILLEGAL_JSON_CODE);//json格式不正确
		
		if (parameters != null) {
			boolean paramCorrectFlag = false;
			boolean allCorrectFlag = true;
			String msg = "入参节点:";
			for (String name:parameters) {
				for (Parameter p:ps) {
					if (p.getParameterIdentify().toUpperCase().equals(name.toUpperCase())) {
						paramCorrectFlag = true;
					}
				}
				if (!paramCorrectFlag) {
					allCorrectFlag = false;
					msg += "[" + name + "] ";
				} else {
					paramCorrectFlag = false;
				}
			}
			
			jsonMap.put("returnCode", ReturnCodeConsts.SUCCESS_CODE);//验证通过
			if (!allCorrectFlag) {				
				msg += "在接口参数列表中未定义,请检查!";
				jsonMap.put("returnCode", ReturnCodeConsts.INTERFACE_MESSAGE_ERROR_JSON_CODE);//验证不通过
				jsonMap.put("msg", msg);//验证不通过
			}			
		}
		
		return SUCCESS;
	}
	
	/**
	 * 增加和修改的方法
	 */
	@Override
	public String edit() {
		
		String returnJson = PracticalUtils.formatJsonStr(model.getParameterJson());	
		
		if (returnJson == null) {
			jsonMap.put("msg", "不是合法的json格式,请检查！");
			jsonMap.put("returnCode", ReturnCodeConsts.INTERFACE_ILLEGAL_JSON_CODE);		
			return SUCCESS;
		}
		
		User user = (User)(StrutsUtils.getSessionMap().get("user"));
		if (model.getMessageId() == null) {
			//增加			
			model.setCreateTime(new Timestamp(System.currentTimeMillis()));
			model.setUser(user);			
		}
		model.setLastModifyUser(user.getRealName());
		model.setParameterJson(returnJson);
		messageService.edit(model);
		
		jsonMap.put("returnCode", ReturnCodeConsts.SUCCESS_CODE);		
		return SUCCESS;
	}
	
	public void setInterfaceId(Integer interfaceId) {
		this.interfaceId = interfaceId;
	}
	
	
	
}
