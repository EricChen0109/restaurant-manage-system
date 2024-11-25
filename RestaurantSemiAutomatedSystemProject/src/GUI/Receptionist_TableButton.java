package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Receptionist_TableButton extends JButton {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receptionist_TableButton frame = new Receptionist_TableButton(ABORT, null, null);
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
	public Receptionist_TableButton(int tableNumber, Table_Information table_Information, FunctionMenu functionMenuReference) {
		setForeground(new Color(0, 0, 0));
		setBackground(new Color(0, 255, 0));
		setBounds(getVisibleRect());
		setText(tableNumber + "桌");
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					setBackground(new Color(255, 0, 0));
					functionMenuReference.getButtonReference(tableNumber).setBackground(new Color(255, 0, 0));
					table_Information.changeCbHavaCustomer();
			}
		});
	}

}
