package view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import model.Sampler;

public class OutputValues extends JPanel {
    private JLabel mean;
    private JLabel variance;

    public OutputValues() {
        JLabel meanLabel = new JLabel("Mean: ");
        mean = new JLabel("0.0");
        
        JLabel varianceLabel = new JLabel("Variance: ");
        variance = new JLabel("0.0");
        
        setLayout(new MigLayout("wrap 2"));
        add(meanLabel);
        add(mean);
        add(varianceLabel);
        add(variance);
    }
    
    public void updateData(Sampler sampler) {
        mean.setText(String.valueOf(sampler.getMeanOfMeans()));
        variance.setText(String.valueOf(sampler.getVarianceOfMeans()));
    }
}
