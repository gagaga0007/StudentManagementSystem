package menu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//登录界面，用户输入用户名密码登录
//用户可以再本界面选择注册、登录或是退出选项
public class LoginFrame extends JFrame {
	private static JTextField userNameField;
	private JPasswordField passwordField;

	public LoginFrame() {
		// 设置背景图片
		setBack();
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
		c.add(jp);

		// getContentPane().setBackground(new Color(255, 255, 240));
		getContentPane().setLayout(null);

		setTitle("学生管理系统-登录页");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		// 设置窗口居中显示
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置

		JLabel label1 = new JLabel("用户名：");
		label1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label1.setForeground(new Color(255, 69, 0));
		label1.setBounds(87, 219, 80, 21);
		getContentPane().add(label1);

		userNameField = new JTextField();
		userNameField.setBounds(195, 213, 294, 39);
		getContentPane().add(userNameField);
		userNameField.setColumns(10);

		JLabel label_1 = new JLabel("密  码：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_1.setForeground(new Color(255, 69, 0));
		label_1.setBounds(87, 300, 80, 21);
		getContentPane().add(label_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(195, 294, 294, 39);
		getContentPane().add(passwordField);

		JButton button = new JButton("登录");
		button.setFont(new Font("Lao MN", Font.PLAIN, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login(); // 登录
			}
		});
		button.setForeground(new Color(0, 0, 255));
		button.setBounds(283, 403, 145, 33);
		getContentPane().add(button);

		JLabel label_2 = new JLabel("请输入您的用户名");
		label_2.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label_2.setBounds(513, 224, 125, 16);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("请输入您的密码");
		label_3.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label_3.setBounds(513, 305, 118, 17);
		getContentPane().add(label_3);

		JButton button_1 = new JButton("注册");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Register(); // 跳转到注册界面并释放该界面资源
				dispose();
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_1.setForeground(new Color(0, 0, 0));
		button_1.setBounds(75, 403, 145, 33);
		getContentPane().add(button_1);

		JLabel label_4 = new JLabel("欢迎使用一个很捞的学生管理系统！");
		label_4.setForeground(new Color(0, 0, 205));
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		label_4.setBounds(87, 51, 575, 57);
		getContentPane().add(label_4);

		JLabel lblNewLabel = new JLabel("请您登陆…");
		lblNewLabel.setFont(new Font("Lantinghei SC", Font.PLAIN, 25));
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setBounds(87, 148, 133, 26);
		getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("退出");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnNewButton.setForeground(new Color(112, 128, 144));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ParaDialog(); // 确认退出弹窗
			}
		});
		btnNewButton.setBounds(493, 403, 145, 33);
		getContentPane().add(btnNewButton);

		this.setVisible(true);
	}

	/**
	 * 实现登录验证
	 */
	public void login() {
		// TODO Auto-generated method stub
		String userName = userNameField.getText().trim();
		String password = new String(passwordField.getPassword());
		password = password.trim(); // 获取密码
		DAO.UserDAO userDAO = new DAO.UserDAO();
		if (userDAO.login(userName, password) == "1") { // 如果返回的字符串为1，则说明是管理员用户
			new AdmainMenu();// 管理员菜单
			dispose();
		} else if (userDAO.login(userName, password) == "2") {// 如果返回的字符串为2，则说明是普通用户
			new NormalMenu();// 普通用户菜单
			dispose();
		} else {// 若未查询到，则提示用户
			JOptionPane.showMessageDialog(this, "用户名或密码错误！", "登录失败！", JOptionPane.WARNING_MESSAGE);
		}

	}

	/**
	 * 该方法用于返回普通用户的用户名，以便StudentDAO中queryNum方法可以通过查询普通用户表返回该用户名对应的学号
	 * 
	 * @return String 用户名
	 */
	public static String getUserNameField() {
		return userNameField.getText().trim();
	}

	public void setUserNameField(JTextField userNameField) {
		LoginFrame.userNameField = userNameField;
	}

	/**
	 * 设置图片背景
	 */
	public void setBack() {
		((JPanel) this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("Image/login.jpg"); // 添加图片
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
}
