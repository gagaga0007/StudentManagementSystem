package DAO;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;

import menu.LoginFrame;

public class StudentDAO {
	// 指向与数据库的连接
	private static Connection conn = null;
	// 指向statement对象
	private static Statement stmt = null;

	/**
	 * 添加学生信息，存在于“添加学生信息”选项中。
	 * 
	 * @param STU_NUM
	 *            学生学号
	 * @param STU_NAME
	 *            学生姓名
	 * @param STU_SEX
	 *            学生性别
	 * @return 执行成功返回1，执行失败返回0
	 */
	public static int addStudent(String STU_NUM, String STU_NAME, String STU_SEX, String STU_TEL, String STU_MAIL)
			throws Exception {
		int result = 0;
		getStatement();
		String sqlStr = "INSERT INTO student(STU_NUM,STU_NAME,STU_SEX,STU_TEL,STU_MAIL)" + " VALUES('" + STU_NUM + "','"
				+ STU_NAME + "','" + STU_SEX + "','" + STU_TEL + "','" + STU_MAIL + "')";
		result = stmt.executeUpdate(sqlStr);
		return result;
	}

	/**
	 * 在学生信息表添加的同时给学生成绩表中添加学生学号，存在于“添加学生信息”选项中。
	 * 
	 * @param STU_NUM
	 * @return 执行成功返回1，执行失败返回0
	 * @throws Exception
	 */
	public static int addStudentNum(String STU_NUM) throws Exception {
		int result = 0;
		getStatement();
		String sqlStr = "INSERT INTO student_score(STU_NUM) VALUES('" + STU_NUM + "')";
		result = stmt.executeUpdate(sqlStr);
		return result;
	}

	/**
	 * 添加学生成绩，存在于“添加学生成绩”选项中。
	 * 虽然是“添加”成绩，但是使用的是update，因为前面的操作中已经将学号插入到了student_score表中，所以使用了update而不是insert。
	 * 
	 * @param STU_NUM
	 *            学生学号
	 * @param STU_MATH
	 *            数学成绩
	 * @param STU_CHINESE
	 *            语文成绩
	 * @param STU_ENGLISH
	 *            英语成绩
	 * @return 执行成功返回1，执行失败返回0
	 * @throws Exception
	 */
	public static int addScore(String STU_NUM, String STU_MATH, String STU_CHINESE, String STU_ENGLISH)
			throws Exception {
		int result = 0;
		getStatement();
		String sqlStr = "UPDATE student_score SET STU_MATH='" + STU_MATH + "',STU_CHINESE='" + STU_CHINESE
				+ "',STU_ENGLISH='" + STU_ENGLISH + "' WHERE STU_NUM='" + STU_NUM + "'";
		result = stmt.executeUpdate(sqlStr);
		return result;
	}

	/**
	 * 添加学生总成绩，存在于“添加学生成绩”选项中，在用户添加完各科成绩后，自动将总成绩相加添加到学生成绩表中。
	 * 需要注意的是，数据库中设置各科成绩为varchar类型，总成绩为int型，需要将各科成绩强制转换类型为int型。
	 * 在前面的方法中，系统已经判断用户只能输入数字，且将未输入的成绩自动赋值为0，所以无需担心数据库报错问题。
	 * 
	 * @param STU_NUM
	 *            学生学号
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public static int addSum(String STU_NUM) throws Exception {
		int result = 0;
		getStatement();
		String sqlStr = "UPDATE student_score SET STU_SUM=CAST(STU_MATH AS signed)+CAST(STU_CHINESE AS signed)+CAST(STU_ENGLISH AS signed) WHERE STU_NUM="
				+ STU_NUM;
		result = stmt.executeUpdate(sqlStr);
		return result;
	}

	/**
	 * 判断添加的学生学号在学生表中是否重复
	 * 
	 * @param id
	 *            学生学号
	 * @return 执行成功返回非空
	 */
	public String checkID(String id) {
		String result = null;
		try {
			Statement stmt = DBTool.getStatement();
			String sqlStr = "select * from student" + " where STU_NUM='" + id + "'";
			ResultSet rs = stmt.executeQuery(sqlStr);
			if (rs.next())
				result = rs.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断学生学号是否存在于学生表。普通用户注册时需要填写自己的学号，此方法用于判断，存在于“注册普通用户”选项中。
	 * 
	 * @param num
	 *            学生学号
	 * @return 成功返回非空
	 */
	public static String checkNum(String num) {
		String result = null;
		try {
			Statement stmt = DBTool.getStatement();
			String sqlStr = "select * from student where STU_NUM='" + num + "'";
			ResultSet rs = stmt.executeQuery(sqlStr);
			if (rs.next())
				result = rs.getString(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 通过学号删除学生信息表中数据，存在于“删除学生所有信息”选项中。
	 * 
	 * @param STU_NUM
	 *            学生学号
	 * @return 执行成功返回1，执行失败返回0
	 */
	public static int deleteStudent(String STU_NUM) throws Exception {
		int result = 0;
		getStatement();
		String sqlStr = "DELETE FROM student WHERE STU_NUM='" + STU_NUM + "'";
		result = stmt.executeUpdate(sqlStr);
		return result;
	}

	/**
	 * 删除学生信息表中数据后删除学生成绩表中数据，存在于“删除学生所有信息”选项中。
	 * 
	 * @param STU_NUM
	 *            学生学号
	 * @return 执行成功返回1，执行失败返回0
	 * @throws Exception
	 */
	public static int deleteScore(String STU_NUM) throws Exception {
		int result = 0;
		getStatement();
		String sqlStr = "DELETE FROM student_score WHERE STU_NUM='" + STU_NUM + "'";
		result = stmt.executeUpdate(sqlStr);
		return result;
	}

	/**
	 * 通过学号修改学生信息，存在于“修改学生信息”选线中。 updateNum作用为判断用户输入是否为空，为空则执行某一句话，反之执行另一句话。
	 * 
	 * @param STU_NUM
	 *            学生学号
	 * @return 执行成功返回1，执行失败返回0；
	 */
	public static int updateStudent(String STU_NUM, String STU_NAME, String STU_SEX, String STU_TEL, String STU_MAIL)
			throws Exception {
		int result = 0;
		getStatement();
		String sqlStr = "UPDATE student SET ";
		int updateNum = 0; // 设置为0
		if (STU_NAME != null && !"".equals(STU_NAME)) {
			sqlStr += "STU_NAME='" + STU_NAME + "'";
			updateNum++; // 如果用户输入非空，则自增
		}
		if (STU_SEX != null && !"".equals(STU_SEX)) {
			if (updateNum == 0) { // 如果用户第一个框输入为空，则执行
				sqlStr += "STU_SEX='" + STU_SEX + "'";
			} else { // 若用户前面已经填写，执行
				sqlStr += ",STU_SEX='" + STU_SEX + "'";
			}
			updateNum++; // 用户在此框中输入非空，则自增
		}
		if (STU_TEL != null && !"".equals(STU_TEL)) {
			if (updateNum == 0) { // 如果用户县两个框输入都为空，则执行
				sqlStr += "STU_TEL='" + STU_TEL + "'";
			} else { // 若用户前面已经填写，则执行
				sqlStr += ",STU_TEL='" + STU_TEL + "'";
			}
			updateNum++; // 用户在此框中输入非空，则自增
		}
		if (STU_MAIL != null && !"".equals(STU_MAIL)) {
			if (updateNum == 0) { // 如果用户前面框全部为空，则执行
				sqlStr += "STU_MAIL='" + STU_MAIL + "'";
			} else { // 若用户前面已经填写，则执行
				sqlStr += ",STU_MAIL='" + STU_MAIL + "'";
			}
			updateNum++; // 用户在此框中输入非空，则执行
		}
		sqlStr += " WHERE STU_NUM='" + STU_NUM + "'"; // 最后添加上语句
		if (updateNum > 0) // 修改一个以上才执行SQL语句
			result = stmt.executeUpdate(sqlStr);
		return result;
	}

	/**
	 * 修改学生的成绩，存在于“修改学生成绩”选项中。
	 * 
	 * @param STU_NUM
	 *            学生学号
	 * @param STU_MATH
	 *            学生新数学成绩
	 * @param STU_CHINESE
	 *            学生新语文成绩
	 * @param STU_ENGLISH
	 *            学生新英语成绩
	 * @return 执行成功返回1，执行失败返回0
	 * @throws Exception
	 */
	public static int updateScore(String STU_NUM, String STU_MATH, String STU_CHINESE, String STU_ENGLISH)
			throws Exception {
		int result = 0;
		getStatement();
		String sqlStr = "UPDATE student_score SET ";
		int updateNum = 0; // 设置计数器，为了判断用户是否输入了空值
		if (STU_MATH != null && !"".equals(STU_MATH)) {
			sqlStr += "STU_MATH='" + STU_MATH + "'";
			updateNum++; // 若用户输入非空，则自增
		}
		if (STU_CHINESE != null && !"".equals(STU_CHINESE)) {
			if (updateNum == 0) { // 若用户前面输入为空值，则执行
				sqlStr += "STU_CHINESE='" + STU_CHINESE + "'";
			} else { // 若前面输入非空，则执行
				sqlStr += ",STU_CHINESE='" + STU_CHINESE + "'";
			}
			updateNum++; // 如果用户此框中输入非空，则自增
		}
		if (STU_ENGLISH != null && !"".equals(STU_ENGLISH)) {
			if (updateNum == 0) { // 若用户前面输入为空值，则执行
				sqlStr += "STU_ENGLISH='" + STU_ENGLISH + "'";
			} else { // 若前面的框输入非空，则执行
				sqlStr += ",STU_ENGLISH='" + STU_ENGLISH + "'";
			}
			updateNum++; // 如果用户此框中输入非空，则自增
		}
		sqlStr += " WHERE STU_NUM='" + STU_NUM + "'";
		if (updateNum > 0) // 修改一个以上再执行SQL语句
			result = stmt.executeUpdate(sqlStr);
		return result;
	}

	/**
	 * 修改学生总成绩，存在于“修改学生成绩”中。
	 * 在此方法中，需要用到强制转换的内容。在MySQL数据表中，各科成绩为varchar类型，总成绩为int型，所以需要将各科成绩强制转换为int型再相加。
	 * 
	 * @param STU_NUM
	 *            学生学号
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public static int updateSum(String STU_NUM) throws Exception {
		int result = 0;
		getStatement();
		String sqlStr = "UPDATE student_score SET STU_SUM=CAST(STU_MATH AS signed)+CAST(STU_CHINESE AS signed)+CAST(STU_ENGLISH AS signed) WHERE STU_NUM="
				+ STU_NUM; // 强制转换为int型再相加，使用MySQL中cast函数进行转换。
		result = stmt.executeUpdate(sqlStr);
		return result;
	}

	/**
	 * 查询学生信息，返回学生信息，存在于“查询学生所有信息”选项中。
	 * 
	 * @param STU_NUM
	 *            学生学号
	 * @throws Exception
	 * @return String[] 为null表示查询不成功，查询成功第0个元素是姓名，第1个元素是性别，以此类推。
	 */
	public static String[] queryOneStudent(String STU_NUM) throws Exception {
		String[] result = null;
		getStatement();
		String sqlStr = "SELECT * FROM student WHERE STU_NUM='" + STU_NUM + "'";
		ResultSet rs = stmt.executeQuery(sqlStr);
		if (rs.next()) { // 将各个信息都赋值给数组，返回学生的信息
			result = new String[4];
			result[0] = rs.getString("STU_NAME");
			result[1] = rs.getString("STU_SEX");
			result[2] = rs.getString("STU_TEL");
			result[3] = rs.getString("STU_MAIL");
		}
		return result;
	}

	/**
	 * 查询学生学号，返回成绩的值，存在于“查询学生所有信息”选项中，在查询信息过后立即查询成绩并同时显示在屏幕上。
	 * 
	 * @param STU_NUM
	 *            学生学号
	 * @return
	 * @throws Exception
	 * @return String[] 为null表示查询不成功，查询成功则第0个元素为数学成绩，第1个元素为语文成绩，以此类推。
	 */
	public static String[] queryStudentScore(String STU_NUM) throws Exception {
		String[] result = null;
		getStatement();
		String sqlStr = "SELECT * FROM student_score WHERE STU_NUM='" + STU_NUM + "'";
		ResultSet rs = stmt.executeQuery(sqlStr);
		if (rs.next()) { // 将各个信息赋值给数组，返回学生的成绩
			result = new String[4];
			result[0] = rs.getString("STU_MATH");
			result[1] = rs.getString("STU_CHINESE");
			result[2] = rs.getString("STU_ENGLISH");
			result[3] = rs.getString("STU_SUM");
		}
		return result;
	}

	/**
	 * 通过确认普通用户表的用户名，返回该用户名的学号。作用为在普通用户登录后在数据表中获取到该学生的学号，以便学生能查询到自己的信息。
	 * 
	 * @return String 学号
	 * @throws Exception
	 */
	public static String queryNum() throws Exception {
		String result = null;
		String username = menu.LoginFrame.getUserNameField();
		getStatement();
		String sqlStr = "SELECT * FROM visitor_user WHERE username='" + username + "'";
		ResultSet rs = stmt.executeQuery(sqlStr);
		if (rs.next())
			result = rs.getString("STU_NUM");
		return result;
	}

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
	 * 
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
