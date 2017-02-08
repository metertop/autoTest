package com.dcits.business.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.user.bean.Role;
import com.dcits.business.user.dao.RoleDao;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{
	
	public Role get(String roleName){
		String hql = "From Role r where r.roleName=:roleName";
		return (Role) getSession().createQuery(hql).setString("roleName", roleName).setFirstResult(0).setMaxResults(1).uniqueResult();
	}
	
	public void changeUserRole(int roleId){
		String sql = "update at_user set role_id=3 where role_id="+roleId;
		getSession().createSQLQuery(sql).executeUpdate();
	}

}
