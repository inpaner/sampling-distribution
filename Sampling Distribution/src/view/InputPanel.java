package view;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.crypto.spec.PSource.PSpecified;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;

import org.apache.commons.math3.genetics.NPointCrossover;

import model.Binomial;
import net.miginfocom.swing.MigLayout;

public class InputPanel extends JPanel {
    
    private JLabel xLabel;
    private JSpinner xLowerSpinner;
    private JLabel toLabel;
    private JSpinner xUpperSpinner;
    private JCheckBox singleCheck;
    private JLabel nLabel;
    private JSpinner nSpinner;
    private JLabel pLabel;
    private JTextField pField;
    private JSlider pSlider;
    private JButton updateButton;
    private JLabel bLabel;
    private JLabel bValueLabel;
    
    public InputPanel() {
        setLayout(new MigLayout("", "[][30][30, center][30]"));
        initComponents();
        addComponents();
    }
    
    private void initComponents() {                                
        // x
        xLabel = new JLabel("x: ");
        xLowerSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Binomial.MAX_X, 1));
        JComponent field = ((JSpinner.DefaultEditor) xLowerSpinner.getEditor());
        Dimension prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        toLabel = new JLabel("to: ");
        
        xUpperSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Binomial.MAX_X, 1));
        field = ((JSpinner.DefaultEditor) xUpperSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        // checkbox
        singleCheck = new JCheckBox("Single X");
        singleCheck.addItemListener(single());
        
        // n
        nLabel = new JLabel("n: ");
        nSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Binomial.MAX_X, 1));
        field = ((JSpinner.DefaultEditor) nSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        // p
        pLabel = new JLabel("p: ");
        pField = new JTextField(6);
        pField.setHorizontalAlignment(JTextField.RIGHT);
        pSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
        
        // b
        updateButton = new JButton("Update Values");
        bLabel = new JLabel("b: ");
        bValueLabel = new JLabel("");
    }
    
    private void addComponents() {
        add(xLabel);
        add(xLowerSpinner);
        add(toLabel);
        add(xUpperSpinner, "wrap");
        
        add(singleCheck, "skip 3, span, split, wrap");
        
        add(nLabel);
        add(nSpinner, "wrap");
        
        add(pLabel);
        add(pField, "span, split");
        add(pSlider, "wrap 30");
        
        add(updateButton, "span, split, wrap");
        
        add(bLabel);
        add(bValueLabel, "span, split");
    }
    
    
    

    //--------------------------------
    // Getters and setters
    
    public int getXLower() {
        return (Integer) xLowerSpinner.getValue();
    }
    
    public int getXUpper() {
        return (Integer) xUpperSpinner.getValue();
    }
    
    public boolean isSingle() {
    	return singleCheck.isSelected();
    }
    
    public int getN() {
        return (Integer) nSpinner.getValue();
    }
    
    public double getP() {
        return Double.valueOf(pField.getText());
    }
    
    public double getPSlider() {
        return (Double) (pSlider.getValue() / 1000.0);
    }
    
    
    public void setBValue(double b) {
        bValueLabel.setText(Double.toString(b));
    }
    
    public void setXLower(int x) {
        xLowerSpinner.setValue(x);
    }
    
    public void setXUpper(int x) {
        xUpperSpinner.setValue(x);
    }
    
    public void setN(int x) {
        nSpinner.setValue(x);
    }
    
    public void setP(double p) {
        pField.setText(Double.toString(p));
    }
    
    public void setPSlider(double p) {
        int value = (int) (p * 1000);
        System.out.println(value);
        
        pSlider.setValue(value);
    }
    
    public void enableSingle() {
    	xUpperSpinner.setEnabled(false);
    	setXUpper(getXLower());
    }
    
    public void disableSingle() {
    	xUpperSpinner.setEnabled(true);
    }
    
    private ItemListener single() {
    	return new ItemListener() {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (singleCheck.isSelected()) 
				enableSingle();
			else
				disableSingle();
			
		}
		};
    }
    
    
    //--------------------------------
    // Listener setters
    
    public void addUpdateListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }
    
    public void addLowerSpinnerListener(ChangeListener listener) {
        xLowerSpinner.addChangeListener(listener);
    }
    
    public void addUpperSpinnerListener(ChangeListener listener) {
        xUpperSpinner.addChangeListener(listener);
    }
    
    public void addSingleListener(ItemListener listener) {
    	singleCheck.addItemListener(listener);
    }
    
    public void addNSpinnerListener(ChangeListener listener) {
        nSpinner.addChangeListener(listener);
    }
    
    public void addPFieldListener(ActionListener listener) {
        pField.addActionListener(listener);
    }
    
    public void addPSliderListener(ChangeListener listener) {
        pSlider.addChangeListener(listener);
    }

}
