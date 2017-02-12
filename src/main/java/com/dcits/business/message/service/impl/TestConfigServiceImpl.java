package com.dcits.business.message.service.impl;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.message.bean.TestConfig;
import com.dcits.business.message.service.TestConfigService;

@Service("testConfigService")
public class TestConfigServiceImpl extends BaseServiceImpl<TestConfig> implements TestConfigService{

}
