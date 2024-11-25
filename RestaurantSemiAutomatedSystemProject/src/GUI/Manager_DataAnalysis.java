package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SortOrder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import json.*;

public class Manager_DataAnalysis extends JFrame {
	
	private DefaultPieDataset<String> pieDataset;
	private JFreeChart pieChart;
	private PiePlot piePlot;
	private ChartPanel chartPanel;
	private JPanel pieChartPanel;
	ArrayList<String> mealNameAL;
	ArrayList<Long> mealAmountAL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_DataAnalysis frame = new Manager_DataAnalysis();
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
	public Manager_DataAnalysis() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		getContentPane().setLayout(null);

		pieChartPanel = new JPanel();
		pieChartPanel.setBounds(10, 35, 400, 300);
		getContentPane().add(pieChartPanel);
		pieChartPanel.setLayout(new BorderLayout(0, 0));
		showPieChart();
	}
	
	public void showPieChart() {
		pieDataset = new DefaultPieDataset<String>();
		this.mealNameAL = ReadmealJSONFile.readMealsStatisticsName();
		this.mealAmountAL = ReadmealJSONFile.readMealsStatisticsAmount();
		bubbleSort();


		for (int i = 0; i < mealNameAL.size(); i++) {
			pieDataset.setValue(mealNameAL.get(i), mealAmountAL.get(i));
		}

		pieChart = ChartFactory.createPieChart3D("菜單項目受歡迎程度", pieDataset, false, false, false);
		
		TextTitle textTitle = pieChart.getTitle();
		textTitle.setFont(new Font("宋體",Font.BOLD,15));
		
		LegendTitle legend = pieChart.getLegend();   
		if (legend!=null) {   
			legend.setItemFont(new Font("宋體", Font.PLAIN, 15));   
		}
		
		piePlot = (PiePlot) pieChart.getPlot();
		piePlot.setLabelFont(new Font("宋體",Font.PLAIN,12));
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
		chartPanel = new ChartPanel(pieChart);
		chartPanel.setForeground(new Color(255, 255, 255));
		chartPanel.setBackground(new Color(255, 128, 128));
		pieChartPanel.removeAll();
		pieChartPanel.add(chartPanel, BorderLayout.CENTER);
		chartPanel.validate();
		
		
	}
	
	
	public void bubbleSort() {
        if (mealAmountAL.size() == 0)
            return;
        for (int i = 0; i < mealAmountAL.size(); i++)
            for (int j = 0; j < mealAmountAL.size() - 1 - i; j++)
                if (mealAmountAL.get(j + 1) < mealAmountAL.get(j)) {
                    Long temp = mealAmountAL.get(j + 1);
                    mealAmountAL.set(j + 1, mealAmountAL.get(j));
                    mealAmountAL.set(j, temp);
                    
                    String temp2 = mealNameAL.get(j + 1);
                    mealNameAL.set(j + 1, mealNameAL.get(j));
                    mealNameAL.set(j, temp2);
                }
        return;
    }

}
