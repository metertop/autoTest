package com.dcits.business.message.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.message.bean.Parameter;
import com.dcits.business.message.service.ParameterService;

/**
 * 接口参数Service接口实现
 * 
 * @author xuwangcheng
 * @version 1.0.0.0,2017.2.13
 * 
 */

@Service("parameterService")
public class ParameterServiceImpl extends BaseServiceImpl<Parameter> implements ParameterService {

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
