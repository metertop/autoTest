package com.dcits.business.message.service;

import java.util.List;

import com.dcits.business.base.service.BaseService;
import com.dcits.business.message.bean.InterfaceInfo;

public interface InterfaceInfoService extends BaseService<InterfaceInfo>{
	
	List<InterfaceInfo> findInterfaceByCondition(String condition);
	void changeStatus(int id,String status);
	InterfaceInfo findInterfaceByName(String interfaceName);
}
