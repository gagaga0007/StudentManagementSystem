package menu;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//此类为普通用户菜单
public class NormalMenu extends JFrame {
	public NormalMenu() {
		// 设置背景图片
		setBack();
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
		c.add(jp);

		// getContentPane().setBackground(new Color(240, 255, 255));
		setTitle("菜单");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		// 界面居中显示
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置
		getContentPane().setLayout(null);

		JLabel label = new JLabel("欢迎使用学生管理系统！");
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 35));
		label.setBounds(169, 60, 385, 43);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("您是“普通用户”，仅可进行查找操作。");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_1.setBounds(187, 156, 336, 28);
		getContentPane().add(label_1);

		JButton button = new JButton("查询信息");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new function.NormalSearch().queryStudent();
				dispose();
			}
		});
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button.setBounds(249, 264, 183, 43);
		getContentPane().add(button);

		JLabel label_2 = new JLabel("如有疑问，请联系管理员进行修改删除操作。");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		label_2.setBounds(434, 423, 260, 33);
		getContentPane().add(label_2);

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
		ImageIcon img = new ImageIcon("Image/normalmenu.jpg"); // 添加图片
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
}
