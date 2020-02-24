package com.ymw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBManager {
	private static final String driverName="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
	private static final String user="root",pwd="admin";

	/**
	 * 加载驱动
	 * 建立连接
	 * @return
	 */
	public static Connection getConnection(){
		try {
			Class.forName(driverName);
			return DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}

		
	/**
	 * 增删改操作
	 * @param sql
	 * @return 受影响的行数
	 */
	public static int executeUpdate(String sql){
		Connection conn=null;
		Statement sta=null;
		try {
			conn = getConnection();
			sta = conn.createStatement();
			return sta.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getErrorCode()*(-1);
		}
		finally{
			closeAll(conn,sta,null);
		}
	}
	
		
	/**
	 * 关闭资源
	 * @param conn
	 * @param sta
	 * @param set
	 */
	public static void closeAll(Connection conn,Statement sta,ResultSet set){
		try {	
             if(set !=null)               
            	 set.close();		
            if(sta!=null)
            	sta.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
