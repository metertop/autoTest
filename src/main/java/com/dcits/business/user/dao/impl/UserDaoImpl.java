package com.dcits.business.user.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dcits.business.base.dao.impl.BaseDaoImpl;
import com.dcits.business.user.bean.User;
import com.dcits.business.user.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
	/**
	 * 匹配进行登陆的用户
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@Override
	public User login(String userName,String passWord){
		// 创建HQL语句
		Query query = getSession()
				.createQuery(
						"from User u where u.username=:userName and u.password=:passWord");
		// 设置HQL语句中的参数
		query.setString("userName", userName);
		query.setString("passWord", passWord);
		// 执行HQL语句
		return (User) query.uniqueResult();
	}
	
	public void resetPasswd(Integer userId,String passwd){
		String hql = "update User u set u.password=:passwd where u.userId=:userId";
		getSession().createQuery(hql).setString("passwd", passwd).setInteger("userId", userId).executeUpdate();
		
	}
	
	public void lockUser(Integer userId,String status){
		String hql = "update User u set u.status=:status where u.userId=:userId";
		getSession().createQuery(hql).setInteger("userId", userId).setString("status",status).executeUpdate();
	}
	
	public User validateUsername(String username){		
		String hql = "From User u where u.username=:username";
		return (User) getSession().createQuery(hql).setString("username", username).uniqueResult();
	}
	
	public User validateUsername(String username,Integer userId){
		String hql = "From User u where u.username=:username and u.userId<>:userId";
		return (User) getSession().createQuery(hql).setString("username", username).setInteger("userId", userId).uniqueResult();
	}
	
	public void updateRealName(String realName,Integer userId){
		String  hql = "update User u set u.realName=:realName where u.userId=:userId";
		getSession().createQuery(hql).setString("realName", realName).setInteger("userId",userId).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByRealName(String realName){
		String hql = "From User u where u.realName like :realName";
		return getSession().createQuery(hql).setString("realName","%"+realName+"%").setCacheable(true).list();
	}
}
