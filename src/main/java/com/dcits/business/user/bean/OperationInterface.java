package com.dcits.business.user.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class OperationInterface implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer opId;
	private String opName;
	private String callName;
	private String isParent;
	private String opType;  //类型  0为静态Html资源请求  1为动态请求
	private String mark;
	private String status;
	private OperationInterface oi;
	
	private Set<Role> roles=new HashSet<Role>();
	
	private Set<OperationInterface> ois = new HashSet<OperationInterface>();
	
	//设置角色是否拥有对应的权限
	private Boolean isOwn;
	
	//父节点ID
	public Integer parentOpId;
	//父节点名称
	private String parentOpName;
	
/*	//当前节点下不是父节点的操作接口集合
	private Set<OperationInterface> allOis = new HashSet<OperationInterface>();
	
	
	
	
	@JSON(serialize=false)
	public Set<OperationInterface> getAllOis() {
		return allOis;
	}*/

	//只考虑到了倒数第二的父节点,往后的再修改
	@JSON(serialize=false)
	public Set<OperationInterface> getAllOis() {
		Set<OperationInterface> ops1 = new HashSet<OperationInterface>();
		for(OperationInterface op:this.getOis()){
			if(op.getIsParent().equals("true")){
				ops1.addAll(op.getOis());
			}else{
				ops1.add(op);
			}
		}
		return ops1;
	}

	public String getParentOpName() {
		return parentOpName;
	}

	public void setParentOpName() {
		this.parentOpName = this.oi.getOpName();
	}

	public Integer getParentOpId() {
		return parentOpId;
	}

	public void setParentOpId() {
		this.parentOpId = this.oi.getOpId();
	}

	public Boolean getIsOwn() {
		return isOwn;
	}

	public void setIsOwn(Boolean isOwn) {
		this.isOwn = isOwn;
	}

	@JSON(serialize=false)
	public Set<Role> getRoles() {
		return roles;
	}
		
	
	public void setOpType(String opType) {
		this.opType = opType;
	}
	
	public String getOpType() {
		return opType;
	}
	
	
	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	@JSON(serialize=false)
	public Set<OperationInterface> getOis() {
		return ois;
	}

	public void setOis(Set<OperationInterface> ois) {
		this.ois = ois;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@JSON(serialize=false)
	public OperationInterface getOi() {
		return oi;
	}

	public void setOi(OperationInterface oi) {
		this.oi = oi;
	}

	public Integer getOpId() {
		return opId;
	}
	public void setOpId(Integer opId) {
		this.opId = opId;
	}
	public String getOpName() {
		return opName;
	}
	public void setOpName(String opName) {
		this.opName = opName;
	}
	public String getCallName() {
		return callName;
	}
	public void setCallName(String callName) {
		this.callName = callName;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	public OperationInterface(String opName, String callName, String isParent,
			String mark, String opType,String status, OperationInterface oi) {
		super();
		this.opName = opName;
		this.callName = callName;
		this.isParent = isParent;
		this.mark = mark;
		this.opType = opType;
		this.status = status;
		this.oi = oi;
	}

	public OperationInterface() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
