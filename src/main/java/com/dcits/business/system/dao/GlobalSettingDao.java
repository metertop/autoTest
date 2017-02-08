package com.dcits.business.system.dao;

import com.dcits.business.base.dao.BaseDao;
import com.dcits.business.system.bean.GlobalSetting;

public interface GlobalSettingDao extends BaseDao<GlobalSetting>{
	
	 void updateSetting(String settingName,String settingValue);

}
