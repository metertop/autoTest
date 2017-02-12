package com.dcits.business.message.service.impl;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.message.bean.TestReport;
import com.dcits.business.message.service.TestReportService;

@Service("testReportService")
public class TestReportServiceImpl extends BaseServiceImpl<TestReport> implements TestReportService{

}
