package cn.edu.ntu.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtil {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/smart";
	private static final String username = "root";
	private static final String password = "";
	
	private static Connection connection = null;
	
	public static Connection getInstance(){
		try {
			if(connection == null){
				Class.forName(driver);
				connection = (Connection) DriverManager.getConnection(url, username, password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void close(){
		try {
			if(connection != null){
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void startTransaction(){
		try {
			Connection connection = getInstance();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void endTransaction(){
		try {
			Connection connection = getInstance();
			connection.commit();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(){
		try {
			Connection connection = getInstance();
			connection.rollback();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
