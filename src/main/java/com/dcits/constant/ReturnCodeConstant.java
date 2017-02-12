package com.dcits.constant;

/**
 * 调用操作接口之后的返回code和msg
 * @author xwc
 *
 */
public class ReturnCodeConstant {
	/**
	 * 通用
	 */
	public final static Integer SUCCESS_CODE = 0;   //成功
	public final static Integer NOT_LOGIN_CODE = 7;  //用户未登录
	public final static Integer NO_POWER_CODE = 8;   //没有权限
	public final static Integer SYSTEM_ERROR_CODE = 1;  //系统处理错误
	public final static Integer OP_DISABLE_CODE = 4;   //接口不可用
	public final static Integer OP_NOTFOUND_CODE = 5;  //无对应接口
	public final static Integer FILE_UPLOAD_SUCCESS_CODE = 6;  //文件上传成功
	public final static Integer NO_RESULT_CODE = 3; //查询无结果
	public final static Integer MISS_PARAM_CODE = 2; //缺少请求参数
	public final static Integer ILLEGAL_HANDLE_CODE = 9;  //不允许的操作 禁止的操作
	public final static Integer NAME_EXIST_CODE = 10; //重复的名称
		
		
	/**
	 * User 
	 * 21开头
	 */
	public final static Integer USER_RE_LOGIN_CODE = 214; //重复登录
	public final static Integer USER_ACCOUNT_LOCK_CODE = 212; //账号被锁定
	public final static Integer USER_ERROR_ACCOUT_CODE = 211; //账号或者密码错误
	public final static Integer USER_VALIDATE_ERROR_CODE = 204; //验证密码失败	
	
	/**
	 * Mail
	 * 13开头
	 */
	public final static Integer MAIL_MISS_RECEIVER_CODE = 132;//缺少收件人
	
	/**
	 * dataDB
	 * 41开头
	 */
	public final static Integer DB_CONNECT_FAIL_CODE = 412;//尝试连接失败
	
	
	/**
	 * interface
	 * 91开头
	 */
	
	public final static Integer INTERFACE_ILLEGAL_JSON_CODE = 912;//不是合法的json格式
	
}
