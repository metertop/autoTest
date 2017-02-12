package com.dcits.business.message.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.message.bean.Parameter;
import com.dcits.business.message.dao.ParameterDao;

@Repository("parameterDao")
public class ParameterDaoImpl extends BaseDaoImpl<Parameter> implements ParameterDao{
	
	/**
	 * 根据interfaceId查找入参
	 * @param interfaceId
	 * @return
	 */
	public List<Parameter> findByInterfaceId(int interfaceId){
		return getSession().createQuery("from Parameter where interfaceInfo.interfaceId= :interfaceId").setInteger("interfaceId", interfaceId).list();
	}

	/**
	 * 更改某一个属性值
	 * @param parameterId
	 * @param attrName
	 * @param attrValue
	 */
	public void editProperty(int parameterId,String attrName,String attrValue) {
		String hsql = "update Parameter p set "+attrName+"= :attrValue where p.parameterId= :parameterId";
		getSession().createQuery(hsql).setString("attrValue", attrValue).setInteger("parameterId",parameterId).executeUpdate();		
	}
	
}
