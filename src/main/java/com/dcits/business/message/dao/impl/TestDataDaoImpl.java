package com.dcits.business.message.dao.impl;

import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.message.bean.TestData;
import com.dcits.business.message.dao.TestDataDao;

@Repository("testDataDao")
public class TestDataDaoImpl extends BaseDaoImpl<TestData> implements TestDataDao{

}
