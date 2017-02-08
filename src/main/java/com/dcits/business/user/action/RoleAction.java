package com.dcits.business.user.action;

import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.business.base.action.BaseAction;
import com.dcits.business.user.bean.OperationInterface;
import com.dcits.business.user.bean.Role;
import com.dcits.util.StrutsUtils;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String delOpIds;
	
	private String addOpIds;
	
	
	//展示当前的所有的角色
	@Override
	public String listAll(){
		List<Role> roles=roleService.findAll();
		for(Role r:roles){
			r.setOiNum();
		}
		jsonMap.put("returnCode", 0);
		jsonMap.put("data", roles);
		return SUCCESS;
	}
	
	//删除角色,但是不能删除预置的管理员账户
	public String del(){
			Role role = roleService.get(model.getRoleId());
			String roleName = role.getRoleName();
			if(roleName.equals("admin")||roleName.equals("default")){
				jsonMap.put("returnCode", 2);
				jsonMap.put("msg", "不能删除超级管理员角色或者默认角色");
				return SUCCESS;
			}
			roleService.delete(model.getRoleId());
			//删除其他角色,配置该角色的用户变更成default角色
			jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	//根据roleId查找指定的role信息
	public String get(){
		Role role = roleService.get(model.getRoleId());
		jsonMap.put("role", role);
		jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	//编辑或者新增role信息
	public String edit(){
		//判断roleName的合法性:不能重复
		Role r = roleService.get(model.getRoleName());
		if(r!=null){
			if((model.getRoleId()!=null&&r.getRoleId()!=model.getRoleId())||model.getRoleId()==null){
				jsonMap.put("returnCode", 2);
				jsonMap.put("msg", "该角色名已存在,请更换!");
				return SUCCESS;
			}			
		}
		if(model.getRoleId()!=null){
			//修改
			model.setOis(roleService.get(model.getRoleId()).getOis());
		}
		roleService.edit(model);		
		jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	//获取当前所有的操作接口列表
	//并且对当前角色已拥有的操作接口打标记
	@SuppressWarnings("unchecked")
	public String getNodes(){		
		List<OperationInterface> ops = (List<OperationInterface>) StrutsUtils.getApplicationMap().get("ops");				
		Role role = roleService.get(model.getRoleId());
		Set<OperationInterface> ownOps = role.getOis();
		
		for(OperationInterface op:ops){
			op.setIsOwn(false);			
			for(OperationInterface op1:ownOps){
				if((int)op1.getOpId()==(int)op.getOpId()){
					op.setIsOwn(true);
				}
			}
		}
				
		jsonMap.put("interfaces", ops);
		jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	//更新角色的权限信息
	//更新角色与操作接口的关联关系
	public String updateRolePower(){
		Role role = roleService.get(model.getRoleId());
		Set<OperationInterface> ops = role.getOis();
		//更新增加的权限
		if(addOpIds!=null&&!addOpIds.equals("")){
			String[] addOpArray = addOpIds.split(",");
			for(String s:addOpArray){
				OperationInterface o = new OperationInterface();
				o.setOpId(Integer.valueOf(s));
				ops.add(o);
			}
			
		}
		//更新删除的权限
		if(delOpIds!=null&&!delOpIds.equals("")){
			String[] delOpArray = delOpIds.split(",");
			for(String s:delOpArray){
				OperationInterface o = new OperationInterface();
				o.setOpId(Integer.valueOf(s));
				ops.remove(o);
			}
		}
		role.setOis(ops);
		roleService.edit(role);
		jsonMap.put("returnCode", 0);
		return SUCCESS;
	}
	
	/********************************************************************************************/
	public void setDelOpIds(String delOpIds) {
		this.delOpIds = delOpIds;
	}
	
	public void setAddOpIds(String addOpIds) {
		this.addOpIds = addOpIds;
	}
	
}
