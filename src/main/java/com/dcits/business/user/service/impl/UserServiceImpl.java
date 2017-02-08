package com.dcits.business.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dcits.business.base.service.impl.BaseServiceImpl;
import com.dcits.business.user.bean.User;
import com.dcits.business.user.service.UserService;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Override
	public User login(String userName, String passWord) {
		// TODO Auto-generated method stub
		return userDao.login(userName, passWord);
	}

	@Override
	public void resetPasswd(Integer userId, String passwd) {
		// TODO Auto-generated method stub
		userDao.resetPasswd(userId, passwd);
	}

	@Override
	public void lockUser(Integer userId, String status) {
		// TODO Auto-generated method stub
		userDao.lockUser(userId, status);
	}

	@Override
	public User validateUsername(String username, Integer userId) {		
		if(userId==null){
			return userDao.validateUsername(username);
		}
		return userDao.validateUsername(username,userId);
	}

	@Override
	public void updateRealName(String realName, Integer userId) {
		// TODO Auto-generated method stub
		userDao.updateRealName(realName, userId);
		
	}

	@Override
	public List<User> findByRealName(String realName) {
		// TODO Auto-generated method stub
		return userDao.findByRealName(realName);
	}

	
}
