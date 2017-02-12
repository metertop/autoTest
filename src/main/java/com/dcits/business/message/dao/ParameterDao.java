package com.dcits.business.message.dao;

import java.util.List;

import com.dcits.business.base.dao.BaseDao;
import com.dcits.business.message.bean.Parameter;

public interface ParameterDao extends BaseDao<Parameter>{
	List<Parameter> findByInterfaceId(int interfaceId);
	void editProperty(int parameterId,String attrName,String attrValue);
}
