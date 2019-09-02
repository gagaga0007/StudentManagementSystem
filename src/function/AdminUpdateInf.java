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
import javax.swing.JComboBox;

//此方法用于修改学生的信息
//通过学号修改学生的信息，可以随意更改姓名、性别、手机号码以及电子邮件的其中一项或多项
public class AdminUpdateInf extends JFrame {
	private JTextField IDField;
	private JTextField NameField;
	private JTextField TelField;
	private JTextField MailField;
	private JComboBox<String> comboBox;
	private JLabel checkLabel;

	public AdminUpdateInf() {
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
				new menu.AdmainMenu(); // 返回主菜单
				dispose(); // 释放当前界面资源
			}
		});
		button.setBounds(6, 6, 129, 40);
		getContentPane().add(button);

		JLabel label = new JLabel("学生管理系统-修改学生信息");
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
			// 屏蔽掉其它字符输入，让用户只能输入数字
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

		JLabel label_3 = new JLabel("性    别：");
		label_3.setForeground(new Color(255, 0, 0));
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_3.setBounds(332, 285, 72, 37);
		getContentPane().add(label_3);

		NameField = new JTextField();
		NameField.setBounds(114, 291, 206, 28);
		getContentPane().add(NameField);
		NameField.setColumns(10);

		JButton SureButton = new JButton("确认修改");
		SureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkID(); // 首先确认学号
			}
		});
		SureButton.setForeground(new Color(0, 0, 255));
		SureButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		SureButton.setBounds(287, 424, 129, 37);
		getContentPane().add(SureButton);

		JLabel label_4 = new JLabel("姓    名：");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_4.setBounds(30, 285, 72, 37);
		getContentPane().add(label_4);

		JButton button_2 = new JButton("退出系统");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ParaDialog(); // 弹窗确认
			}
		});
		button_2.setBounds(565, 6, 129, 40);
		getContentPane().add(button_2);

		JLabel label_8 = new JLabel("注：学号长度不大于11位，姓名长度不大于10位，手机号码长度不大于20位，邮箱长度不大于100位。");
		label_8.setBounds(30, 386, 592, 16);
		getContentPane().add(label_8);

		JLabel label_5 = new JLabel("手    机：");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_5.setBounds(30, 337, 72, 37);
		getContentPane().add(label_5);

		JLabel label_6 = new JLabel("邮    箱：");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		label_6.setBounds(332, 337, 72, 37);
		getContentPane().add(label_6);

		TelField = new JTextField();
		TelField.setToolTipText("");
		TelField.setColumns(10);
		TelField.setBounds(114, 346, 206, 28);
		getContentPane().add(TelField);

		MailField = new JTextField();
		MailField.setToolTipText("");
		MailField.setColumns(10);
		MailField.setBounds(416, 343, 206, 28);
		getContentPane().add(MailField);

		JLabel label_7 = new JLabel("请输入新的学生信息：");
		label_7.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_7.setBounds(30, 242, 268, 28);
		getContentPane().add(label_7);

		checkLabel = new JLabel("");
		checkLabel.setForeground(Color.RED);
		checkLabel.setBounds(287, 251, 191, 16);
		getContentPane().add(checkLabel);

		String[] sex = { "", "M", "F" }; // 设置性别选择框
		comboBox = new JComboBox<String>(sex);
		comboBox.setSelectedIndex(0); // 默认为第一个空白选项
		comboBox.setBounds(416, 293, 206, 27);
		getContentPane().add(comboBox);

		setTitle("学生管理系统-添加学生");
		setSize(700, 500);
		// 屏幕居中显示
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
		String STU_NAME = NameField.getText().trim();
		String STU_SEX = (String) comboBox.getSelectedItem();
		String STU_TEL = TelField.getText().trim();
		String STU_MAIL = MailField.getText().trim();
		// 防止用户输入非法值
		if (STU_NAME.equals("") && STU_SEX.equals("") && STU_TEL.equals("") && STU_MAIL.equals("")) {
			checkLabel.setText("请修改至少一个信息！");
		} else if (STU_TEL.length() > 20) {
			JOptionPane.showMessageDialog(this, "手机号码应小于20位！", "修改失败！", JOptionPane.WARNING_MESSAGE);
		} else if (STU_MAIL.length() > 100) {
			JOptionPane.showMessageDialog(this, "邮箱长度应小于100位！", "修改失败！", JOptionPane.WARNING_MESSAGE);
		} else if (STU_NAME.length() > 10) {
			JOptionPane.showMessageDialog(this, "姓名长度不得超过10位！", "修改失败！", JOptionPane.WARNING_MESSAGE);
		} else {
			try {// 参数传入，更新信息
				int result = DAO.StudentDAO.updateStudent(STU_NUM, STU_NAME, STU_SEX, STU_TEL, STU_MAIL);
				if (result == 1) {// 返回1，更新成功
					JOptionPane.showMessageDialog(this, "修改成功！");
					new AdminUpdateInf();// 刷新页面
					dispose();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断学号是否非法
	 */
	public void checkID() {
		String ID = IDField.getText().trim();
		StudentDAO studentDAO = new StudentDAO();
		if (ID.equals("")) {// 为空
			JOptionPane.showMessageDialog(this, "学号不能为空！", "修改失败！", JOptionPane.WARNING_MESSAGE);
			new AdminUpdateInf();
			dispose();
		} else if (ID.length() > 11) {// 过长
			JOptionPane.showMessageDialog(this, "学号长度不得超过11位！", "修改失败！", JOptionPane.WARNING_MESSAGE);
			new AdminUpdateInf();
			dispose();
		} else if (studentDAO.checkID(ID) == null) {// 不存在
			JOptionPane.showMessageDialog(this, "学号不存在！", "修改失败！", JOptionPane.WARNING_MESSAGE);
			new AdminUpdateInf();
			dispose();
		} else {// 如果正确，跳转
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
