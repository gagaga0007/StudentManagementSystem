package menu;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JTextField;

import DAO.UserDAO;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

//此类用于注册普通用户
//用户通过填写用户名、密码、绑定呃学号进行注册
public class RegistNormal extends JFrame {
	private JTextField userField;
	private JPasswordField passwordField;
	private JTextField numberField;

	public RegistNormal() {
		// 设置背景图片
		setBack();
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
		c.add(jp);

		// getContentPane().setBackground(new Color(255, 245, 238));
		setTitle("学生管理系统-注册页");
		setSize(700, 500);
		// 窗口居中
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置
		getContentPane().setLayout(null);

		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Register();// 返回注册主界面
				dispose();
			}
		});
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button.setBounds(419, 372, 128, 45);
		getContentPane().add(button);

		JLabel label = new JLabel("普通用户注册...");
		label.setFont(new Font("Lantinghei SC", Font.PLAIN, 30));
		label.setForeground(new Color(0, 0, 205));
		label.setBounds(70, 57, 250, 45);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("新用户名：");
		label_1.setForeground(new Color(178, 34, 34));
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_1.setBounds(70, 140, 100, 30);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("密    码：");
		label_2.setForeground(new Color(178, 34, 34));
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_2.setBounds(70, 211, 100, 30);
		getContentPane().add(label_2);

		userField = new JTextField();
		userField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				checkName();// 确认用户名是否合法
			}
		});
		userField.setBounds(172, 139, 294, 39);
		getContentPane().add(userField);
		userField.setColumns(10);

		JLabel label_3 = new JLabel("用户名长度不超过20位");
		label_3.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label_3.setBounds(491, 150, 176, 16);
		getContentPane().add(label_3);

		JLabel label_4 = new JLabel("密码长度不超过20位");
		label_4.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label_4.setBounds(491, 221, 176, 16);
		getContentPane().add(label_4);

		JButton button_1 = new JButton("注册");
		button_1.setForeground(new Color(0, 0, 255));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_1.setBounds(133, 372, 128, 45);
		getContentPane().add(button_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(172, 210, 294, 39);
		getContentPane().add(passwordField);

		JLabel lblNewLabel = new JLabel("学    号：");
		lblNewLabel.setForeground(new Color(178, 34, 34));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(70, 290, 100, 28);
		getContentPane().add(lblNewLabel);

		numberField = new JTextField();
		numberField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				// 屏蔽其他字符，使得用户只能输入数字
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 屏蔽掉非法输入
				}
			}

		});
		numberField.setBounds(172, 288, 294, 39);
		getContentPane().add(numberField);
		numberField.setColumns(10);

		JLabel label_5 = new JLabel("请输入您的学号");
		label_5.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		label_5.setBounds(491, 299, 111, 16);
		getContentPane().add(label_5);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("系统");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("普通用户注册");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegistNormal();// 普通用户注册窗口
				dispose();
			}
		});
		menu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("管理员注册");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegistAdmin();// 管理员注册窗口
				dispose();
			}
		});
		menu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("返回登录");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();// 登录界面
				dispose();
			}
		});
		menu.add(menuItem_2);

		JMenuItem menuItem_3 = new JMenuItem("退出系统");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ParaDialog();// 确认退出弹窗
			}
		});
		menu.add(menuItem_3);

		this.setVisible(true);
	}

	/**
	 * 注册一个新用户
	 */
	protected void register() {
		String userName = userField.getText().trim();
		String password = new String(passwordField.getPassword());
		password = password.trim();
		String num = numberField.getText().trim();
		// 判断输入框是否合法
		if (userName.equals("") || password.equals("") || num.equals("")) {
			JOptionPane.showMessageDialog(this, "用户名、密码和学号不能为空值！", "注册失败！", JOptionPane.WARNING_MESSAGE);
		} else if (password.length() > 20) {
			JOptionPane.showMessageDialog(this, "密码字符长度不能超过20位！", "注册失败！", JOptionPane.WARNING_MESSAGE);
		} else if (!num.equals("")) { // 如果学生学号非空， 执行如下程序
			if (DAO.StudentDAO.checkNum(num) != null) { // 判断学号是否存在，使用student表
				if (DAO.UserDAO.checkNum(num) != null) { // 学生学号若存在，则判断学号是否被注册，使用visitor_user表
					JOptionPane.showMessageDialog(this, "此学号已被注册！", "注册失败！", JOptionPane.WARNING_MESSAGE);
				} else { // 若学号存在于student表中且未存在于visitor_user表中，则开始注册
					DAO.UserDAO userDAO = new DAO.UserDAO();
					if (userDAO.register(userName, password, num)) { // 注册成功
						JOptionPane.showMessageDialog(this, "注册成功！");
						new RegistNormal();
						dispose();
					} else { // 注册失败
						JOptionPane.showMessageDialog(this, "注册失败！", "注册失败！", JOptionPane.WARNING_MESSAGE);
					}
				}
			} else { // 学号不存在的情况，提醒用户
				JOptionPane.showMessageDialog(this, "学生学号不存在！", "注册失败！", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * 判断用户名是否被注册
	 */
	protected void checkName() {
		String userName = userField.getText().trim();
		UserDAO userDAO = new UserDAO();
		if ((userDAO.checkName(userName) != null) || userDAO.checkAdmin(userName) != null) {// 判断用户名是否存在，如果存在则提醒用户用户名已经被注册
			JOptionPane.showMessageDialog(this, "此用户名已经存在！", "注册失败！", JOptionPane.WARNING_MESSAGE);
			new RegistNormal();
			dispose();
		} else if (userName.length() > 20) {// 判断学号是否合法
			JOptionPane.showMessageDialog(this, "用户名字符长度不能超过20位！", "注册失败！", JOptionPane.WARNING_MESSAGE);
			new RegistNormal();
			dispose();
		}
	}

	// 设置背景图片
	public void setBack() {
		((JPanel) this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("Image/register.jpg"); // 添加图片
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
}
