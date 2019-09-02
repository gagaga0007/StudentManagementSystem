package function;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.StudentDAO;

import java.awt.Font;

//此类用于查询某学生的信息
//结果将返回学生信息表以及学生成绩表中的学生信息，并显示出来
public class AdminSearch extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField IDField;
	private JLabel NameLabel;
	private JLabel SexLabel;
	private JLabel Label;
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
	public AdminSearch() {
		// 设置背景图片
		setBack();
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
		c.add(jp);

		setTitle("学生管理系统-查询");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		// 使屏幕居中显示。
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置

		getContentPane().setLayout(null);

		Label = new JLabel("\u5B66\u53F7\uFF1A");
		Label.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		Label.setForeground(Color.RED);
		Label.setBounds(116, 194, 72, 37);
		getContentPane().add(Label);

		JButton button = new JButton("\u67E5\u8BE2");
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				queryStudent();
			}
		});
		button.setBounds(524, 194, 113, 35);
		getContentPane().add(button);

		IDField = new JTextField();
		IDField.addKeyListener(new KeyAdapter() {
			// 使得用户只能输入数字，输入其他字符自动屏蔽，下同，
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 屏蔽掉非法输入
				}
			}

		});
		IDField.setBounds(237, 192, 236, 40);
		getContentPane().add(IDField);
		IDField.setColumns(10);

		SexLabel = new JLabel("");
		SexLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		SexLabel.setBounds(116, 340, 242, 28);
		getContentPane().add(SexLabel);

		NameLabel = new JLabel("");
		NameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		NameLabel.setBounds(116, 300, 242, 28);
		getContentPane().add(NameLabel);

		JButton button_1 = new JButton("< 返回主菜单");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new menu.AdmainMenu();
				dispose();
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_1.setBounds(6, 6, 129, 40);
		getContentPane().add(button_1);

		JLabel label = new JLabel("学生管理系统-查询学生");
		label.setForeground(new Color(0, 0, 139));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label.setBounds(192, 48, 337, 46);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("请输入您想查询学生的学号：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_1.setBounds(105, 124, 260, 28);
		getContentPane().add(label_1);

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
		TelLabel.setBounds(116, 380, 242, 28);
		getContentPane().add(TelLabel);

		MailLabel = new JLabel("");
		MailLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		MailLabel.setBounds(116, 420, 242, 28);
		getContentPane().add(MailLabel);

		MathLabel = new JLabel("");
		MathLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		MathLabel.setBounds(370, 299, 242, 28);
		getContentPane().add(MathLabel);

		ChineseLabel = new JLabel("");
		ChineseLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		ChineseLabel.setBounds(370, 340, 242, 28);
		getContentPane().add(ChineseLabel);

		EnglishLabel = new JLabel("");
		EnglishLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		EnglishLabel.setBounds(370, 380, 242, 28);
		getContentPane().add(EnglishLabel);

		SumLabel = new JLabel("");
		SumLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		SumLabel.setBounds(370, 420, 242, 28);
		getContentPane().add(SumLabel);

		SearchLabel = new JLabel("");
		SearchLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		SearchLabel.setBounds(116, 260, 242, 28);
		getContentPane().add(SearchLabel);

		this.setVisible(true);
	}

	/**
	 * 通过学号查询学生姓名和性别，并显示到界面上
	 * 
	 * @param STU_NUM
	 */
	public void queryStudent() {
		String STU_NUM = IDField.getText().trim();
		// 判断学号是否合法
		if (STU_NUM.equals("")) {
			JOptionPane.showMessageDialog(this, "学号不能为空值！", "查询失败！", JOptionPane.WARNING_MESSAGE);
		} else if (STU_NUM.length() > 11) {
			JOptionPane.showMessageDialog(this, "学号长度不得超过11位！", "查询失败！", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				String[] value = DAO.StudentDAO.queryOneStudent(STU_NUM);
				if (value != null) { // 先返回学生的信息,利用student表
					SearchLabel.setText("查询结果：");
					NameLabel.setText("姓名：" + value[0]);
					SexLabel.setText("性别：" + value[1]);
					TelLabel.setText("电话：" + value[2]);
					MailLabel.setText("邮箱：" + value[3]);
					String[] value1 = DAO.StudentDAO.queryStudentScore(STU_NUM);
					if (value1 != null) { // 再返回学生的成绩，利用student_score表
						MathLabel.setText("数学成绩：" + value1[0]);
						ChineseLabel.setText("语文成绩：" + value1[1]);
						EnglishLabel.setText("英语成绩：" + value1[2]);
						SumLabel.setText("总成绩：" + value1[3]);
					}
				} else { // 如果没有该学生，提示用户没有这个学生，并将所有Label设置为空。
					SearchLabel.setText("");
					NameLabel.setText("");
					SexLabel.setText("");
					TelLabel.setText("");
					MailLabel.setText("");
					MathLabel.setText("");
					ChineseLabel.setText("");
					EnglishLabel.setText("");
					SumLabel.setText("");
					JOptionPane.showMessageDialog(this, "学号" + STU_NUM + "无对应学生!", "查询失败！",
							JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
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
