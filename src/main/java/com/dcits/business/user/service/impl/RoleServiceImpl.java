package com.dcits.business.user.service.impl;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.user.bean.Role;
import com.dcits.business.user.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	@Override
	public Role get(String roleName) {
		// TODO Auto-generated method stub
		return roleDao.get(roleName);
	}

	@Override
	public void changeUserRole(int roleId) {
		// TODO Auto-generated method stub
		roleDao.changeUserRole(roleId);
		
	}

}
