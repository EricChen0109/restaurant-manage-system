package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import json.UpdatetimeJSONFile;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Chef_OrderList extends JFrame {

	private JTextArea[] chef_OrderListReference;
	private int OrderAmount = 0;
	private String[] orderNumber = {"第一順位(defalt)", "第二順位", "第三順位", "第四順位", "第五順位", "第六順位", "第七順位", "第八順位"};
	long orderStartTime = 0;
	long orderFinishTime = 0;
	int[] tableOrderAmount = new int[60];
	ArrayList<Integer> orderTableNumber = new ArrayList<Integer>(60);
	FunctionMenu functionMenuReference;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chef_OrderList frame = new Chef_OrderList(new JTextArea[8], null);
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
	public Chef_OrderList(JTextArea[] chef_OrderListReference, FunctionMenu functionMenuReference) {
		super("廚師待處理訂單");
		this.chef_OrderListReference = chef_OrderListReference;
		this.functionMenuReference = functionMenuReference;
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		
		JComboBox<String> comboBox = new JComboBox<>(orderNumber);
		comboBox.setBounds(1074, 113, 136, 23);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("完成訂單");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishSelectedOrder(comboBox.getItemAt(comboBox.getSelectedIndex()));
				comboBox.setSelectedIndex(0);
				orderFinishTime = System.currentTimeMillis();
				sentOrderFinishTime();
			}
		});
		btnNewButton.setBounds(1093, 206, 85, 23);
		getContentPane().add(btnNewButton);
		
		
		

		int x = 46;
		int y = 23;
		for (int i = 0; i < chef_OrderListReference.length; i++) {
			chef_OrderListReference[i] = new JTextArea();
			chef_OrderListReference[i].setBounds(x, y, 216, 306);
			x += 240;
			if (x > 766) {
				x = 46;
				y += 333;
			}
			getContentPane().add(chef_OrderListReference[i]);
		}
	}

	public void addOrder(int tableNumber, JTextArea inputOrder) {
		String inputOrderTextString = inputOrder.getText();
		chef_OrderListReference[OrderAmount].append("第" + tableNumber + "桌訂單\n");
		chef_OrderListReference[OrderAmount].append(inputOrderTextString);
		if (OrderAmount < 59)
			OrderAmount += 1;
		tableOrderAmount[tableNumber]++;
		orderTableNumber.add(tableNumber);
	}
	
	public void finishSelectedOrder_2(int selectedOrderIndex) {
		int orderIndex = selectedOrderIndex;
		while(!chef_OrderListReference[orderIndex].equals("") && orderIndex < 59) {
			chef_OrderListReference[orderIndex].setText(chef_OrderListReference[orderIndex + 1].getText());
			orderIndex += 1;
		}
		OrderAmount -= 1;
		int removeNumber = orderTableNumber.remove(selectedOrderIndex);
		tableOrderAmount[removeNumber]--;
		if (tableOrderAmount[removeNumber] == 0)
			functionMenuReference.setTableFinish(removeNumber);
	}
	
	public void finishSelectedOrder(String selectOrder) {
		int selectedOrderIndex = 0;
		switch (selectOrder) {
		case "第一順位(defalt)": {
			selectedOrderIndex = 0;
			break;
		}
		case "第二順位": {
			selectedOrderIndex = 1;
			break;
		}
		case "第三順位": {
			selectedOrderIndex = 2;
			break;
		}
		case "第四順位": {
			selectedOrderIndex = 3;
			break;
		}
		case "第五順位": {
			selectedOrderIndex = 4;
			break;
		}
		case "第六順位": {
			selectedOrderIndex = 5;
			break;
		}
		case "第七順位": {
			selectedOrderIndex = 6;
			break;
		}
		case "第八順位": {
			selectedOrderIndex = 7;
			break;
		}
		}
		finishSelectedOrder_2(selectedOrderIndex);
	}
	
	public void addOrderTime(long orderStartTime) {
		this.orderStartTime = orderStartTime;
	}
	
	public void sentOrderFinishTime() {
		int orderTime = 0;
		orderTime = (int) ((orderFinishTime - orderStartTime) / 1000);
		if (orderTime == 0 || orderStartTime == 0) {
			return;
		}
		orderStartTime = 0;
		UpdatetimeJSONFile.AddTime("usetime.json", orderTime);
	}

}
