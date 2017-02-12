package com.dcits.business.message.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.message.bean.InterfaceInfo;
import com.dcits.business.message.service.InterfaceInfoService;

@Service("interfaceInfoImpl")
public class InterfaceInfoServiceImpl extends BaseServiceImpl<InterfaceInfo> implements InterfaceInfoService{

	@Override
	public List<InterfaceInfo> findInterfaceByCondition(String condition) {
		// TODO Auto-generated method stub
		return interfaceInfoDao.findInterfaceByCondition(condition);
	}

	@Override
	public void changeStatus(int id, String status) {
		interfaceInfoDao.changeStatus(id, status);
		
	}

	@Override
	public InterfaceInfo findInterfaceByName(String interfaceName) {
		// TODO Auto-generated method stub
		return interfaceInfoDao.findInterfaceByName(interfaceName);
	}
	
	
}
