package com.dcits.business.system.service.impl;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.system.bean.GlobalSetting;
import com.dcits.business.system.service.GlobalSettingService;

@Service("globalSettingService")
public class GlobalSettingServiceImpl extends BaseServiceImpl<GlobalSetting> implements GlobalSettingService{
	
	/**
	 * 更新单个全局设置项
	 * @param settingName
	 * @param settingValue
	 */
	@Override
	public void updateSetting(String settingName, String settingValue) {
		globalSettingDao.updateSetting(settingName, settingValue);		
	}

}
