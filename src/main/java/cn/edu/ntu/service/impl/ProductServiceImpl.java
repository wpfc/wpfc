package cn.edu.ntu.service.impl;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import cn.edu.ntu.service.ProductService;
import cn.edu.ntu.thread.ClientThread;
import cn.edu.ntu.utils.DBUtil;

public class ProductServiceImpl implements ProductService {

	private static final String PRODUCT_SQL = 
			"update product set product_number = ? where product_id = ?";
	
	private static final String LOG_SQL = 
			"insert into log(log_name, log_remark) values (?, ?)";
	
	@Override
	public void addProduct() {
		try {
			Connection conn = DBUtil.getInstance();
			conn.setAutoCommit(false);
			
			updateProductInfo(conn, PRODUCT_SQL);
			insertLog(conn, LOG_SQL);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateProductInfo(Connection conn, String productSql) {
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(productSql);
			ps.setInt(1, 23);
			ps.setInt(2, 1);
			
			int row = ps.executeUpdate();
			if(row != 0){
				System.out.println("update product successfully!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void insertLog(Connection conn, String logSql) {
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(logSql);
			ps.setString(1, "后台日志");
			ps.setString(2, "hello world to mysql");
			int flag = ps.executeUpdate();
			if(flag != 0){
				System.out.println("插入后台日志成功！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//	public static void main(String[] args){
//		ProductService  ps = new ProductServiceImpl();
//		ps.addProduct();
//	}
	
	public static void main(String[] args){
		for(int i=0; i<20; i++){
			ProductService  ps = new ProductServiceImpl();
			ClientThread ct = new ClientThread(ps);
			ct.start();
		}
	}
	
}
