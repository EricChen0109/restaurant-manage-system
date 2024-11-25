package GUI;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;

import json.UpdatetableJSONFile;

public class Receptionist_TableOverview extends JFrame {
	Container contentPane;
	JButton[] tablesSituation;
	int tableAmount;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receptionist_TableOverview frame = new Receptionist_TableOverview(new JButton[20], 20);
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
	public Receptionist_TableOverview(JButton[] tablesSituation, int tableAmount) {
		super("領檯人員餐桌總況圖");
		this.tablesSituation = tablesSituation;
		this.tableAmount = tableAmount;
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		int x = 24;
		int y = 20;
		contentPane = getContentPane();
		
		for(int i = 1; i <= tableAmount; i++) {
//			tablesButtonList[i-1] = new DiningTable(i);
			tablesSituation[i-1].setBounds(x, y, 85, 85);
			getContentPane().add(tablesSituation[i-1]);
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
			tablesSituation[i-1].setBounds(x, y, 85, 85);
			contentPane.add(tablesSituation[i-1]);
			y += 112;
			if (y > 643) {
				y = 20;
				x += 119;
			}
		}
	}
}
