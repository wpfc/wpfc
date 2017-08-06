package cn.edu.ntu.utils;

import java.util.Map;

import com.mysql.jdbc.Connection;

public class DatabaseUtil {

	private static Connection connection = null;
	
	static{
		connection = DBUtil.getInstance();
	}
	
	public static void insertEntity(Class<?> clazz, Map<String, Object> fieldMap) {
		
		
	}

}
