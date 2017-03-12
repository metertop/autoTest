package com.dcits.constant;

/**
 * 调用操作接口之后的返回code常量
 * @author xuwangcheng
 * @version 1.0.0.0,2017.2.13
 *
 */
public class ReturnCodeConsts {
	
	//通用ReturnCode
	
	/**
	 * 成功
	 */
	public static final Integer SUCCESS_CODE = 0;   
	
	/**
	 * 用户未登录或登录已失效
	 */
	public static final Integer NOT_LOGIN_CODE = 7;
	
	/**
	 * 没有权限
	 */
	public static final Integer NO_POWER_CODE = 8; 
	
	/**
	 * 系统处理错误
	 */
	public static final Integer SYSTEM_ERROR_CODE = 1; 
	
	/**
	 * 系统处理错误
	 */
	public static final Integer OP_DISABLE_CODE = 4;  
	
	/**
	 * 无对应接口
	 */
	public static final Integer OP_NOTFOUND_CODE = 5;  
	
	/**
	 * 文件上传成功
	 */
	public static final Integer FILE_UPLOAD_SUCCESS_CODE = 6; 
	
	/**
	 * 查询无结果
	 */
	public static final Integer NO_RESULT_CODE = 3; 
	
	/**
	 * 缺少请求参数
	 */
	public static final Integer MISS_PARAM_CODE = 2; 
	
	/**
	 * 不允许的操作 禁止的操作
	 */
	public static final Integer ILLEGAL_HANDLE_CODE = 9; 
	
	/**
	 * 不允许的操作 禁止的操作
	 */
	public static final Integer NAME_EXIST_CODE = 10; 
		
		
	//user相关   21开头
	
	/**
	 * 重复登录
	 */
	public static final Integer USER_RE_LOGIN_CODE = 214; 
	
	/**
	 * 账号被锁定
	 */
	public static final Integer USER_ACCOUNT_LOCK_CODE = 212; 
	
	/**
	 * 账号或者密码错误
	 */
	public static final Integer USER_ERROR_ACCOUT_CODE = 211; 
	
	/**
	 * 验证密码失败	
	 */
	public static final Integer USER_VALIDATE_ERROR_CODE = 214; 
	
	
	//mail相关  13开头
	
	/**
	 * 缺少收件人
	 */
	public static final Integer MAIL_MISS_RECEIVER_CODE = 132;
	
	
	//dataDB相关 41开头
	
	/**
	 * 尝试连接失败
	 */
	public static final Integer DB_CONNECT_FAIL_CODE = 412;
	
	
	//interface相关 91开头
	
	/**
	 * 不是合法的json格式
	 */
	public static final Integer INTERFACE_ILLEGAL_JSON_CODE = 912;
	/**
	 * 入参报文缺少参数 
	 */
	public static final Integer INTERFACE_LACK_PARAMETER_CODE = 917;
	
	/**
	 * 报文的入参报文对应接口的参数不匹配
	 */
	public static final Integer INTERFACE_MESSAGE_ERROR_JSON_CODE = 914;
	
}
