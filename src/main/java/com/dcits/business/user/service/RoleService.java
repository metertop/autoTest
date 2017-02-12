package com.dcits.business.user.service;

import com.dcits.business.base.service.BaseService;
import com.dcits.business.user.bean.Role;

public interface RoleService extends BaseService<Role>{
	
	 Role get(String roleName);
	
	 void changeUserRole(int roleId);
	 
	 void del(int roleId);

}
