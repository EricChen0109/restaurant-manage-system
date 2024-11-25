package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Manager_MainPage extends JFrame {

	Manager_ManageStaffs staffManage;
	Manager_ManageTable tableManage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_MainPage frame = new Manager_MainPage(null, null, null, null);
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
	public Manager_MainPage(Staff[] staffListReference, Receptionist_TableOverview frameForReceptionistReference, Waiter_TableOverview frameForWaitersReference, CleaningStaff_TableOverview frameForCSReference) {
		super("經理管理頁面");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		this.tableManage = new Manager_ManageTable(staffListReference, frameForReceptionistReference, frameForWaitersReference, frameForCSReference);
		this.staffManage = new Manager_ManageStaffs(staffListReference, this.tableManage);
		
		JButton btnNewButton = new JButton("員工管理");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							staffManage.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		btnNewButton.setBounds(30, 20, 200, 200);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("餐廳桌位管理");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							tableManage.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		btnNewButton_1.setBounds(280, 20, 200, 200);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("餐點管理");
		btnNewButton_1_1.setBounds(530, 20, 200, 200);
		getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("經營分析");
		btnNewButton_1_1_1.setBounds(780, 20, 200, 200);
		getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("New button");
		btnNewButton_1_1_1_1.setBounds(1030, 20, 200, 200);
		getContentPane().add(btnNewButton_1_1_1_1);

		
		
	}
}
