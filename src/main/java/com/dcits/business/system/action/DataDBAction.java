package com.dcits.business.system.action;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dcits.business.base.action.BaseAction;
import com.dcits.business.system.bean.DataDB;
import com.dcits.constant.ReturnCodeConstant;
import com.dcits.util.DBUtil;

@Controller
@Scope("prototype")
public class DataDBAction extends BaseAction<DataDB>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//增加或者编辑
	@Override
	public String edit(){
		if(model.getDbId()==null){
			//新增
			model.setDbId(dataDBService.getMaxDBId());
		}
		dataDBService.edit(model);
		jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
		return SUCCESS;
	}
	
	
	//测试是否可以连接
	public String testDB() throws SQLException{
		DataDB db = dataDBService.get(id);
		Connection conn = DBUtil.getConnection(db.getDbType(), db.getDbUrl(), db.getDbName(), db.getDbUsername(), db.getDbPasswd());
		if(conn!=null){
			jsonMap.put("returnCode", ReturnCodeConstant.SUCCESS_CODE);
			DBUtil.close(conn);
		}else{
			jsonMap.put("returnCode", ReturnCodeConstant.DB_CONNECT_FAIL_CODE);
			jsonMap.put("msg","尝试连接数据库失败,请检查配置!");
		}		
		return SUCCESS;
	}

}
