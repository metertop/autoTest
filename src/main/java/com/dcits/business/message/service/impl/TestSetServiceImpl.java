package com.dcits.business.message.service.impl;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.message.bean.TestSet;
import com.dcits.business.message.service.TestSetService;


@Service("testSetService")
public class TestSetServiceImpl extends BaseServiceImpl<TestSet> implements TestSetService{

}
