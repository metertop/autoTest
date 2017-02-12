package com.dcits.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBUtil {
	private static Logger logger = Logger.getLogger(DBUtil.class);
	
	/**
	 * ʹ��JDBC������ݿ�����
	 * Ŀǰ֧��mysql��oracle
	 * @param dbType
	 * @param dbUrl
	 * @param dbName
	 * @param dbUserName
	 * @param dbPasswd
	 * @return
	 */
	public static Connection getConnection(String dbType,String dbUrl,String dbName,String  dbUserName,String dbPasswd){
   	 Connection con=null;
   	 try {
   		 
   		 if(dbType.equals("oracle")){
   			Class.forName("oracle.jdbc.driver.OracleDriver");
   			con = DriverManager.getConnection("jdbc:oracle:thin:@"+dbUrl+":"+dbName, dbUserName, dbPasswd);
   		 }else if(dbType.equals("mysql")){
   			Class.forName("com.mysql.jdbc.Driver");
   			con = DriverManager.getConnection("jdbc:mysql://"+dbUrl+"/"+dbName+"?characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true", dbUserName, dbPasswd);
   		 }else{
   			logger.info("��֧�ֵ���ݿ�����,�޷�����");
   			return null;
   		 }			
		} catch (Exception e) {
			logger.error(dbUrl+","+dbName+"������ѯ��ݿ�����ʧ��:"+e.toString());
			e.printStackTrace();
		}
   	 return con;
    }
	
	
    public static void close(Connection con)throws SQLException{
   	 if(con!=null){
   		 try {
				con.close();
			} catch (SQLException e) {				
				e.printStackTrace();
				logger.error("�رղ�ѯ��ݿ��쳣:"+e.toString());
				throw e;
			}
   	 }
    }
    
    /**
     * ������ݿ����Ӻ�Ҫִ�е�sql���
     * �õ�����ֵ,���ֵֻȡ��һ��,û��ֵ����null
     * @param con
     * @param sqlStr
     * @return
     */
    public static String getDBData(Connection con,String sqlStr){
    	String returnStr = null;
    	PreparedStatement ps=null;
    	ResultSet rs=null;
    	try {
    		ps=con.prepareStatement(sqlStr);
    		rs=ps.executeQuery();
    		while(rs.next()){
    			//ֻȡ��һ����¼
    			returnStr = rs.getString(1);
    			break;
    		}   		
		} catch (Exception e) {			
			logger.error("��ѯ���ִ��ʧ��["+sqlStr+"]:"+e.toString());
			e.printStackTrace();
		}finally{
			try {
				close(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	return returnStr;
    }
}
