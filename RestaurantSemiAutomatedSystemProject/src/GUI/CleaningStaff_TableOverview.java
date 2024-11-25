package GUI;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

import json.UpdatetableJSONFile;

public class CleaningStaff_TableOverview extends JFrame {
	Container contentPane;
	JButton[] tableCleaningNeeds;
	int tableAmount;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CleaningStaff_TableOverview frame = new CleaningStaff_TableOverview(new JButton[5], 5);
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
	public CleaningStaff_TableOverview(JButton[] tableCleaningNeeds, int tableAmount) {
		super("清潔人員需求圖");
		this.tableCleaningNeeds = tableCleaningNeeds;
		this.tableAmount = tableAmount;
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		int x = 24;
		int y = 20;
		contentPane = getContentPane();
		
		for(int i = 1; i <= tableAmount; i++) {
//			tablesButtonList[i-1] = new DiningTable(i);
			tableCleaningNeeds[i-1].setBounds(x, y, 85, 85);
			getContentPane().add(tableCleaningNeeds[i-1]);
			y += 112;
			if (y > 643) {
				y = 20;
				x += 119;
			}
		}
	}
	
	public void refreshTable() {
		contentPane.removeAll();
		tableAmount = UpdatetableJSONFile.ReadTableNumber(0);
		int x = 24;
		int y = 20;
		for(int i = 1; i <= tableAmount; i++) {
//			tablesButtonList[i-1] = new DiningTable(i);
			tableCleaningNeeds[i-1].setBounds(x, y, 85, 85);
			contentPane.add(tableCleaningNeeds[i-1]);
			y += 112;
			if (y > 643) {
				y = 20;
				x += 119;
			}
		}
	}

}
