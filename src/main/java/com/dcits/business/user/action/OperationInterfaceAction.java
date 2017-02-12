package com.dcits.business.user.action;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.business.base.action.BaseAction;
import com.dcits.business.user.bean.OperationInterface;
import com.dcits.constant.ReturnCodeConstant;
import com.dcits.constant.SystemConstant;

@Controller
@Scope("prototype")
public class OperationInterfaceAction extends BaseAction<OperationInterface>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private Integer opType;
	
	
	//展示操作接口列表按Type
	public String listOp(){
		Integer opId = 0;
		switch (opType) {
		case 1:
			opId = SystemConstant.MESSAGE_OP_ID;
			break;
		case 2:
			opId = SystemConstant.WEB_OP_ID;
			break;
		case 3:
			jsonMap.put("data", new HashSet<OperationInterface>());
			jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
			return SUCCESS;
		case 4:
			opId = SystemConstant.SYSTEM_OP_ID;
			break;
		case 5:
			opId = SystemConstant.USER_OP_ID;
			break;
		}
		Set<OperationInterface> ops = operationInterfaceService.get(opId).getAllOis();
		for(OperationInterface op:ops){
			op.setParentOpName();
		}
		
		jsonMap.put("data", ops);
		jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
		return SUCCESS;
	}
	
	/**************************************************************************/
	
	public void setOpType(Integer opType) {
		this.opType = opType;
	}
	
	
}
