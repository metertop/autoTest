package com.dcits.business.system.service;

import com.dcits.business.base.service.BaseService;
import com.dcits.business.system.bean.DataDB;

public interface DataDBService extends BaseService<DataDB>{
	Integer getMaxDBId();
	
}
