package function;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

//此类用于删除学生的所有信息
//在输入学号时系统设置为用户只能输入数字，输入其它字符无效，保证学号的真实性。
public class AdminDelete extends JFrame {
	private JTextField textField;

	public AdminDelete() {
		setBack();
		Container c = getContentPane(); // 获取JFrame面板
		JPanel jp = new JPanel(); // 创建个JPanel
		jp.setOpaque(false); // 把JPanel设置为透明 这样就不会遮住后面的背景
		c.add(jp);

		// getContentPane().setBackground(new Color(240, 255, 240));
		setTitle("学生管理系统-删除学生");
		setSize(700, 500);
		// 窗口居中
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置
		getContentPane().setLayout(null);

		JButton button = new JButton("< 返回主菜单");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new menu.AdmainMenu(); // 返回主菜单
				dispose();
			}
		});
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button.setBounds(6, 6, 128, 36);
		getContentPane().add(button);

		JButton button_1 = new JButton("退出系统");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new dialog.ParaDialog();// 退出系统，转到确认退出的弹窗
			}
		});
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		button_1.setBounds(566, 6, 128, 36);
		getContentPane().add(button_1);

		JLabel label = new JLabel("学生管理系统-删除学生");
		label.setForeground(new Color(0, 0, 128));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		label.setBounds(180, 68, 325, 36);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("学号：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		label_1.setBounds(119, 151, 61, 16);
		getContentPane().add(label_1);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			// 此处设置监听器，使得用户只能输入数字，输入其它字符无效，下同
			public void keyTyped(KeyEvent e) {
				int keyChar = e.getKeyChar();
				if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {

				} else {
					e.consume(); // 屏蔽掉非法输入
				}
			}

		});
		textField.setBounds(180, 142, 255, 36);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton deleteButton = new JButton("删除学生");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteStudent();
			}
		});
		deleteButton.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		deleteButton.setBounds(498, 142, 122, 36);
		getContentPane().add(deleteButton);

		this.setVisible(true);
	}

	/**
	 * 通过学号删除学生
	 */
	public void deleteStudent() {
		String STU_NUM = textField.getText().trim();
		if (STU_NUM.equals("")) { // 判断学号是否为空
			JOptionPane.showMessageDialog(this, "学号不能为空！", "删除失败！", JOptionPane.WARNING_MESSAGE);
		} else {
			try {// 首先删除学生信息表中的学生信息
				int result = DAO.StudentDAO.deleteStudent(STU_NUM);
				if (result == 1) {
					int result1 = DAO.StudentDAO.deleteScore(STU_NUM);
					if (result1 == 1) { // 删除学生信息后再删除该学生在学生成绩表中的成绩
						JOptionPane.showMessageDialog(this, "删除成功！");
						new AdminDelete(); // 删除成功，刷新界面
						dispose();
					} else {
						JOptionPane.showMessageDialog(this, "学号无对应学生！", "删除失败！", JOptionPane.WARNING_MESSAGE);
					}
				} else {// 学号输错，则提醒
					JOptionPane.showMessageDialog(this, "学号无对应学生！", "删除失败！", JOptionPane.WARNING_MESSAGE);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 设置背景图片
	public void setBack() {
		((JPanel) this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("Image/admindelete.jpg"); // 添加图片
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
	}
}
