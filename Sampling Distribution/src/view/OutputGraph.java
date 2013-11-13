package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import model.Binomial;

public class OutputGraph extends JPanel{
	private Binomial binomial;;
	
	DefaultCategoryDataset dataset2;
	JFreeChart barChart;
	
	public OutputGraph() {
		dataset2 = new DefaultCategoryDataset();
		barChart = ChartFactory.createBarChart("Binomial Distribution", "x", "Probability distribution", dataset2, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel panel =new ChartPanel(barChart);
		add(panel, BorderLayout.CENTER);   
		
	 }
	
	
	public void updateData(Binomial newBinomial) {
		Binomial old = binomial;
		binomial = newBinomial;
		
		if (old != null) {
			for (int i = 0; i < old.probabilities().length; i++) {
	        	dataset2.removeValue("Probability distribution", String.valueOf(old.getStart()+i));
	        }
		}
		
		
		for (int i = 0; i <  newBinomial.probabilities().length; i++) {
        	double rounded = (double) Math.round(newBinomial.probabilities()[i] * 10000) / 10000;
            dataset2.setValue(rounded, "Probability distribution", String.valueOf(newBinomial.getStart()+i));
        }
    }
}
