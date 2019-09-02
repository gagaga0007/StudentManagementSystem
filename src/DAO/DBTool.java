package DAO;

import java.sql.*;
import java.util.Scanner;

public class DBTool {
	// 指向与数据库的连接
	private static Connection conn = null;
	// 指向statement对象
	private static Statement stmt = null;

	/**
	 * 释放资源
	 * 
	 * @throws SQLException
	 */
	public static void close() throws SQLException {
		stmt.close();
		conn.close();
	}

	/**
	 * 通过与数据库连接产生一个Statement对象，确保只产生一个Statement对象
	 * @return 
	 * 
	 * @throws Exception
	 * @throws SQLException
	 */
	public static Statement getStatement() throws Exception, SQLException {
		if (stmt == null) {
			getConnection();
			stmt = conn.createStatement(); // 直接执行语句
		}
		return stmt;
	}

	/**
	 * 获取与数据库的连接，确保只产生一个连接
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void getConnection() throws ClassNotFoundException, SQLException {
		if (conn == null) {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://******:3306/test", "root", "******");
		}
	}
}
