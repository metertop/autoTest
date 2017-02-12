package com.dcits.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

 
/** 
 *  通用多层json递归解析。在没有Object对象，或是极度复杂的多级嵌套json，情况下以类的方式，直接获取结果。
 *  支持String、Map、ArrayList、ArrayMap四种返回对象的数据获取
 *  使用方式：根据json层级关系直接使用: 基节点.子节点.孙节点
 *  @author ww
 */

@SuppressWarnings("rawtypes")
public class JsonUtil {
 
    //private static String jsonStr = "{\"ROOT\":{\"PHONE_NO\": \"18755189818\",\"LOGIN_NO\":\"A0AAA0021\",\"CHANNEL_TYPE\": \"1000\",\"ROUTE_NO\": \"18755189818\"}}";
    private static ObjectMapper mapper = new ObjectMapper();
    
    /**
     * 根据输入的json串获取指定的节点信息
     * mode=1,返回包含所有节点名称的list
     * mode=0,返回包含所有节点的类型的list
     * mode=2,返回key为节点名称,value为节点值的map
     * mode=3,返回包含所有的map
     * @param jsonStr
     * @param mode
     * @return
     */
    public static Object getJsonList(String jsonStr,int mode){
    	//返回的都是子节点的map或者list
    	//mode为1=list,2=map
    	Map<String,String> jsonTreeMap=new HashMap<String,String>();
    	List<String> jsonTreeList=new ArrayList<String>();
    	List<String> jsonTreeType=new ArrayList<String>();
    	
    	try {
			Map maps = mapper.readValue(jsonStr, Map.class);
			viewJsonTree(maps,jsonTreeMap,jsonTreeList,jsonTreeType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		} 
		if(mode==1){
			return jsonTreeList;
		}else if(mode==2){
			return jsonTreeMap;
		}else if(mode==0){
			return jsonTreeType;			
		}else{
			Object a[] = {jsonTreeList,jsonTreeType,jsonTreeMap};
			return a;
		}
        
    }
    

    
    /** 复杂嵌套Map转Json  */
    public static String getObjectByJson(Object obj){
        String str = "";
        try {
            str = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            System.out.println("###[Error] getObjectToJson() "+e.getMessage());
        }
        return str;
    }
    /** 复杂嵌套Json层级展示  */
    
	public static Object viewJsonTree(Object m,Map<String,String> jsonTreeMap,List<String> jsonTreeList,List<String> jsonTreeType){
        if(m == null){ return null;}  
        try {
            Map mp = null;
            List ls = null;
            if(m instanceof Map || m instanceof LinkedHashMap){
                mp = (LinkedHashMap)m;
                for(Iterator ite = mp.entrySet().iterator(); ite.hasNext();){  
                    Map.Entry e = (Map.Entry) ite.next();  
                    if(e.getValue() instanceof String){
                        //System.out.println("[String]"+e.getKey()+" | " + e.getValue());
                        jsonTreeMap.put(e.getKey().toString(),e.getValue().toString());
                        jsonTreeList.add(e.getKey().toString()); 
                        jsonTreeType.add("String");
                    }else if(e.getValue() instanceof LinkedHashMap){
                        //System.out.println("{Map}"+ e.getKey()+" | "+e.getValue());
                    	jsonTreeList.add(e.getKey().toString());
                    	jsonTreeMap.put(e.getKey().toString(),"");
                    	jsonTreeType.add("Map");
                        viewJsonTree(e.getValue(),jsonTreeMap,jsonTreeList,jsonTreeType);
                    }else if(e.getValue() instanceof ArrayList){
                        //System.out.println("[Array]"+ e.getKey()+" | "+e.getValue());
                    	jsonTreeList.add(e.getKey().toString());
                    	jsonTreeMap.put(e.getKey().toString(),"");
                    	jsonTreeType.add("Array");
                        viewJsonTree(e.getValue(),jsonTreeMap,jsonTreeList,jsonTreeType);
                    }else if(e.getValue() instanceof Number){
                    	jsonTreeList.add(e.getKey().toString());
                    	jsonTreeMap.put(e.getKey().toString(),String.valueOf(e.getValue()));
                    	jsonTreeType.add("Number");
                        viewJsonTree(e.getValue(),jsonTreeMap,jsonTreeList,jsonTreeType);
                    }
                }     
            }
            if(m instanceof List || m instanceof ArrayList){
                ls = (ArrayList)m;
                for(int i=0;i<ls.size();i++){
                    if(ls.get(i) instanceof LinkedHashMap){
                        viewJsonTree(ls.get(i),jsonTreeMap,jsonTreeList,jsonTreeType);
                    }else if(ls.get(i) instanceof ArrayList){
                        viewJsonTree(ls.get(i),jsonTreeMap,jsonTreeList,jsonTreeType);
                    }   
                }
            }   
            //System.out.println();
        } catch (Exception e) {
            //System.out.println("###[Error] viewJsonTree() "+e.getMessage());
        	return null;
        }
        return null;
    }   
     
     
    private static int i = 0;
    /** 复杂嵌套Json获取Object数据  */
   
	public static String getObjectByJson(String jsonStr,String argsPath,TypeEnum argsType){
        if(argsPath == null || argsPath.equals("") || argsType == null){return null;}
         
        Object obj = null;
        try {
            Map maps = mapper.readValue(jsonStr, Map.class);
           
            //多层获取
            if(argsPath.indexOf(".") >= 0){
            	
                //类型自适应
                obj = getObject(maps,argsPath,argsType);
            }else{ //第一层获取
                if(argsType == TypeEnum.string){
                    obj = maps.get(argsPath).toString();
                }else if(argsType == TypeEnum.map){
                    obj = maps.get(argsPath);
                }else if(argsType == TypeEnum.arrayList){
                    obj = maps.get(argsPath);
                }else if(argsType == TypeEnum.arrayMap){
                    obj = maps.get(argsPath);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("###[Error] getObjectByJson() "+e.getMessage());
            return null;
        }
        i = 0;
        if(obj!=null){
        	return obj.toString();
        }else{
        	return null;
        }
        
    }
    //递归获取object
	private static Object getObject(Object m,String key,TypeEnum type){
        if(m == null){return null;}
        Object o = null;
        Map mp = null;
        List ls = null;
        try {
            //对象层级递归遍历解析
            if(m instanceof Map || m instanceof LinkedHashMap){
                mp = (LinkedHashMap)m;
                for(Iterator ite = mp.entrySet().iterator(); ite.hasNext();){  
                    Map.Entry e = (Map.Entry) ite.next();  
                     
                    if(i<key.split("\\.").length && e.getKey().equals(key.split("\\.")[i])){
                        i++;
                        if(e.getValue() instanceof String){
                            if(i== key.split("\\.").length){
                                o = e.getValue();
                                return o;
                            }
                        }else if(e.getValue() instanceof LinkedHashMap){
                            if(i== key.split("\\.").length){
                                if(type == TypeEnum.map){
                                    o = e.getValue();
                                    return o;
                                }
                            }else{
                                o = getObject(e.getValue(),key,type);
                            }
                            return o;
                        }else if(e.getValue() instanceof ArrayList){
                            if(i== key.split("\\.").length){
                                if(type == TypeEnum.arrayList){
                                    o = e.getValue();
                                    return o;
                                }
                                if(type == TypeEnum.arrayMap){
                                    o = e.getValue();
                                    return o;
                                }
                            }else{
                                o = getObject(e.getValue(),key,type);
                            }
                            return o;
                        }
                    }
                }     
            }
            //数组层级递归遍历解析
            if(m instanceof List || m instanceof ArrayList){
                ls = (ArrayList)m;
                for(int i=0;i<ls.size();i++){
                    if(ls.get(i) instanceof LinkedHashMap){
                        if(i== key.split("\\.").length){
                            if(type == TypeEnum.map){
                                o = ls.get(i);
                                return o;
                            }
                        }else{
                            o = getObject(ls.get(i),key,type);
                        }
                        return o;
                    }else if(ls.get(i) instanceof ArrayList){
                        if(i== key.split("\\.").length){
                            if(type == TypeEnum.arrayList){
                                o = ls.get(i);
                                return o;
                            }
                            if(type == TypeEnum.arrayMap){
                                o = ls.get(i);
                                return o;
                            }
                        }else{
                            o = getObject(ls.get(i),key,type);
                        }
                        return o;
                    }   
                }
            }   
        } catch (Exception e) {
            System.out.println("###[Error] getObject() "+e.getMessage());
        }
         
        return o;
    }
     
     
    /*
     * Json数据解析返回数据类型枚举
     */
    public enum TypeEnum{
        /** 单纯的键值对，通过key获取valus */
        string,
        /** 通过key获取到Map对象 */
        map,
        /** 通过key获取到ArrayList数组 */
        arrayList,
        /** 通过key获取到ArrayMap数组对象 */
        arrayMap;
        
    }
 
}