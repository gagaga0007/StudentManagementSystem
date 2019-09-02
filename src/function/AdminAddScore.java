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

//此类用于添加学生成绩。用户必须输入想要添加的学号，并至少填写一科成绩。
//学号不得超过11位，成绩不得超过3位。
//在此类中，系统将学号、各科成绩均设置为只能填写数字，填写其他字符无效，保证填写的信息真实有效。
public class AdminAddScore extends JFrame {
	private JTextField IDField;
	private JTextField MathField;
	private JTextField ChineseField;
	private JTextField EnglishField;
	private JLabel checkLabel;

	public AdminAddScore() {
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

		JLabel label = new JLabel("学生管理系统-添加学生成绩");
		label.setForeground(new Color(0, 0, 128));
		label.setBackground(new Color(0, 0, 205));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label.setBounds(156, 49, 387, 46);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("请输入学生信息：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_1.setBounds(40, 143, 182, 28);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("学    号：");
		label_2.setForeground(new Color(255, 69, 0));
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_2.setBounds(40, 198, 72, 37);
		getContentPane().add(label_2);

		IDField = new JTextField();
		IDField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				checkID(); // 验证学号是否重复
			}
		});
		IDField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				// 此处为判断用户输入的信息，输入数字正确显示，输入其他字符无效并将其屏蔽，下同。
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 屏蔽掉非法输入
				}
			}

		});
		IDField.setBounds(124, 204, 216, 28);
		getContentPane().add(IDField);
		IDField.setColumns(10);

		JLabel label_3 = new JLabel("语文成绩：");
		label_3.setForeground(new Color(255, 69, 0));
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_3.setBounds(40, 284, 97, 37);
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
		MathField.setBounds(443, 204, 216, 28);
		getContentPane().add(MathField);
		MathField.setColumns(10);

		JButton SureButton = new JButton("确认添加");
		SureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addScore();
			}
		});
		SureButton.setForeground(new Color(0, 0, 255));
		SureButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		SureButton.setBounds(287, 414, 129, 37);
		getContentPane().add(SureButton);

		JLabel label_4 = new JLabel("数学成绩：");
		label_4.setForeground(new Color(255, 69, 0));
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_4.setBounds(355, 198, 90, 37);
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
		ChineseField.setBounds(125, 290, 216, 28);
		getContentPane().add(ChineseField);

		JButton button_2 = new JButton("退出系统");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ParaDialog();
			}
		});
		button_2.setBounds(565, 6, 129, 40);
		getContentPane().add(button_2);

		JLabel label_8 = new JLabel("注：学号字符长度不大于11位，各科成绩均填写数字。");
		label_8.setBounds(40, 352, 356, 16);
		getContentPane().add(label_8);

		JLabel label_5 = new JLabel("英语成绩：");
		label_5.setForeground(new Color(255, 69, 0));
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_5.setBounds(355, 284, 90, 37);
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
		EnglishField.setBounds(443, 291, 216, 28);
		getContentPane().add(EnglishField);

		checkLabel = new JLabel("");
		checkLabel.setForeground(Color.RED);
		checkLabel.setBounds(277, 386, 149, 16);
		getContentPane().add(checkLabel);

		// 添加标题等，并将界面设置居中
		setTitle("学生管理系统-添加学生成绩");
		setSize(700, 500);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置

		this.setVisible(true);
	}

	/**
	 * 添加成绩
	 */
	public void addScore() {
		String STU_NUM = IDField.getText().trim();
		String STU_MATH = MathField.getText().trim();
		String STU_CHINESE = ChineseField.getText().trim();
		String STU_ENGLISH = EnglishField.getText().trim();
		if (STU_NUM.equals("")) { // 如果用户未输入学号，提示用户输入学号
			JOptionPane.showMessageDialog(this, "请输入学号！", "添加失败！", JOptionPane.WARNING_MESSAGE);
		} else if (STU_MATH.equals("") && STU_CHINESE.equals("") && STU_ENGLISH.equals("")) {
			// 如果用户未输入任何成绩，不进行任何操作
			checkLabel.setText("请输入至少一科成绩！");
		} else {
			// 如果用户未输入成绩，自动赋值为0（为了数据库不报错）。
			if (STU_MATH.equals("")) {
				STU_MATH = "0";
			}
			if (STU_CHINESE.equals("")) {
				STU_CHINESE = "0";
			}
			if (STU_ENGLISH.equals("")) {
				STU_ENGLISH = "0";
			}
			// 判断用户输入的字符
			if (STU_CHINESE.length() > 3 || STU_MATH.length() > 3 || STU_ENGLISH.length() > 3) {
				JOptionPane.showMessageDialog(this, "请输入正确的成绩！", "添加失败！", JOptionPane.WARNING_MESSAGE);
			} else {
				try { // 如果全部输入正确将执行操作
					new DAO.StudentDAO();
					// 添加学生成绩
					int result = StudentDAO.addScore(STU_NUM, STU_MATH, STU_CHINESE, STU_ENGLISH);
					if (result == 1) { // 如果成绩添加成功，则添加学生的总成绩(总成绩数据库设置为int型，需要强制转换)
						int result1 = StudentDAO.addSum(STU_NUM);
						if (result1 == 1) { // 添加成功
							JOptionPane.showMessageDialog(this, "添加成功！");
							new AdminAddScore();
							dispose();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断学号是否存在
	 */
	public void checkID() {
		String ID = IDField.getText().trim();
		StudentDAO studentDAO = new StudentDAO();
		if (ID.equals("")) { // 如果未输入学号，提示
			JOptionPane.showMessageDialog(this, "学号不能为空！", "添加失败！", JOptionPane.WARNING_MESSAGE);
		} else if (studentDAO.checkID(ID) == null) { // 如果学号不存在，提示
			JOptionPane.showMessageDialog(this, "此学号不存在！", "添加失败！", JOptionPane.WARNING_MESSAGE);
			new AdminAddScore(); // 重新刷新界面
			dispose();
		} else if (ID.length() > 11) { // 如果学号长度过长，提示
			JOptionPane.showMessageDialog(this, "学号长度不得超过11位！", "添加失败！", JOptionPane.WARNING_MESSAGE);
			new AdminAddScore(); // 重新刷新界面
			dispose();
		}
	}

	// 设置背景图片
	public void setBack() {
		((JPanel) this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("Image/adminadd.jpg"); // 添加图片
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
}
