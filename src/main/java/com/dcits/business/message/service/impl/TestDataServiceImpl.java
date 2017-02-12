package com.dcits.business.message.service.impl;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.message.bean.TestData;
import com.dcits.business.message.service.TestDataService;

@Service("testDataService")
public class TestDataServiceImpl extends BaseServiceImpl<TestData> implements TestDataService{

}
