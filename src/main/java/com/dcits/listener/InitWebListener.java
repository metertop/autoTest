package com.dcits.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.dcits.business.system.bean.GlobalSetting;
import com.dcits.business.system.service.GlobalSettingService;
import com.dcits.business.user.bean.OperationInterface;
import com.dcits.business.user.service.OperationInterfaceService;

/**
 * 初始化Web操作-加载当前操作接口列表、加载网站全局设置
 * @author Administrator
 *
 */
public class InitWebListener implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		  //取得appliction上下文
		ApplicationContext ctx =WebApplicationContextUtils.
		getRequiredWebApplicationContext(context);
		  //取得特定bean
		OperationInterfaceService opService =(OperationInterfaceService)ctx.getBean("operationInterfaceService");
		GlobalSettingService settingService = (GlobalSettingService) ctx.getBean("globalSettingService");
		//获取当前系统的所有接口信息  
		List<OperationInterface> ops = opService.findAll();
		for(OperationInterface op:ops){
			op.setParentOpId();
		}
		context.setAttribute("ops", ops);
		
		//获取网站全局设置信息
		List<GlobalSetting> settings = settingService.findAll();
		Map<String,GlobalSetting> globalSettingMap = new HashMap<String,GlobalSetting>();
		for(GlobalSetting g:settings){
			globalSettingMap.put(g.getSettingName(), g);
		}
		context.setAttribute("settingMap", globalSettingMap);		
		
	}
	
}
