package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import json.*;

public class LogInView extends JFrame {

	private JTextField txtAccount;
	private JTextField txtPassword;
	private int tableNumber;

	/**
	 * Launch the application.ˋ
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInView frame = new LogInView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInView() {
		super("登入頁面");
		tableNumber = UpdatetableJSONFile.ReadTableNumber(0);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("帳號：");
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(136, 67, 46, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密碼：");
		lblNewLabel_1.setBounds(136, 116, 46, 15);
		getContentPane().add(lblNewLabel_1);
		
		txtAccount = new JTextField();
		txtAccount.setText("");
		txtAccount.setBounds(208, 64, 96, 21);
		getContentPane().add(txtAccount);
		txtAccount.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setText("");
		txtPassword.setBounds(208, 113, 96, 21);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		JButton btnNewButton = new JButton("登入");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginObj = new Login(txtAccount.getText(), txtPassword.getText());
				int jobPosition = loginObj.getIsExisted().intValue();
				if (jobPosition != 0) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								FunctionMenu functionMenu = new FunctionMenu(txtAccount.getText(), tableNumber, jobPosition);
								functionMenu.setVisible(true);
								setVisible(false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}else {
					System.out.println("帳號或密碼錯誤");
				}
			}
		});
		btnNewButton.setBounds(175, 200, 86, 23);
		getContentPane().add(btnNewButton);
	}
}
