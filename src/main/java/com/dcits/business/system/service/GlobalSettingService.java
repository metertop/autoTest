package com.dcits.business.system.service;

import com.dcits.business.base.service.BaseService;
import com.dcits.business.system.bean.GlobalSetting;

public interface GlobalSettingService extends BaseService<GlobalSetting>{
	 void updateSetting(String settingName,String settingValue);
}
