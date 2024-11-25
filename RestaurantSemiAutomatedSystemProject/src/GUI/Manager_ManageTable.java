package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import json.*;

public class Manager_ManageTable extends JFrame {

	Staff[] staffListReference;
	int tableAmount;
	DefaultListModel<String> listModel;
	DefaultListModel<String> listModel2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_ManageTable frame = new Manager_ManageTable(null, null, null, null);
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
	public Manager_ManageTable(Staff[] staffListReference, Receptionist_TableOverview frameForReceptionistReference, Waiter_TableOverview frameForWaitersReference, CleaningStaff_TableOverview frameForCSReference) {
		super("員工管理");
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBounds(120, 120, 640, 480);
		this.setResizable(false);
		this.staffListReference = staffListReference;
		
		///桌子SP
		this.listModel = new DefaultListModel<String>();
		JList<String> list = new JList<String>(listModel);

		JScrollPane scrollPane = new JScrollPane(list);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(list);
		scrollPane.setBounds(24, 45, 225, 365);
		getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("桌子");
		scrollPane.setColumnHeaderView(lblNewLabel);
		///
		
		///員工SP
		listModel2 = new DefaultListModel<String>();
		JList<String> list2 = new JList<String>(listModel2);

		JScrollPane scrollPane_1 = new JScrollPane(list2);
		list2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportView(list2);
		scrollPane_1.setBounds(374, 45, 225, 365);
		getContentPane().add(scrollPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("員工");
		scrollPane_1.setColumnHeaderView(lblNewLabel_1);
		///
		
		JButton btnNewButton = new JButton("增加桌子");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatetableJSONFile.EditTableAmount(1);
				frameForReceptionistReference.refreshTable();
				frameForWaitersReference.refreshTable();
				frameForCSReference.refreshTable();
				refreshTableList();
			}
		});
		btnNewButton.setBounds(269, 45, 85, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("刪除桌子");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatetableJSONFile.EditTableAmount(-1);
				frameForReceptionistReference.refreshTable();
				frameForWaitersReference.refreshTable();
				frameForCSReference.refreshTable();
				refreshTableList();
			}
		});
		btnNewButton_1.setBounds(269, 95, 85, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("配對負責人");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] tableIdxL = list.getSelectedIndices();
				String staffName = list2.getSelectedValue();
				for (int tableIdx: tableIdxL) {
					UpdatetableJSONFile.EditWaiter(tableIdx + 1, staffName);
				}
			}
		});
		btnNewButton_2.setBounds(257, 208, 110, 23);
		getContentPane().add(btnNewButton_2);
		
		this.tableAmount = UpdatetableJSONFile.ReadTableNumber(0);
		for (int i = 0; i < tableAmount; i++) {
			listModel.addElement("第" + (i+1) + "桌");
		}
		
		ArrayList<String> staffNameList = UpdatestaffJSONFile.readname();
		for (String staffName: staffNameList) {
			listModel2.addElement(staffName);
		}
	}
	
	public void refreshTableList() {
		this.tableAmount = UpdatetableJSONFile.ReadTableNumber(0);
		listModel.clear();
		for (int i = 0; i < tableAmount; i++) {
			listModel.addElement("第" + (i+1) + "桌");
		}
	}
	
	public void refreshStaffList() {
		ArrayList<String> staffNameList = UpdatestaffJSONFile.readname();
		listModel2.clear();
		System.out.println("Yes");
		for (String staffName: staffNameList) {
			listModel2.addElement(staffName);
		}
	}
}
