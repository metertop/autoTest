package com.dcits.business.system.service.impl;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.system.bean.DataDB;
import com.dcits.business.system.service.DataDBService;

@Service("dataDBService")
public class DataDBServiceImpl extends BaseServiceImpl<DataDB> implements DataDBService{
	/**
	 * 自定义dbId
	 * @return
	 */
	public Integer getMaxDBId(){
		DataDB db = dataDBDao.getMaxDBId();
		if(db==null){
			return 9999990;
		}else{
			return db.getDbId()+1;
		}
	}
}
