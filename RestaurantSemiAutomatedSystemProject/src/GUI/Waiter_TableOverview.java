package GUI;

import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.JFrame;
import json.UpdatetableJSONFile;
import javax.swing.JButton;

public class Waiter_TableOverview extends JFrame {
	Container contentPane;
	JButton[] tablesButtonList;
	int tableAmount;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Waiter_TableOverview frame = new Waiter_TableOverview(new JButton[500], 60);
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
	public Waiter_TableOverview(JButton[] tablesButtonList, int tableAmount) {
		super("服務生餐桌服務單元");
		this.tableAmount = tableAmount;
		this.tablesButtonList = tablesButtonList;
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		int x = 24;
		int y = 20;
		contentPane = getContentPane();
		
		for(int i = 1; i <= tableAmount; i++) {
			tablesButtonList[i-1].setBounds(x, y, 85, 85);
			getContentPane().add(tablesButtonList[i-1]);
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
			tablesButtonList[i-1].setBounds(x, y, 85, 85);
			contentPane.add(tablesButtonList[i-1]);
			y += 112;
			if (y > 643) {
				y = 20;
				x += 119;
			}
		}
	}
}
