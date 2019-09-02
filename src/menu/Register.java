package menu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import DAO.UserDAO;
import javax.swing.JMenuItem;

//此类为注册的主界面，用户通过本界面选择注册普通用户或者管理员用户
public class Register extends JFrame {
	public Register() {
		// 设置背景图片
		setBack();
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
		c.add(jp);

		setTitle("学生管理系统-注册页");
		setSize(700, 500);
		// 设置屏幕居中
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置
		getContentPane().setLayout(null);

		JButton button = new JButton("返回");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();// 返回登录界面
				dispose();
			}
		});
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button.setBounds(278, 372, 128, 45);
		getContentPane().add(button);

		JLabel label = new JLabel("新用户注册...");
		label.setFont(new Font("Lantinghei SC", Font.PLAIN, 30));
		label.setForeground(new Color(0, 0, 205));
		label.setBounds(70, 77, 204, 45);
		getContentPane().add(label);

		JButton button_1 = new JButton("普通用户注册");
		button_1.setForeground(new Color(255, 99, 71));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegistNormal();// 普通用户注册界面
				dispose();
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_1.setBounds(137, 212, 144, 58);
		getContentPane().add(button_1);

		JButton button_3 = new JButton("管理员注册");
		button_3.setForeground(new Color(75, 0, 130));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegistAdmin();// 管理员注册界面
				dispose();
			}
		});
		button_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_3.setBounds(399, 212, 144, 58);
		getContentPane().add(button_3);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("系统");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("普通用户注册");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegistNormal();
				dispose();
			}
		});
		menu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("管理员注册");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegistAdmin();
				dispose();
			}
		});
		menu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("返回登录");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				dispose();
			}
		});
		menu.add(menuItem_2);

		JMenuItem menuItem_3 = new JMenuItem("退出系统");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ParaDialog();
			}
		});
		menu.add(menuItem_3);

		this.setVisible(true);
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
