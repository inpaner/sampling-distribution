package view;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import model.Binomial;
import model.Sampler;

/* 
 * Modified from:
 * https://github.com/dwdyer/uncommons-maths/blob/master/demo
 *      /src/java/main/org/uncommons/maths/demo/GraphPanel.java
 */
public class OutputGraph extends JPanel{
	

    private XYSeriesCollection dataset;
	
	public OutputGraph() {
		dataset = new XYSeriesCollection();
        
        JFreeChart chart = ChartFactory.createXYLineChart("Binomial Distribution",
                "Value",
                "Probability",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false);

		ChartPanel panel = new ChartPanel(chart);
		add(panel, BorderLayout.CENTER);   	
	 }
	
	public void updateData(Sampler sampler) {
	    dataset.removeAllSeries();
		XYSeries series = new XYSeries("Observed");
        for (Map.Entry<Double, Integer> entry: sampler.getMeansCount().entrySet()) {
            series.add(entry.getKey(), entry.getValue());
        }
        dataset.addSeries(series);
		
        /*
		if (old != null) {
			for (int i = 0; i < old.probabilities().length; i++) {
	        	dataset.removeValue("Probability distribution", String.valueOf(old.getStart()+i));
	        }
		}
		
		
		for (int i = 0; i <  newBinomial.probabilities().length; i++) {
        	double rounded = (double) Math.round(newBinomial.probabilities()[i] * 10000) / 10000;
            dataset.setValue(rounded, "Probability distribution", String.valueOf(newBinomial.getStart()+i));
        }
        */
    }
}
