package view;

import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import model.Sampler;

/* 
 * Modified from:
 * https://github.com/dwdyer/uncommons-maths/blob/master/demo
 *      /src/java/main/org/uncommons/maths/demo/GraphPanel.java
 */
@SuppressWarnings("serial")
public class OutputGraph extends JPanel{
	
    private XYSeriesCollection dataset;
	
	public OutputGraph() {
		dataset = new XYSeriesCollection();
        
        JFreeChart chart = ChartFactory.createXYLineChart("Distribution",
                "Mean",
                "Count",
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
    }
}
