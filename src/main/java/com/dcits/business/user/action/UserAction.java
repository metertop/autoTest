package com.dcits.business.user.action;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.SessionMap;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.business.base.action.BaseAction;
import com.dcits.business.user.bean.Role;
import com.dcits.business.user.bean.User;
import com.dcits.util.MD5Util;
import com.dcits.util.StrutsUtils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	private String mode;
	
	private Integer roleId;

	private static Logger logger = Logger.getLogger(UserAction.class);
	
	//用户登陆
	public String toLogin(){
	
		model = userService.login(model.getUsername(), MD5Util.code(model.getPassword()));
		User user = (User)StrutsUtils.getSessionMap().get("user");
		int returnCode;
		String msg;
		if(model!=null){
			if(user!=null&&user.getUserId()==model.getUserId()){
				jsonMap.put("returnCode", 4);
				jsonMap.put("msg", "你已登录该账号,请切换至不同的账号!");
				return SUCCESS;
			}
			if(model.getStatus().equals("0")){
				returnCode=0;
				msg="";
				//将用户信息放入session中
				StrutsUtils.getSessionMap().put("user", model);			
				model.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
				userService.edit(model);
				logger.info("用户"+model.getRealName()+"[ID="+model.getUserId()+"]"+"登录成功!");
			}else{
				returnCode=2;
				msg="你的账号已被锁定,请联系管理员进行解锁。";
			}
			
		}else{
			returnCode=1;
			msg="账号或密码不正确,请重新输入!";
		}
		jsonMap.put("returnCode", returnCode);
		jsonMap.put("msg", msg);
		return SUCCESS;
		
	}
	
	//用户登出
	@SuppressWarnings("rawtypes")
	public String logout(){
		logger.info("用户"+((User)StrutsUtils.getSessionMap().get("user")).getRealName()+"已登出!");
		((SessionMap)StrutsUtils.getSessionMap()).invalidate();
		jsonMap.put("returnCode", 0);			
		return SUCCESS;
	}
	
	//判断用户是否登陆
	public String judgeLogin(){
		User user=(User)StrutsUtils.getSessionMap().get("user");
		if(user!=null){
			jsonMap.put("returnCode", 0);
		}else{
			jsonMap.put("returnCode", 1);
		}
		return SUCCESS;

	}
	
	//获取已登录在线用户的基本信息
	public String getLoginUserInfo(){
		
		User user=(User)StrutsUtils.getSessionMap().get("user");		
		
		if(user!=null){
			jsonMap.put("data", user);			
			jsonMap.put("returnCode", 0);
		}else{
			jsonMap.put("returnCode", 1);
		}
		return SUCCESS;
	}
	
	//登录用户修改自己的真实姓名
	public String editMyName(){
		User user = (User)StrutsUtils.getSessionMap().get("user");		
		userService.updateRealName(model.getRealName(), user.getUserId());
		user.setRealName(model.getRealName());
		jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	//验证当前密码
	public String verifyPasswd(){
		User user=(User)StrutsUtils.getSessionMap().get("user");
		if(user.getPassword().equals(MD5Util.code(model.getPassword()))){
			jsonMap.put("returnCode", 0);			
		}else{
			jsonMap.put("returnCode", 2);
			jsonMap.put("msg", "密码验证失败!");
		}		
		return SUCCESS;
	}

	//修改密码
	public String modifyPasswd(){
		User user=(User)StrutsUtils.getSessionMap().get("user");
		userService.resetPasswd(user.getUserId(), MD5Util.code(model.getPassword()));
		user.setPassword(MD5Util.code(model.getPassword()));
		jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	//删除指定用户
	@Override
	public String del(){
		if(userService.get(id).getUsername().equals("admin")){
			jsonMap.put("returnCode", 2);
			jsonMap.put("msg", "不能删除预置管理员用户!");
			return SUCCESS;
		}
		userService.delete(id);
		jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	//锁定或者解锁用户
	public String lock(){
		if(model.getUsername().equals("admin")){
			jsonMap.put("returnCode", 2);
			jsonMap.put("msg", "不能锁定预置管理员用户!");
			return SUCCESS;
		}
		userService.lockUser(model.getUserId(), mode);
		jsonMap.put("returnCode", 0);
		return SUCCESS;
		
	}
	
	//重置密码
	public String resetPwd(){
		userService.resetPasswd(model.getUserId(),MD5Util.code("111111"));
		jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	
	//用户编辑
	@Override
	public String edit(){
		User u1 = userService.validateUsername(model.getUsername(),model.getUserId());
		if(u1!=null){
			jsonMap.put("returnCode", 2);
			jsonMap.put("msg", "用户名已存在!");
			return SUCCESS;
		}
		Role r = new Role();
		r.setRoleId(roleId);
		model.setRole(r);
		if(model.getUserId()==null){
			//新增
			model.setIfNew("1");
			model.setCreateTime(new Timestamp(System.currentTimeMillis()));
			model.setPassword(MD5Util.code("111111"));
			model.setStatus("0");
			model.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
		}else{
			//修改
			User u2 = userService.get(model.getUserId());
			model.setIfNew(u2.getIfNew());
			model.setPassword(u2.getPassword());			
		}
		userService.edit(model);
		jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	
	//条件查询
	public String filter(){
		List<User> users = userService.findByRealName(model.getRealName());
		if(users.size()==0){
			jsonMap.put("returnCode", 2);
			jsonMap.put("msg", "没有查询到指定的用户");
		}else{
			jsonMap.put("returnCode", 0);		
		}
		jsonMap.put("data",users );
		return SUCCESS;
	}
	
	//测试
	/*public String quartzTest(){
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext sc=request.getSession().getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		JobManager obj = (JobManager) ac.getBean("jobManager");
		AutoTask task = new AutoTask("测试", "0", 0, "0 4 19 * * ?", 0, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), "0");
    	task.setTaskId(1);
		try {
			obj.stopTask(task);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonMap.put("returnCode", 0);
		return SUCCESS;
		
	}*/
	
	/*****************************GET-SET******************************************************/
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
