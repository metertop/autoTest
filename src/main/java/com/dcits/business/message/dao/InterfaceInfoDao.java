package com.dcits.business.message.dao;

import java.util.List;

import com.dcits.business.base.dao.BaseDao;
import com.dcits.business.message.bean.InterfaceInfo;

public interface InterfaceInfoDao extends BaseDao<InterfaceInfo>{
	List<InterfaceInfo> findInterfaceByCondition(String condition);
	void changeStatus(int id,String status);
	InterfaceInfo findInterfaceByName(String interfaceName);
}
