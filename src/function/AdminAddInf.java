package function;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JTextField;

import DAO.StudentDAO;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;

//此类用于添加学生信息。用户必须输入学号以及姓名，其余选填。系统将所有信息添加到student表中，
//并且同时将学号添加到student_score表中，并在student_score表中自动将学生成绩设为null。
//学号长度不超过11位，姓名不超过10位，手机号码不超过20位，电子邮箱不超过100位。
//在此类中，系统将学号、手机号码设置为只能填写数字，填写数字以外的字符不显示、不记录，使得学号、手机号码真实有效。
public class AdminAddInf extends JFrame {
	private JTextField IDField;
	private JTextField NameField;
	private JTextField TelField;
	private JTextField MailField;
	private JComboBox<String> comboBox;

	public AdminAddInf() {
		// 设置图片背景
		setBack();
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景
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

		JLabel label = new JLabel("学生管理系统-添加学生信息");
		label.setForeground(new Color(0, 0, 128));
		label.setBackground(new Color(0, 0, 205));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label.setBounds(147, 62, 382, 46);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("请输入学生信息：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_1.setBounds(30, 137, 182, 28);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("学    号：");
		label_2.setForeground(new Color(255, 69, 0));
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_2.setBounds(30, 177, 72, 37);
		getContentPane().add(label_2);

		IDField = new JTextField();
		IDField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				checkID(); // 验证学号是否重复
			}
		});
		IDField.addKeyListener(new KeyAdapter() {
			// 此处判断用户输入的字符是否为数字，如果是数字则正常显示，如果为其他字符则屏蔽，下同。
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 屏蔽掉非法输入
				}
			}

		});
		IDField.setBounds(114, 183, 206, 28);
		getContentPane().add(IDField);
		IDField.setColumns(10);

		JLabel label_3 = new JLabel("性    别：");
		label_3.setForeground(new Color(255, 69, 0));
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_3.setBounds(30, 243, 72, 37);
		getContentPane().add(label_3);

		NameField = new JTextField();
		NameField.setBounds(416, 183, 206, 28);
		getContentPane().add(NameField);
		NameField.setColumns(10);

		JButton SureButton = new JButton("确认添加");
		SureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent(); // 用户点击添加，则转到对应方法
			}
		});
		SureButton.setForeground(new Color(0, 0, 255));
		SureButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		SureButton.setBounds(287, 414, 129, 37);
		getContentPane().add(SureButton);

		JLabel label_4 = new JLabel("姓    名：");
		label_4.setForeground(new Color(255, 69, 0));
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_4.setBounds(332, 177, 72, 37);
		getContentPane().add(label_4);

		JButton button_2 = new JButton("退出系统");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ParaDialog(); // 用户选择退出，则跳转到确认退出弹窗类
			}
		});
		button_2.setBounds(565, 6, 129, 40);
		getContentPane().add(button_2);

		JLabel label_8 = new JLabel("注：学号长度不超过11位，姓名长度不超过10位，手机长度不超过20位，邮箱长度不超过100位。");
		label_8.setBounds(30, 386, 592, 16);
		getContentPane().add(label_8);

		JLabel label_5 = new JLabel("手    机：");
		label_5.setForeground(new Color(255, 69, 0));
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_5.setBounds(332, 243, 72, 37);
		getContentPane().add(label_5);

		JLabel label_6 = new JLabel("邮    箱：");
		label_6.setForeground(new Color(255, 69, 0));
		label_6.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_6.setBounds(30, 311, 72, 37);
		getContentPane().add(label_6);

		TelField = new JTextField();
		TelField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 屏蔽掉非法输入
				}
			}

		});
		TelField.setToolTipText("");
		TelField.setColumns(10);
		TelField.setBounds(416, 249, 206, 28);
		getContentPane().add(TelField);

		MailField = new JTextField();
		MailField.setToolTipText("");
		MailField.setColumns(10);
		MailField.setBounds(114, 317, 508, 28);
		getContentPane().add(MailField);

		String[] sex = { "", "M", "F" };
		comboBox = new JComboBox<String>(sex);
		comboBox.setSelectedIndex(0); // 设置默认选项
		comboBox.setBounds(114, 251, 206, 27);
		getContentPane().add(comboBox);

		// 设置标题等，并将窗口设置为居中
		setTitle("学生管理系统-添加学生");
		setSize(700, 500);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置

		this.setVisible(true);
	}

	/**
	 * 添加学生
	 */
	public void addStudent() {
		String STU_NUM = IDField.getText().trim();
		String STU_NAME = NameField.getText().trim();
		String STU_SEX = (String) comboBox.getSelectedItem();
		String STU_TEL = TelField.getText().trim();
		String STU_MAIL = MailField.getText().trim();
		// 防止用户输入空的学号以及姓名，或是信息不符合相关规则
		if (STU_NUM.equals("") || STU_NAME.equals("")) {
			JOptionPane.showMessageDialog(this, "姓名、学号不能为空值！", "添加失败！", JOptionPane.WARNING_MESSAGE);
		} else if (STU_TEL.length() > 20) {
			JOptionPane.showMessageDialog(this, "手机号码应小于20位！", "添加失败！", JOptionPane.WARNING_MESSAGE);
		} else if (STU_MAIL.length() > 100) {
			JOptionPane.showMessageDialog(this, "邮箱长度应小于100位！", "添加失败！", JOptionPane.WARNING_MESSAGE);
		} else if (STU_NAME.length() > 10) {
			JOptionPane.showMessageDialog(this, "姓名长度不得超过10位！", "添加失败！", JOptionPane.WARNING_MESSAGE);
			NameField.grabFocus();
		} else {
			try { // 如果填写正确，则执行操作。
				new DAO.StudentDAO();
				// 将学生信息添加到student表中
				int result = StudentDAO.addStudent(STU_NUM, STU_NAME, STU_SEX, STU_TEL, STU_MAIL);
				if (result == 1) { // 如果信息添加成功，同时将学号添加到student_score表中
					int result1 = StudentDAO.addStudentNum(STU_NUM);
					if (result1 == 1) { // 添加成功
						JOptionPane.showMessageDialog(this, "添加成功！");
						new AdminAddInf(); // 刷新界面
						dispose();
					} else {
						JOptionPane.showMessageDialog(this, "添加失败！", "添加失败！", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "添加失败！", "添加失败！", JOptionPane.WARNING_MESSAGE);
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
		if (studentDAO.checkID(ID) != null) { // 判断学号是否存在
			JOptionPane.showMessageDialog(this, "此学号已经存在！", "添加失败！", JOptionPane.WARNING_MESSAGE);
			new AdminAddInf(); // 刷新界面
			dispose();
		} else if (ID.length() > 11) { // 判断学号是否过长
			JOptionPane.showMessageDialog(this, "学号长度不得超过11位！", "添加失败！", JOptionPane.WARNING_MESSAGE);
			new AdminAddInf(); // 刷新界面
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
