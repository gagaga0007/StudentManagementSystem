package dialog;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.UserDAO;
import menu.AdmainMenu;
import menu.NormalMenu;
import menu.RegistNormal;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

//此类用于修改密码，不论普通用户还是管理员用户，均可以通过此方法进行更改密码的操作。
public class ChangePassword extends JDialog {
	private JTextField usernameField;
	private JPasswordField newPasswordField;
	private JPasswordField checkPasswordField;
	private JPasswordField oldPasswordField;

	public ChangePassword() {
		setTitle("修改密码");
		setSize(350, 400);
		getContentPane().setLayout(null);
		// 将屏幕置于屏幕中央
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置

		JLabel label = new JLabel("您的用户名：");
		label.setForeground(new Color(139, 0, 0));
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		label.setBounds(27, 104, 95, 16);
		getContentPane().add(label);

		usernameField = new JTextField();
		usernameField.setBounds(134, 100, 183, 26);
		getContentPane().add(usernameField);
		usernameField.setColumns(10);

		JLabel label_1 = new JLabel("您的原密码：");
		label_1.setForeground(new Color(139, 0, 0));
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		label_1.setBounds(27, 163, 95, 16);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("您的新密码：");
		label_2.setForeground(new Color(139, 0, 0));
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		label_2.setBounds(27, 220, 95, 16);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("确认新密码：");
		label_3.setForeground(new Color(139, 0, 0));
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		label_3.setBounds(27, 279, 95, 16);
		getContentPane().add(label_3);

		JButton button = new JButton("确认修改");
		button.setForeground(new Color(0, 0, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkName();
			}
		});
		button.setBounds(27, 330, 117, 29);
		getContentPane().add(button);

		JLabel label_4 = new JLabel("修改用户密码");
		label_4.setForeground(new Color(0, 0, 128));
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label_4.setBounds(115, 33, 120, 34);
		getContentPane().add(label_4);

		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(134, 216, 183, 26);
		getContentPane().add(newPasswordField);

		checkPasswordField = new JPasswordField();
		checkPasswordField.setBounds(134, 275, 183, 26);
		getContentPane().add(checkPasswordField);

		oldPasswordField = new JPasswordField();
		oldPasswordField.setBounds(134, 159, 183, 26);
		getContentPane().add(oldPasswordField);

		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(200, 330, 117, 29);
		getContentPane().add(button_1);

		this.setVisible(true);
	}

	/**
	 * 修改密码
	 * 
	 * @param a
	 *            确认用户权限
	 */
	public void changePassword(int a) {
		String userName = usernameField.getText().trim();
		String oldPassword = new String(oldPasswordField.getPassword());
		oldPassword = oldPassword.trim();
		String newPassword = new String(newPasswordField.getPassword());
		newPassword = newPassword.trim();
		String checkPassword = new String(checkPasswordField.getPassword());
		checkPassword = checkPassword.trim();
		int checkNum = a; // 定义一个变量，用于确认用户的类型，传入的1表示管理员用户，传入的2表示普通用户
		if (oldPassword.equals("") || newPassword.equals("")) { // 新密码框不准为空
			JOptionPane.showMessageDialog(this, "请输入新密码以及确认密码！", "修改失败！", JOptionPane.WARNING_MESSAGE);
		} else {
			if (!newPassword.equals(checkPassword)) {// 确认两次输入的密码是否一致
				JOptionPane.showMessageDialog(this, "新密码两次输入不一致！", "修改失败！", JOptionPane.WARNING_MESSAGE);
			} else {// 如果一致，开始更改密码，传入参数分别为用户名、新密码、用于确认用户类型的变量
				if (DAO.UserDAO.changePassword(userName, newPassword, checkNum)) {
					JOptionPane.showMessageDialog(this, "修改成功！");
					new ChangePassword();
					dispose();
				} else {// 修改失败则提示用户密码修改失败
					JOptionPane.showMessageDialog(this, "修改失败！", "修改失败！", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}

	/**
	 * 确认用户名是否存在
	 */
	public void checkName() {
		String userName = usernameField.getText().trim();
		UserDAO userDAO = new UserDAO();
		if (userName.equals("")) {// 判断用户名是否输入为空
			JOptionPane.showMessageDialog(this, "用户名不能为空！", "修改失败！", JOptionPane.WARNING_MESSAGE);
		} else {
			if ((userDAO.checkName(userName) != null) || userDAO.checkAdmin(userName) != null) {// 判断用户名是否存在
				checkOldPassword();// 若存在，确认原密码正确性
			} else { // 否则用户不存在，提醒用户
				JOptionPane.showMessageDialog(this, "用户名不存在！", "修改失败！", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * 确认原密码是否正确
	 */
	public void checkOldPassword() {
		String userName = usernameField.getText().trim();
		String oldPassword = new String(oldPasswordField.getPassword());
		oldPassword = oldPassword.trim();
		DAO.UserDAO userDAO = new DAO.UserDAO();
		if (oldPassword.equals("")) {// 判断用户是否输入为空
			JOptionPane.showMessageDialog(this, "请输入原密码！", "修改失败！", JOptionPane.WARNING_MESSAGE);
		} else {
			if (userDAO.login(userName, oldPassword) == "1") {
				changePassword(1);// 如果密码正确，且是管理员用户，变量值赋值为1
			} else if (userDAO.login(userName, oldPassword) == "2") {
				changePassword(2);// 如果密码正确，是普通用户，则赋值为2
			} else { // 如果密码错误，提示用户
				JOptionPane.showMessageDialog(this, "原密码错误！", "修改失败！", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
