package function;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//此类用于普通用户的查询
//系统自动返回普通用户的学号，使得用户点击查询信息立即显示自己的信息。
public class NormalSearch extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel NameLabel;
	private JLabel SexLabel;
	private JButton button_2;
	private JLabel TelLabel;
	private JLabel MailLabel;
	private JLabel MathLabel;
	private JLabel ChineseLabel;
	private JLabel EnglishLabel;
	private JLabel SumLabel;
	private JLabel SearchLabel;

	/**
	 * Create the frame.
	 */
	public NormalSearch() {
		// 设置背景图片
		setBack();
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
		c.add(jp);

		setTitle("学生管理系统-查询");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置

		getContentPane().setLayout(null);

		SexLabel = new JLabel("");
		SexLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		SexLabel.setBounds(116, 282, 242, 28);
		getContentPane().add(SexLabel);

		NameLabel = new JLabel("");
		NameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		NameLabel.setBounds(116, 212, 242, 28);
		getContentPane().add(NameLabel);

		JButton button_1 = new JButton("< 返回主菜单");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new menu.NormalMenu();
				dispose();
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_1.setBounds(6, 6, 129, 40);
		getContentPane().add(button_1);

		JLabel label = new JLabel("学生管理系统-查询信息");
		label.setForeground(new Color(0, 0, 139));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label.setBounds(192, 48, 337, 46);
		getContentPane().add(label);

		button_2 = new JButton("退出系统");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ParaDialog();
			}
		});
		button_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_2.setBounds(565, 6, 129, 40);
		getContentPane().add(button_2);

		TelLabel = new JLabel("");
		TelLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		TelLabel.setBounds(116, 350, 242, 28);
		getContentPane().add(TelLabel);

		MailLabel = new JLabel("");
		MailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		MailLabel.setBounds(116, 420, 242, 28);
		getContentPane().add(MailLabel);

		MathLabel = new JLabel("");
		MathLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		MathLabel.setBounds(370, 212, 242, 28);
		getContentPane().add(MathLabel);

		ChineseLabel = new JLabel("");
		ChineseLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		ChineseLabel.setBounds(370, 282, 242, 28);
		getContentPane().add(ChineseLabel);

		EnglishLabel = new JLabel("");
		EnglishLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		EnglishLabel.setBounds(370, 350, 242, 28);
		getContentPane().add(EnglishLabel);

		SumLabel = new JLabel("");
		SumLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		SumLabel.setBounds(370, 420, 242, 28);
		getContentPane().add(SumLabel);

		SearchLabel = new JLabel("");
		SearchLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		SearchLabel.setBounds(116, 146, 242, 28);
		getContentPane().add(SearchLabel);

		this.setVisible(true);
	}

	/**
	 * 查询对应学号的学生信息以及成绩，并显示到界面上
	 * 
	 * @param STU_NUM
	 */
	public void queryStudent() {
		try {
			// 首先查询该用户名对应的学号，利用visitor_user表查询
			String STU_NUM = DAO.StudentDAO.queryNum();
			// 查询到学号后，查询学生的信息，利用student表
			String[] value = DAO.StudentDAO.queryOneStudent(STU_NUM);
			if (value != null) {
				SearchLabel.setText("学号" + STU_NUM + "的查询结果：");
				NameLabel.setText("姓名：" + value[0]);
				SexLabel.setText("性别：" + value[1]);
				TelLabel.setText("电话：" + value[2]);
				MailLabel.setText("邮箱：" + value[3]);
				// 查询信息并显示后，查询学生的成绩，利用student_score表
				String[] value1 = DAO.StudentDAO.queryStudentScore(STU_NUM);
				if (value1 != null) {
					MathLabel.setText("数学成绩：" + value1[0]);
					ChineseLabel.setText("语文成绩：" + value1[1]);
					EnglishLabel.setText("英语成绩：" + value1[2]);
					SumLabel.setText("总成绩：" + value1[3]);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 设置背景图片
	public void setBack() {
		((JPanel) this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("Image/search.jpg"); // 添加图片
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
}
