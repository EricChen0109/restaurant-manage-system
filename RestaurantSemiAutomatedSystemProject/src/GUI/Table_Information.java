package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import json.*;

public class Table_Information extends JFrame {

	String[] appetizer;
	String[] mainMeal;
	String[] dessert;
	String[] drinks;
	JButton[] tablesSituationReference;
	JCheckBox cbHavaCustomer;
	JCheckBox cbNeedClean;
	int orderIndexNumber = -1;
	long customerStartTime = 0;
	long customerFinishTime = 0;
	int tableNumber;
	JButton btnAddMeal;
	JButton btnSubmitOrder;
	JTextArea txtSubmitedOrder;
	JButton btnFinishOrder;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Table_Information frame = new Table_Information(ABORT, null, null, null, null);
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
	public Table_Information(int tableNumber, JButton[] tablesSituationReference, JButton[] tableCleaningNeeds, FunctionMenu functionMenuReference, Chef_OrderList frameForChefReference) {
		super("第" + tableNumber + "桌 桌面資訊");
		this.tablesSituationReference = tablesSituationReference;
		this.tableNumber = tableNumber;
		this.appetizer = ReadmealJSONFile.readMeal(1).toArray(new String[0]);
		this.mainMeal = ReadmealJSONFile.readMeal(2).toArray(new String[0]);
		this.dessert = ReadmealJSONFile.readMeal(3).toArray(new String[0]);
		this.drinks = ReadmealJSONFile.readMeal(4).toArray(new String[0]);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		
		JTextArea txtMealsOrder = new JTextArea();
		txtMealsOrder.setBounds(175, 38, 175, 415);
		getContentPane().add(txtMealsOrder);
		
		JLabel lblOrder = new JLabel("訂單");
		lblOrder.setBounds(175, 10, 46, 15);
		getContentPane().add(lblOrder);
		
		JLabel lblAppetizer = new JLabel("前菜");
		lblAppetizer.setBounds(25, 56, 46, 15);
		getContentPane().add(lblAppetizer);
		
		JLabel lblMainMeal = new JLabel("主餐");
		lblMainMeal.setBounds(25, 133, 46, 15);
		getContentPane().add(lblMainMeal);
		
		JLabel lblDessert = new JLabel("甜點");
		lblDessert.setBounds(25, 210, 46, 15);
		getContentPane().add(lblDessert);
		
		JLabel lblDrinks = new JLabel("酒水");
		lblDrinks.setBounds(25, 287, 46, 15);
		getContentPane().add(lblDrinks);
		
		JComboBox<String> cbAppetizer = new JComboBox<>(appetizer);
		cbAppetizer.setBounds(21, 81, 95, 23);
		getContentPane().add(cbAppetizer);
		
		JComboBox<String> cbMainMeal = new JComboBox<>(mainMeal);
		cbMainMeal.setBounds(21, 158, 95, 23);
		getContentPane().add(cbMainMeal);
		
		JComboBox<String> cbDessert = new JComboBox<>(dessert);
		cbDessert.setBounds(21, 235, 95, 23);
		getContentPane().add(cbDessert);
		
		JComboBox<String> cbDrinks = new JComboBox<>(drinks);
		cbDrinks.setBounds(21, 312, 95, 23);
		getContentPane().add(cbDrinks);
		
		btnAddMeal = new JButton("增加餐點");
		btnAddMeal.setEnabled(false);
		btnAddMeal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!cbAppetizer.getItemAt(cbAppetizer.getSelectedIndex()).equals("null"))
					txtMealsOrder.append(cbAppetizer.getItemAt(cbAppetizer.getSelectedIndex()) + "\n");
				if (!cbMainMeal.getItemAt(cbMainMeal.getSelectedIndex()).equals("null"))
					txtMealsOrder.append(cbMainMeal.getItemAt(cbMainMeal.getSelectedIndex()) + "\n");
				if (!cbDessert.getItemAt(cbDessert.getSelectedIndex()).equals("null"))
					txtMealsOrder.append(cbDessert.getItemAt(cbDessert.getSelectedIndex()) + "\n");
				if (!cbDrinks.getItemAt(cbDrinks.getSelectedIndex()).equals("null"))
					txtMealsOrder.append(cbDrinks.getItemAt(cbDrinks.getSelectedIndex()) + "\n");
				if (!txtMealsOrder.getText().equals(""))
					txtMealsOrder.setBackground(new Color(255, 200, 200));
				cbAppetizer.setSelectedIndex(0);
				cbMainMeal.setSelectedIndex(0);
				cbDessert.setSelectedIndex(0);
				cbDrinks.setSelectedIndex(0);
			}
		});
		btnAddMeal.setBounds(21, 378, 85, 23);
		getContentPane().add(btnAddMeal);
		
		this.cbNeedClean = new JCheckBox("需清潔");
		this.cbNeedClean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbNeedClean.isSelected()) {
					tableCleaningNeeds[tableNumber-1].setBackground(new Color(255, 255, 0));
					functionMenuReference.getButtonReference(tableNumber).setBackground(new Color(255, 255, 0));
					tablesSituationReference[tableNumber - 1].setBackground(new Color(255, 255, 0));
					cbHavaCustomer.setEnabled(false);
				}
				else {
					tableCleaningNeeds[tableNumber-1].setBackground(new Color(255, 255, 255));
					functionMenuReference.getButtonReference(tableNumber).setBackground(new Color(0, 255, 0));
					tablesSituationReference[tableNumber - 1].setBackground(new Color(0, 255, 0));
					cbHavaCustomer.setEnabled(true);
				}
			}
		});
		this.cbNeedClean.setBounds(21, 31, 95, 23);
		getContentPane().add(this.cbNeedClean);
		
		txtSubmitedOrder = new JTextArea();
		txtSubmitedOrder.setBounds(376, 38, 175, 415);
		getContentPane().add(txtSubmitedOrder);
		
		btnSubmitOrder = new JButton("提交訂單");
		btnSubmitOrder.setEnabled(false);
		btnSubmitOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMealsOrder.setBackground(new Color(255, 255, 255));
				frameForChefReference.addOrder(tableNumber, txtMealsOrder);
				txtSubmitedOrder.setBackground(new Color(255, 255, 0));
				txtSubmitedOrder.append(txtMealsOrder.getText());
				txtSubmitedOrder.append("————————————————\n");
				txtMealsOrder.setText("");
				frameForChefReference.addOrderTime(System.currentTimeMillis());
				btnFinishOrder.setEnabled(false);
			}
		});
		btnSubmitOrder.setBounds(21, 415, 85, 23);
		getContentPane().add(btnSubmitOrder);
		
		JLabel lblSubmitedOrder = new JLabel("已提交訂單");
		lblSubmitedOrder.setBounds(376, 10, 95, 15);
		getContentPane().add(lblSubmitedOrder);
		
		this.cbHavaCustomer = new JCheckBox("有客人");
		this.cbHavaCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbHavaCustomer.isSelected()) {
					functionMenuReference.getButtonReference(tableNumber).setBackground(new Color(255, 0, 0));
					tablesSituationReference[tableNumber - 1].setBackground(new Color(255, 0, 0));
					btnAddMeal.setEnabled(true);
					btnSubmitOrder.setEnabled(true);
					cbNeedClean.setEnabled(false);
					customerStartTime = System.currentTimeMillis();
					cbHavaCustomer.setEnabled(false);
				}
			}
		});
		this.cbHavaCustomer.setBounds(21, 6, 95, 23);
		getContentPane().add(this.cbHavaCustomer);
		
		btnFinishOrder = new JButton("結單");
		btnFinishOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableCleaningNeeds[tableNumber-1].setBackground(new Color(255, 255, 0));
				functionMenuReference.getButtonReference(tableNumber).setBackground(new Color(255, 255, 0));
				tablesSituationReference[tableNumber - 1].setBackground(new Color(255, 255, 0));
				txtSubmitedOrder.setBackground(new Color(255, 255, 255));
				btnAddMeal.setEnabled(false);
				btnSubmitOrder.setEnabled(false);
				cbNeedClean.setEnabled(true);
				cbHavaCustomer.setEnabled(true);
				cbHavaCustomer.setSelected(false);
				cbNeedClean.setSelected(true);
				cbHavaCustomer.setEnabled(false);
				customerFinishTime = System.currentTimeMillis();
				countOrderMaintainTime();
				sentOrderSMealToDataBase();
				txtSubmitedOrder.setText("");
				btnFinishOrder.setEnabled(false);
			}
		});
		btnFinishOrder.setBounds(491, 6, 85, 23);
		getContentPane().add(btnFinishOrder);
		btnFinishOrder.setEnabled(false);
		
		
		
	}
	
	private void countOrderMaintainTime() {
		int customerMaintainTime = 0;
		customerMaintainTime = (int) ((customerFinishTime - customerStartTime) / 1000);
		if (customerMaintainTime == 0 || customerStartTime == 0) {
			return;
		}
		customerStartTime = 0;
		sentOrderMaintainTimeToDataBase(customerMaintainTime);
	}
	
	private void sentOrderMaintainTimeToDataBase(int orderMaintainTime) {
		UpdatetimeJSONFile.AddTime("staytime.json", orderMaintainTime);
	}
	
	private void sentOrderSMealToDataBase() {
		//訂單餐點傳到資料庫
		String[] orderMeal = txtSubmitedOrder.getText().split("\n");
		ArrayList<String> orderMealAL = new ArrayList<String>(Arrays.asList(orderMeal));
		int ordermealNumber = orderMealAL.size();
		for (int i = 0; i < ordermealNumber; i++) {
			if (orderMealAL.get(i).equals("————————————————")) {
				orderMealAL.remove(i);
				ordermealNumber--;
			}
		}
		UpdatemealsstatisticsJSONFile.Add(orderMealAL);
		System.out.println(orderMealAL);
	}

	public void changeCbHavaCustomer() {
			cbHavaCustomer.setSelected(true);
			cbNeedClean.setEnabled(false);
			btnAddMeal.setEnabled(true);
			btnSubmitOrder.setEnabled(true);
			cbNeedClean.setEnabled(false);
			customerStartTime = System.currentTimeMillis();
	}
	
	public void changeCbNeedClean() {
		cbNeedClean.setSelected(false);
		cbHavaCustomer.setEnabled(true);
	}

	public void setTableFinish() {
		txtSubmitedOrder.setBackground(new Color(0, 255, 0));
		btnFinishOrder.setEnabled(true);
	}
}