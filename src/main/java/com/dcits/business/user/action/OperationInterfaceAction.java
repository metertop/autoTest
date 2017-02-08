package com.dcits.business.user.action;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.business.base.action.BaseAction;
import com.dcits.business.user.bean.OperationInterface;

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
			opId = 2;
			break;
		case 2:
			opId = 85;
			break;
		case 3:
			jsonMap.put("data", new HashSet<OperationInterface>());
			jsonMap.put("returnCode", 0);
			return SUCCESS;
		case 4:
			opId = 63;
			break;
		case 5:
			opId = 70;
			break;
		}
		Set<OperationInterface> ops = operationInterfaceService.get(opId).getAllOis();
		for(OperationInterface op:ops){
			op.setParentOpName();
		}
		
		jsonMap.put("data", ops);
		jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	/**************************************************************************/
	
	public void setOpType(Integer opType) {
		this.opType = opType;
	}
	
	
}
