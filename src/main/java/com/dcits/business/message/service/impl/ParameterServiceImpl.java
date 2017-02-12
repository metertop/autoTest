package com.dcits.business.message.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.message.bean.Parameter;
import com.dcits.business.message.service.ParameterService;

@Service("parameterService")
public class ParameterServiceImpl extends BaseServiceImpl<Parameter> implements ParameterService{

	@Override
	public List<Parameter> findByInterfaceId(int interfaceId) {
		// TODO Auto-generated method stub
		return parameterDao.findByInterfaceId(interfaceId);
	}

	@Override
	public void editProperty(int parameterId, String attrName, String attrValue) {
		// TODO Auto-generated method stub
		parameterDao.editProperty(parameterId, attrName, attrValue);
	}

}
