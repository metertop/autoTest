package com.dcits.business.system.dao;

import com.dcits.business.base.dao.BaseDao;
import com.dcits.business.system.bean.DataDB;

public interface DataDBDao extends BaseDao<DataDB>{
	DataDB getMaxDBId();
}
