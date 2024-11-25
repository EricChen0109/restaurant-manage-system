package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Component;
import json.*;

public class Manager_ManageStaffs extends JFrame {

	private JTextField txtName;
	private JComboBox<String> cbJobPosition;
	private JTextField txtPay;
	private JTextField txtEntryTime;
	private JTextField txtAcount;
	private JTextField txtPasswd;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	JScrollPane scrollPane;
	String[] jobPosition = {"null", "領櫃人員", "服務生", "清潔員", "廚師", "經理"};
	Staff[] staffListReference;
	JTextArea textArea;
	int staffNumber = 0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_ManageStaffs frame = new Manager_ManageStaffs(null, null);
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
	public Manager_ManageStaffs(Staff[] staffListReference, Manager_ManageTable tableManageReference) {
		super("員工管理");
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(120, 120, 640, 480);
		this.setResizable(false);
		this.staffListReference = staffListReference;
		
		txtName = new JTextField();
		txtName.setBounds(500, 30, 96, 21);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		cbJobPosition = new JComboBox<String>(jobPosition);
		cbJobPosition.setBounds(500, 90, 96, 21);
		getContentPane().add(cbJobPosition);
		
		txtPay = new JTextField();
		txtPay.setBounds(500, 150, 96, 21);
		getContentPane().add(txtPay);
		txtPay.setColumns(10);
		
		txtEntryTime = new JTextField();
		txtEntryTime.setBounds(500, 210, 96, 21);
		getContentPane().add(txtEntryTime);
		txtEntryTime.setColumns(10);
		
		txtAcount = new JTextField();
		txtAcount.setBounds(500, 270, 96, 21);
		getContentPane().add(txtAcount);
		txtAcount.setColumns(10);
		
		txtPasswd = new JTextField();
		txtPasswd.setBounds(500, 330, 96, 21);
		getContentPane().add(txtPasswd);
		txtPasswd.setColumns(10);
		
		JLabel nameJLabel = new JLabel("姓名(ooo)");
		nameJLabel.setBounds(500, 10, 96, 15);
		getContentPane().add(nameJLabel);
		
		JLabel lblNewLabel_1 = new JLabel("職位(XXX)");
		lblNewLabel_1.setBounds(500, 70, 96, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("時薪(***/per hour)");
		lblNewLabel_2.setBounds(500, 130, 116, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("入職時間(DD/MM/YY)");
		lblNewLabel_3.setBounds(500, 190, 116, 15);
		getContentPane().add(lblNewLabel_3);
		
        JLabel lblNewLabel = new JLabel("帳號");
        lblNewLabel.setBounds(500, 250, 46, 15);
        getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_5 = new JLabel("密碼");
        lblNewLabel_5.setBounds(500, 310, 46, 15);
        getContentPane().add(lblNewLabel_5);
        
        listModel = new DefaultListModel<String>();
        list = new JList<String>(listModel);
        
        JScrollPane scrollPane = new JScrollPane(list);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(list);
		scrollPane.setBounds(61, 29, 215, 379);
		getContentPane().add(scrollPane);
		
		JLabel lblNewLabel_4 = new JLabel("員工表");
		scrollPane.setColumnHeaderView(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("新增員工");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtName.getText().trim().equals("") &&
					!(cbJobPosition.getSelectedIndex() == 0) &&
					!txtEntryTime.getText().trim().equals("") &&
					!txtPay.getText().trim().equals("") &&
					!txtAcount.getText().trim().equals("") &&
					!txtPasswd.getText().trim().equals("")) {
					UpdatestaffJSONFile.Add(txtName.getText(), cbJobPosition.getSelectedIndex() - 1, Integer.valueOf(txtPay.getText()), txtEntryTime.getText(), txtAcount.getText(), txtPasswd.getText());
					refreshListModel();
					txtName.setText("");
			        cbJobPosition.setSelectedIndex(0);;
			        txtEntryTime.setText("");
			        txtPay.setText("");
			        txtAcount.setText("");
			        txtPasswd.setText("");
			        tableManageReference.refreshStaffList();
				}
			}
		});
		btnNewButton.setBounds(500, 385, 85, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取出員工詳細資訊");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchAndPrintStaffBySelectedNames();
			}
		});
		btnNewButton_1.setBounds(307, 29, 132, 23);
		getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(307, 124, 132, 284);
		getContentPane().add(scrollPane_1);
		
		JLabel lblNewLabel_6 = new JLabel("選取員工詳細資訊");
		scrollPane_1.setColumnHeaderView(lblNewLabel_6);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		JButton btnNewButton_2 = new JButton("刪除選取員工");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteStaff();
				refreshListModel();
				tableManageReference.refreshStaffList();
			}
		});
		btnNewButton_2.setBounds(307, 66, 132, 23);
		getContentPane().add(btnNewButton_2);
		
		ArrayList<String> staffAL = UpdatestaffJSONFile.readname();
		for (String staffName: staffAL) {
			listModel.addElement(staffName);
		}

	}
	
	public void refreshListModel() {
		ArrayList<String> staffAL = UpdatestaffJSONFile.readname();
		listModel.clear();
		for (String staffName: staffAL) {
			listModel.addElement(staffName);
		}
    }
	
	public void deleteStaff() {
		List<String> nameList = list.getSelectedValuesList();
		for (String name: nameList) {
			UpdatestaffJSONFile.Remove(name);
		}
	}
	
	public void searchAndPrintStaffBySelectedNames() {
		textArea.setText("");
		List<String> nameList = list.getSelectedValuesList();
		for (String name : nameList) {
			ArrayList<String> staffInforArrayList = UpdatestaffJSONFile.readbyname(name);
			String jobPosition = jobNumSwiToJobPos(staffInforArrayList.get(1));
			textArea.append("姓名: " + staffInforArrayList.get(0)
						  + "\n職位: " + jobPosition
						  + "\n入職時間: " + staffInforArrayList.get(3)
						  + "\n薪資: " + staffInforArrayList.get(2) + "元/時"
						  + "\n帳號: " + staffInforArrayList.get(4)
						  + "\n密碼: " + staffInforArrayList.get(5) + "\n\n");
        }
	}
	
	public String jobNumSwiToJobPos(String jobNumber) {
		String jobPosition = "";
		switch (jobNumber) {
		case "0": {
			jobPosition = "領櫃人員";
			break;
		}
		case "1": {
			jobPosition = "服務員";
			break;
		}
		case "2": {
			jobPosition = "清潔員";
			break;
		}
		case "3": {
			jobPosition = "廚師";
			break;
		}
		case "4": {
			jobPosition = "經理";
			break;
		}
		}
		return jobPosition;
	}
}
