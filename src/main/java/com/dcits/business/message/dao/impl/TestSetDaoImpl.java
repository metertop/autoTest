package com.dcits.business.message.dao.impl;

import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.message.bean.TestSet;
import com.dcits.business.message.dao.TestSetDao;


@Repository("testSetDao")
public class TestSetDaoImpl extends BaseDaoImpl<TestSet> implements TestSetDao{

}
