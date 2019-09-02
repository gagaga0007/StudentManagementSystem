package dialog;

import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//此类用于提醒用户是否确认退出
public class ParaDialog extends JDialog {
	public ParaDialog() {
		setTitle("退出");
		setSize(300, 200);
		// 窗口置于屏幕中央
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕的大小
		Dimension frameSize = this.getSize();// 这里的this可替换成 窗体的名字 ，下同
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);// 设置位置

		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("是否退出本系统？");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel.setEnabled(true);
		lblNewLabel.setBounds(95, 52, 120, 19);
		getContentPane().add(lblNewLabel);

		JButton button = new JButton("是");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // 如果用户选择 是 ，则关闭系统
			}
		});
		button.setBounds(29, 110, 117, 29);
		getContentPane().add(button);

		JButton button_1 = new JButton("否");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();// 如果用户选择否，则关闭弹窗提示
			}
		});
		button_1.setBounds(158, 110, 117, 29);
		getContentPane().add(button_1);

		this.setVisible(true);
	}
}
