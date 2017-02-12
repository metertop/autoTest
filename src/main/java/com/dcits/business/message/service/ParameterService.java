package com.dcits.business.message.service;

import java.util.List;

import com.dcits.business.base.service.BaseService;
import com.dcits.business.message.bean.Parameter;

public interface ParameterService extends BaseService<Parameter>{
	List<Parameter> findByInterfaceId(int interfaceId);
	void editProperty(int parameterId,String attrName,String attrValue);
}
