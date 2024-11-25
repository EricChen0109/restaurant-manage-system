package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import json.UpdatestaffJSONFile;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import json.*;

public class FunctionMenu extends JFrame {

	Waiter_TableButton[] waiter_TableButtonList;
	JButton[] receptionist_TableButtonList;
	JButton[] cleaningStaff_TableButtonList;
	JTextArea[] chef_OrderList;
	private int tableAmount;
	private Chef_OrderList frameForChef;
	Staff[] staffList;
	Waiter_TableOverview frameForWaiters;
	Receptionist_TableOverview frameForReceptionist;
	CleaningStaff_TableOverview frameForCS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FunctionMenu frame = new FunctionMenu("", 60, 0);
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
	public FunctionMenu(String account, int tableNumber, int jobPosition) {
		super(UpdatestaffJSONFile.getNameByAccount(account) + "的工作頁面");
		this.tableAmount = tableNumber;
		this.waiter_TableButtonList = new Waiter_TableButton[60];
		this.receptionist_TableButtonList = new JButton[60];
		this.cleaningStaff_TableButtonList = new JButton[60];
		this.chef_OrderList = new JTextArea[60];
		this.frameForChef = new Chef_OrderList(chef_OrderList, this);
		this.staffList = new Staff[60];
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		
		for(int i = 1; i <= 60; i++) {
			waiter_TableButtonList[i-1] = new Waiter_TableButton(i, receptionist_TableButtonList
					, cleaningStaff_TableButtonList, this, frameForChef);
		}
		
		frameForReceptionist =
				new Receptionist_TableOverview(receptionist_TableButtonList, tableAmount);
		frameForWaiters = 
				new Waiter_TableOverview(waiter_TableButtonList, tableNumber);
		frameForCS = 
				new CleaningStaff_TableOverview(cleaningStaff_TableButtonList, tableAmount);
		
		
		JButton btnNewButton_1_1 = new JButton("領櫃人員");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameForReceptionist.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		
		JButton btnNewButton_1_2 = new JButton("服務生");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameForWaiters.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		
		JButton btnNewButton_1_3 = new JButton("雜工");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameForCS.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		
		JButton btnNewButton_2_1 = new JButton("廚師");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameForChef.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		
		JButton btnNewButton_2_2 = new JButton("經理");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Manager_MainPage frameForMana = new Manager_MainPage(staffList, frameForReceptionist, frameForWaiters, frameForCS);
							frameForMana.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		
		switch (jobPosition) {
		case 0: {
			btnNewButton_1_1.setBounds(20, 20, 388, 300);
			getContentPane().add(btnNewButton_1_1);
			break;
		}
		case 1: {
			btnNewButton_1_2.setBounds(20, 20, 388, 300);
			getContentPane().add(btnNewButton_1_2);
			break;
		}
		case 2: {
			btnNewButton_1_3.setBounds(20, 20, 388, 300);
			getContentPane().add(btnNewButton_1_3);
			break;
		}
		case 3: {
			btnNewButton_2_1.setBounds(20, 20, 388, 300);
			getContentPane().add(btnNewButton_2_1);
			break;
		}		
		case 4: {
			btnNewButton_1_1.setBounds(20, 20, 388, 300);
			btnNewButton_1_2.setBounds(439, 20, 388, 300);
			btnNewButton_1_3.setBounds(858, 20, 388, 300);
			btnNewButton_2_1.setBounds(20, 363, 388, 300);
			btnNewButton_2_2.setBounds(439, 363, 388, 300);
			getContentPane().add(btnNewButton_1_1);
			getContentPane().add(btnNewButton_1_2);
			getContentPane().add(btnNewButton_1_3);
			getContentPane().add(btnNewButton_2_1);
			getContentPane().add(btnNewButton_2_2);
			break;
		}
		}
		

	}
	
	public JButton getButtonReference(int number) {
		return waiter_TableButtonList[number - 1];
	}
	
	public void setTableFinish(int tableNumber) {
		waiter_TableButtonList[tableNumber - 1].setTableFinish(tableNumber);
	}

}
