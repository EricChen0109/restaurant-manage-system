package TestJFreeChart;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class PieChart extends JFrame {

	private DefaultPieDataset<String> pieDataset;
	private JFreeChart pieChart;
	private PiePlot piePlot;
	private ChartPanel chartPanel;
	JPanel pieChartPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PieChart frame = new PieChart();
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
	public PieChart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 519);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel(" Pie Chart");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 18));
		lblNewLabel_1.setBounds(262, 37, 140, 44);
		getContentPane().add(lblNewLabel_1);
		
		pieChartPanel = new JPanel();
		pieChartPanel.setBounds(10, 99, 400, 300);
		getContentPane().add(pieChartPanel);
		pieChartPanel.setLayout(new BorderLayout(0, 0));
		showPieChart();
	}
	
	public void showPieChart() {
		pieDataset = new DefaultPieDataset<String>();
		pieDataset.setValue("A", 9.2);
		pieDataset.setValue("B", 9.2);
		pieDataset.setValue("C", 9.0);
		pieDataset.setValue("D", 9.0);
		pieDataset.setValue("E", 1);

		
		pieChart = ChartFactory.createPieChart3D("ABCDE", pieDataset, true, true, false);
		
		piePlot = (PiePlot) pieChart.getPlot();
		chartPanel = new ChartPanel(pieChart);
		chartPanel.setForeground(new Color(255, 255, 255));
		chartPanel.setBackground(new Color(255, 128, 128));
		pieChartPanel.removeAll();
		pieChartPanel.add(chartPanel, BorderLayout.CENTER);
		chartPanel.validate();
		
		
	}
}
