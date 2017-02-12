package com.dcits.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实用小工具类
 * @author xwc
 *
 */
public class PracticalUtils {
	
	/**
	 * 判断字符串是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	
	/**
	 * arrayList的toString工具
	 * @param list
	 * @return
	 */
	public static String arrayListToString(ArrayList<String> list){
		String returnStr = "";
		for(String s:list){
			returnStr+="["+s+"]";
		}
		return returnStr;
	}
	
	/**
	  * 
	  * @param s  要操作的字符串
	  * @param string  要删除的字符
	  * @param i  删除第几个
	  * @return
	  */
	 public static String removeChar(String s,String string,int i){
	  if(i==1){
		   int j=s.indexOf(string);
		   s=s.substring(0, j)+s.substring(j+1);
		   i--;
		   return s;
	  }else{
		   int j=s.indexOf(string);
		   i--;
		   return s.substring(0, j+1)+removeChar(s.substring(j+1), string, i);
	  }

	}
	 
	 
	 /**
	  * 替换sql中需要替换的参数
	  * 参数格式<参数名>
	  * Web自动化测试中使用的
	  * @param sqlStr
	  * @param map
	  * @return
	  */
	 public static String replaceSqlStr(String sqlStr,Map<String,Object> map){
		 String regex = "(<[a-zA-Z0-9]*>)";
		 Pattern pattern = Pattern.compile(regex);
		 List<String> regStrs = new ArrayList<String>();
		 Matcher matcher = pattern.matcher(sqlStr);//匹配类
		 while (matcher.find()) {
			regStrs.add(matcher.group());
		 }
		 for(String s:regStrs){
			 if(map.get(s)!=null){
				 sqlStr = sqlStr.replaceAll(s, (String)map.get(s));
			 }
		 }
		 return sqlStr;
	 }
	 
	 /**
	  * 替换sql语句中的参数
	  * 参数格式<节点名称或者路径>
	  * 接口自动化中使用的
	  * @param sqlStr
	  * @param requestMap
	  * @param requestJson
	  * @return
	  */
	 public static String replaceSqlStr(String sqlStr,Map<String,String> requestMap,String requestJson){
		 String regex = "(<[a-zA-Z0-9_.]*>)";
		 Pattern pattern = Pattern.compile(regex);
		 List<String> regStrs = new ArrayList<String>();
		 Matcher matcher = pattern.matcher(sqlStr);//匹配类
		 while (matcher.find()) {
			regStrs.add(matcher.group());
		 }
		 for(String s:regStrs){
			 String regS = s.substring(1, s.length()-1);
			 if(s.indexOf(".")!=-1){
				 regS = JsonUtil.getObjectByJson(requestJson, regS, JsonUtil.TypeEnum.string);
				 if(regS!=null){
					 sqlStr = sqlStr.replaceAll(s, regS);
				 }				
			 }else{
				 if(requestMap.get(regS)!=null){
					 sqlStr = sqlStr.replaceAll(s, requestMap.get(regS));
				 }
			 }			 
		 }
		 return sqlStr;
	 }
}
