package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

	/**
	 * 此方法用于查验用户是否存在，在登录时使用。
	 * 
	 * @param userName
	 *            用户输入的用户名
	 * @param password
	 *            用户输入的密码
	 * @return String,1表示管理员用户,2表示普通用户，若不存在则返回null
	 */
	public String login(String userName, String password) {
		String result = null;
		try {
			// 查询用户是否存在于管理员表中，如果是管理员，则返回1
			Statement stmt = DBTool.getStatement();
			String sqlStr = "select * from user " + " where username='" + userName + "'" + " and password=" + "ENCODE('"
					+ password + "','abcd')";
			ResultSet rs = stmt.executeQuery(sqlStr);
			if (rs.next())
				result = "1";
			rs.last();

			// 查询用户是否存在于普通用户表中 ，如果是普通用户，则返回2
			Statement stmt1 = DBTool.getStatement();
			String sqlstr = "select * from visitor_user " + " where username='" + userName + "'" + " and password="
					+ "ENCODE('" + password + "','abcd')";
			rs = stmt1.executeQuery(sqlstr);
			if (rs.next())
				result = "2";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 此方法用于注册普通用户，密码采用encode加密密码。
	 * 
	 * @param userName
	 *            用户输入的用户名
	 * @param password
	 *            用户输入的密码
	 * @return String，true表示注册成功，false表示注册失败
	 */
	public boolean register(String userName, String password, String STU_NUM) {
		boolean result = false;
		try {
			Statement stmt = DBTool.getStatement();
			String sqlStr = "INSERT INTO visitor_user(username,password,STU_NUM) " + "VALUES('" + userName + "',"
					+ "ENCODE('" + password + "','abcd')" + ",'" + STU_NUM + "')";
			int rs = stmt.executeUpdate(sqlStr); // executeUpdate的返回值为更新的条数
			if (rs != 0)
				result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 此方法用于注册管理员，使用encode加密密码。
	 * 
	 * @param userName
	 *            用户输入的用户名
	 * @param password
	 *            用户输入的密码
	 * @return String，true表示注册成功，false表示注册失败
	 */
	public boolean registAdmin(String userName, String password) {
		boolean result = false;
		try {
			Statement stmt = DBTool.getStatement();
			String sqlStr = "INSERT INTO user(username,password) VALUES('" + userName + "'," + "ENCODE('" + password
					+ "','abcd')" + ")";
			int rs = stmt.executeUpdate(sqlStr);
			if (rs != 0)
				result = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	/**
	 * 此方法用于验证用户是否已在普通用户表中被注册，在注册时使用。
	 * 
	 * @param userName
	 *            用户名
	 * @return String 如果不存在返回null
	 */
	public String checkName(String userName) {
		String result = null;
		try {
			Statement stmt = DBTool.getStatement();
			String sqlStr = "select * from visitor_user where username='" + userName + "'";
			ResultSet rs = stmt.executeQuery(sqlStr);
			if (rs.next())
				result = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 此方法用于验证用户是否已在管理员表中被注册，在注册时使用。
	 * 
	 * @param userName
	 *            用户名
	 * @return 如果不存在返回null
	 */
	public String checkAdmin(String userName) {
		String result = null;
		try {
			Statement stmt = DBTool.getStatement();
			String sqlStr = "select * from user where username='" + userName + "'";
			ResultSet rs = stmt.executeQuery(sqlStr);
			if (rs.next())
				result = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断普通用户注册时学号是否已经被注册，在普通用户表中判断是否学号是否存在。
	 * 
	 * @param num
	 *            学生学号
	 * @return 未查询到返回null，反之返回非空
	 */
	public static String checkNum(String num) {
		String result = null;
		try {
			Statement stmt = DBTool.getStatement();
			String sqlStr = "select * from visitor_user where STU_NUM='" + num + "'";
			ResultSet rs = stmt.executeQuery(sqlStr);
			if (rs.next())
				result = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改用户的密码。
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            新密码
	 * @param checkNum
	 *            判断用户权限
	 * @return 成功返回true，失败返回false
	 */
	public static boolean changePassword(String username, String password, int checkNum) {
		boolean result = false;
		try {
			String sqlStr = null;
			Statement stmt = DBTool.getStatement();
			if (checkNum == 1) { // 1表示管理员用户
				sqlStr = "UPDATE user SET password=ENCODE('" + password + "','abcd') WHERE username='" + username + "'";
			} else if (checkNum == 2) { // 2表示普通用户
				sqlStr = "UPDATE visitor_user SET password=ENCODE('" + password + "','abcd') WHERE username='"
						+ username + "'";
			}
			int rs = stmt.executeUpdate(sqlStr);
			if (rs != 0)
				result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
