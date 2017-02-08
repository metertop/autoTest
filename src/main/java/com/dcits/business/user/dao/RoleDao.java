package com.dcits.business.user.dao;

import com.dcits.business.base.dao.BaseDao;
import com.dcits.business.user.bean.Role;

public interface RoleDao extends BaseDao<Role>{
	 Role get(String roleName);
	
	 void changeUserRole(int roleId);

}
