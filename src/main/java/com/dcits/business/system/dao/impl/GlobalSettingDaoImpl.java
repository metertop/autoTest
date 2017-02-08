package com.dcits.business.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.system.bean.GlobalSetting;
import com.dcits.business.system.dao.GlobalSettingDao;

@Repository("globalSettingDao")
public class GlobalSettingDaoImpl extends BaseDaoImpl<GlobalSetting> implements GlobalSettingDao{

	@Override
	public void updateSetting(String settingName, String settingValue) {
		String hql = "update GlobalSetting g set g.settingValue=:settingValue where g.settingName=:settingName";
		getSession().createQuery(hql).setString("settingValue", settingValue).setString("settingName",settingName).executeUpdate();		
	}

}
