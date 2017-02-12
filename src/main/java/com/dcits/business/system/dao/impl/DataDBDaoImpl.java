package com.dcits.business.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.system.bean.DataDB;
import com.dcits.business.system.dao.DataDBDao;

@Repository("dataDBDao")
public class DataDBDaoImpl extends BaseDaoImpl<DataDB> implements DataDBDao{

	public DataDB getMaxDBId(){
		String hql = "From DataDB d order by d.dbId desc";
		return (DataDB) getSession().createQuery(hql).setFirstResult(0).setMaxResults(1).uniqueResult();
	}
}
