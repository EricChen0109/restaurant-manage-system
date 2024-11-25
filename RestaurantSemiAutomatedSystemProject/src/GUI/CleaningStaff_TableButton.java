package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;

public class CleaningStaff_TableButton extends JButton {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CleaningStaff_TableButton frame = new CleaningStaff_TableButton(ABORT, null, null, null);
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
	public CleaningStaff_TableButton(int tableNumber,Table_Information table_InformationReference, FunctionMenu functionMenuReference, JButton[] tablesSituationReference) {
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(255, 255, 255));
		setBounds(getVisibleRect());
		setText(tableNumber + "桌");
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getBackground() != new Color(255, 255, 255)) {
					setBackground(new Color(255, 255, 255));
					table_InformationReference.changeCbNeedClean();
					functionMenuReference.getButtonReference(tableNumber).setBackground(new Color(0, 255, 0));
					tablesSituationReference[tableNumber - 1].setBackground(new Color(0, 255, 0));
				} 
			}
		});
	}
	
}
