package menu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

//管理员菜单
//包括管理员可以操作的各个选项
public class AdmainMenu extends JFrame {
	public AdmainMenu() {
		// 设置背景图片
		setBack();
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
		c.add(jp);

		setTitle("学生管理系统-菜单");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		// 设置窗口居中
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置

		// getContentPane().setBackground(new Color(255, 255, 240));
		getContentPane().setLayout(null);

		// 选择哪个选项就跳转到那个选项并释放菜单界面的资源
		JLabel label = new JLabel("您是“管理员用户”，请选择您想要的功能：");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setBounds(45, 139, 376, 29);
		getContentPane().add(label);

		JButton button = new JButton("添加学生信息");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new function.AdminAddInf();
				dispose();
			}
		});
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button.setForeground(new Color(0, 0, 0));
		button.setBackground(new Color(219, 112, 147));
		button.setBounds(78, 218, 137, 43);
		getContentPane().add(button);

		JButton button_1 = new JButton("删除学生所有信息");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new function.AdminDelete();
				dispose();
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_1.setBounds(464, 323, 137, 43);
		getContentPane().add(button_1);

		JButton button_2 = new JButton("修改学生信息");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new function.AdminUpdateInf();
				dispose();
			}
		});
		button_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_2.setBounds(78, 323, 137, 43);
		getContentPane().add(button_2);

		JButton button_3 = new JButton("查询学生所有信息");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new function.AdminSearch();
				dispose();
			}
		});
		button_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_3.setBounds(464, 218, 137, 43);
		getContentPane().add(button_3);

		JLabel label_1 = new JLabel("欢迎登陆学生管理系统!");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		label_1.setForeground(new Color(255, 20, 147));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(165, 59, 380, 43);
		getContentPane().add(label_1);

		JButton button_4 = new JButton("添加学生成绩");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new function.AdminAddScore();
				dispose();
			}
		});
		button_4.setForeground(Color.BLACK);
		button_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_4.setBackground(new Color(219, 112, 147));
		button_4.setBounds(266, 218, 137, 43);
		getContentPane().add(button_4);

		JButton button_6 = new JButton("修改学生成绩");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new function.AdminUpdateScore();
				dispose();
			}
		});
		button_6.setForeground(Color.BLACK);
		button_6.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_6.setBackground(new Color(219, 112, 147));
		button_6.setBounds(266, 323, 137, 43);
		getContentPane().add(button_6);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menu = new JMenu("系统");
		menuBar.add(menu);

		JMenuItem menuItem = new JMenuItem("修改密码");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ChangePassword();
			}
		});
		menu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("退出登录并返回");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				dispose();
			}
		});
		menu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("退出系统");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ParaDialog();
			}
		});
		menu.add(menuItem_2);

		this.setVisible(true);
	}

	// 设置背景图片
	public void setBack() {
		((JPanel) this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("Image/adminmenu.jpg"); // 添加图片
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
}
