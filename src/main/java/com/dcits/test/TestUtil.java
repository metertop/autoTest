package com.dcits.test;

import java.util.List;

import org.junit.Test;

import com.dcits.util.JsonUtil;

/**
 * Test相关
 * @author xuwangcheng
 * @version 1.0.0.0,2017.2.14
 */

public class TestUtil {
	
	@Test
	public void testJsonUtil() throws Exception{
		String jsonString = "{\"draw\":1,\"returnCode\":0,\"recordsFiltered\":4,\"data\":[{\"createTime\":\"2017-01-2620:14:52\",\"ifNew\":\"1\",\"lastLoginTime\":\"2017-01-2620:14:52\",\"realName\":\"8888888\",\"role\":{\"mark\":\"超级管理员角色,不能删除\",\"oiNum\":null,\"roleGroup\":\"性能测试组\",\"roleId\":1,\"roleName\":\"admin\"},\"status\":\"1\",\"userId\":4,\"username\":\"8888\"},{\"createTime\":\"2016-11-0110:26:11\",\"ifNew\":\"1\",\"lastLoginTime\":\"2016-11-0410:18:23\",\"realName\":\"测试人员\",\"role\":{\"mark\":\"默认组,不能删除\",\"oiNum\":null,\"roleGroup\":\"性能测试组\",\"roleId\":3,\"roleName\":\"default\"},\"status\":\"1\",\"userId\":3,\"username\":\"xuwangcheng888\"}],\"recordsTotal\":4}";
		System.out.println(((List<String>)JsonUtil.getJsonList(jsonString, 4)).size());
		System.out.println(((List<String>)JsonUtil.getJsonList(jsonString, 1)).size());
		System.out.println(JsonUtil.getJsonList(jsonString, 4));
		System.out.println(JsonUtil.getJsonList(jsonString, 1));
	}
}	
