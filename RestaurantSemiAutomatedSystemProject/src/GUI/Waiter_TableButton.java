package GUI;

import java.awt.EventQueue;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Waiter_TableButton extends JButton {
	private int tableNumber;
	private int time;
	Table_Information table_Information;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Waiter_TableButton frame = new Waiter_TableButton(ABORT, null, null, null, null);
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
	public Waiter_TableButton(int tableNumber, JButton[] tablesSituationReference, JButton[] tableCleaningNeedstableCleaningNeeds, FunctionMenu functionMenuReference, Chef_OrderList frameForChefReference) {
		this.tableNumber = tableNumber;
		table_Information = new Table_Information(this.tableNumber, tablesSituationReference, tableCleaningNeedstableCleaningNeeds, functionMenuReference, frameForChefReference);
		tablesSituationReference[tableNumber-1] = new Receptionist_TableButton(this.tableNumber, table_Information, functionMenuReference);
		tableCleaningNeedstableCleaningNeeds[tableNumber-1] = new CleaningStaff_TableButton(this.tableNumber, table_Information, functionMenuReference, tablesSituationReference);
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(0, 255, 0));
		setBounds(getVisibleRect());
		setText(tableNumber + "桌");
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						table_Information.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

			}
		});
	}
	
	public void setTableFinish(int tableNumber) {
		table_Information.setTableFinish();
	}
}
