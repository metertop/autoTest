package com.dcits.business.system.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 静态html访问
 * @author xwc
 *
 */
@Controller
public class StaticResourceAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static String B_USER = "user";
	private final static String B_SYSTEM = "system";
	private final static String B_WEB = "web";
	private final static String B_MESSAGE = "message";
	private final static String B_PUBLIC = "public";
	
	public String login(){				
		return B_PUBLIC;
	}
	
	public String index(){				
		return B_PUBLIC;
	}
	
	public String welcome(){				
		return B_PUBLIC;
	}
	
	public String nopower(){
		return B_PUBLIC;
	}
	
	public String userList(){				
		return B_USER;
	}
	

}
