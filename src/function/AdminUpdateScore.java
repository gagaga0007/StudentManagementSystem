package function;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAO.StudentDAO;

//此类用于修改学生成绩
//用户可以通过学号修改指定学生，可以修改该学生的一科或多科成绩
public class AdminUpdateScore extends JFrame {
	private JTextField IDField;
	private JTextField MathField;
	private JTextField ChineseField;
	private JTextField EnglishField;
	private JLabel checkLabel;

	public AdminUpdateScore() {
		// 设置背景图片
		setBack();
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景 这样你就能在JPanel随意加组件了
		c.add(jp);

		// getContentPane().setBackground(new Color(240, 255, 255));
		getContentPane().setLayout(null);

		JButton button = new JButton("< 返回主菜单");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new menu.AdmainMenu();
				dispose();
			}
		});
		button.setBounds(6, 6, 129, 40);
		getContentPane().add(button);

		JLabel label = new JLabel("学生管理系统-修改学生成绩");
		label.setForeground(new Color(0, 0, 139));
		label.setBackground(new Color(0, 0, 205));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label.setBounds(147, 62, 382, 46);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("请输入想要更改学生的学号：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_1.setBounds(30, 137, 268, 28);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("学    号：");
		label_2.setForeground(new Color(255, 0, 0));
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_2.setBounds(30, 180, 72, 37);
		getContentPane().add(label_2);

		IDField = new JTextField();
		IDField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				checkID(); // 验证学号
			}
		});
		IDField.addKeyListener(new KeyAdapter() {
			// 使得用户只能输入数字，输入其他字符无效
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 屏蔽掉非法输入
				}
			}

		});
		IDField.setBounds(114, 186, 508, 28);
		getContentPane().add(IDField);
		IDField.setColumns(10);

		JLabel label_3 = new JLabel("语    文：");
		label_3.setForeground(new Color(255, 0, 0));
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_3.setBounds(147, 312, 72, 37);
		getContentPane().add(label_3);

		MathField = new JTextField();
		MathField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 屏蔽掉非法输入
				}
			}

		});
		MathField.setBounds(231, 272, 206, 28);
		getContentPane().add(MathField);
		MathField.setColumns(10);

		JButton SureButton = new JButton("确认修改");
		SureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkID();
			}
		});
		SureButton.setForeground(new Color(0, 0, 255));
		SureButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		SureButton.setBounds(287, 424, 129, 37);
		getContentPane().add(SureButton);

		JLabel label_4 = new JLabel("数    学：");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_4.setBounds(147, 266, 72, 37);
		getContentPane().add(label_4);

		ChineseField = new JTextField();
		ChineseField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 屏蔽掉非法输入
				}
			}

		});
		ChineseField.setToolTipText("");
		ChineseField.setColumns(10);
		ChineseField.setBounds(231, 318, 206, 28);
		getContentPane().add(ChineseField);

		JButton button_2 = new JButton("退出系统");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ParaDialog();
			}
		});
		button_2.setBounds(565, 6, 129, 40);
		getContentPane().add(button_2);

		JLabel label_5 = new JLabel("英    语：");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_5.setBounds(147, 358, 72, 37);
		getContentPane().add(label_5);

		EnglishField = new JTextField();
		EnglishField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 屏蔽掉非法输入
				}
			}

		});
		EnglishField.setToolTipText("");
		EnglishField.setColumns(10);
		EnglishField.setBounds(231, 364, 206, 28);
		getContentPane().add(EnglishField);

		JLabel label_7 = new JLabel("请输入新的学生成绩：");
		label_7.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_7.setBounds(30, 229, 268, 28);
		getContentPane().add(label_7);

		checkLabel = new JLabel("");
		checkLabel.setForeground(Color.RED);
		checkLabel.setBounds(310, 238, 166, 16);
		getContentPane().add(checkLabel);

		setTitle("学生管理系统-添加学生");
		setSize(700, 500);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置

		this.setVisible(true);
	}

	/**
	 * 更新学生信息
	 */
	public void updateStudent() {
		String STU_NUM = IDField.getText().trim();
		String STU_MATH = MathField.getText().trim();
		String STU_CHINESE = ChineseField.getText().trim();
		String STU_ENGLISH = EnglishField.getText().trim();
		// 防止用户输入值不合法
		if (STU_MATH.equals("") && STU_CHINESE.equals("") && STU_ENGLISH.equals("")) {
			checkLabel.setText("请输入至少一科成绩！");
		} else if (STU_CHINESE.length() > 3 || STU_MATH.length() > 3 || STU_ENGLISH.length() > 3) {
			JOptionPane.showMessageDialog(this, "请输入正确的成绩！", "修改失败！", JOptionPane.WARNING_MESSAGE);
		} else {
			try {// 传入各个参数修改各科成绩
				int result = DAO.StudentDAO.updateScore(STU_NUM, STU_MATH, STU_CHINESE, STU_ENGLISH);
				if (result == 1) {// 各科成绩修改成功后，修改该学生的总成绩
					int result1 = DAO.StudentDAO.updateSum(STU_NUM);
					if (result1 == 1) {// 修改成功
						JOptionPane.showMessageDialog(this, "修改成功！");
						new AdminUpdateScore();// 刷新界面
						dispose();
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断学号
	 */
	public void checkID() {
		String ID = IDField.getText().trim();
		StudentDAO studentDAO = new StudentDAO();
		if (ID.equals("")) {// 为空
			JOptionPane.showMessageDialog(this, "学号不能为空！", "修改失败！", JOptionPane.WARNING_MESSAGE);
			new AdminUpdateScore();
			dispose();
		} else if (ID.length() > 11) {// 过长
			JOptionPane.showMessageDialog(this, "学号长度不得超过11位！", "修改失败！", JOptionPane.WARNING_MESSAGE);
			new AdminUpdateScore();
			dispose();
		} else if (studentDAO.checkID(ID) == null) {// 不存在
			JOptionPane.showMessageDialog(this, "学号不存在！", "修改失败！", JOptionPane.WARNING_MESSAGE);
			new AdminUpdateScore();
			dispose();
		} else {// 开始更新
			updateStudent();
		}
	}

	// 设置背景图片
	public void setBack() {
		((JPanel) this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("Image/adminupdate.jpg"); // 添加图片
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
}
