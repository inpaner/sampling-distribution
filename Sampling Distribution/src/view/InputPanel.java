package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource.PSpecified;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.math3.genetics.NPointCrossover;

import model.Binomial;
import net.miginfocom.swing.MigLayout;

public class InputPanel extends JPanel {
    
    private JSpinner lowerSpinner;
    private JSpinner upperSpinner;
    private JSpinner nSpinner;
    private JButton updateButton;
    
    private int lowerLimit;
    private int upperLimit;
    private int populationN;
    private int sampleN;
    
    private List<Listener> listeners = new ArrayList<>();
    
    public InputPanel() {
        /*
         * Initialize Components
         */
        
        // Generators
        JRadioButton uniformRadio = new JRadioButton("Uniform");
        JRadioButton normalRadio = new JRadioButton("Normal");
        JRadioButton skewedRadio = new JRadioButton("Skewed");
        JRadioButton bimodalRadio = new JRadioButton("Bimodal");
        JRadioButton randomRadio = new JRadioButton("Random");
        ButtonGroup generatorGroup = new ButtonGroup();
        generatorGroup.add(uniformRadio);
        generatorGroup.add(normalRadio);
        generatorGroup.add(skewedRadio);
        generatorGroup.add(bimodalRadio);
        generatorGroup.add(randomRadio);
        
        Box radioBox = new Box(BoxLayout.X_AXIS);
        radioBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        // x
        JLabel xLabel = new JLabel("x: ");
        lowerSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Binomial.MAX_X, 1));
        JComponent field = ((JSpinner.DefaultEditor) lowerSpinner.getEditor());
        Dimension prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        JLabel toLabel = new JLabel("to: ");
        
        upperSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Binomial.MAX_X, 1));
        field = ((JSpinner.DefaultEditor) upperSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);

        // n
        JLabel nLabel = new JLabel("n: ");
        nSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Binomial.MAX_X, 1));
        field = ((JSpinner.DefaultEditor) nSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        /*
         * Add internal listeners
         */
        lowerSpinner.addChangeListener(new LowerSpinner());
        upperSpinner.addChangeListener(new UpperSpinner());
        nSpinner.addChangeListener(new UpperSpinner());
        
        /*
         * Add components
         */
        setLayout(new MigLayout("", "[][30][30, center][30]"));
        
        add(xLabel);
        add(lowerSpinner);
        add(toLabel);
        add(upperSpinner, "wrap");
        
        add(nLabel);
        add(nSpinner, "wrap");
        
        add(updateButton, "span, split, wrap");
        
    }
    
    public interface Listener {
        void valueChanged(Event event);
    }
    
    public void addListener(Listener listener) {
        listeners.add(listener);
    }
    
    public class Event {
        public int lowerLimit;
        public int upperLimit;
        public int populationN;
        public int sampleN;
        
        private Event() {}
    }
          
    private class LowerSpinner implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent arg0) {         
            lowerLimit = (Integer) lowerSpinner.getValue();
            if (lowerLimit < 0) {
                lowerLimit = 0;
            }
            if (lowerLimit > upperLimit) {
                upperLimit = lowerLimit;
            }
            if (lowerLimit >= populationN) {
                populationN = upperLimit;
            }

            informListeners();
        }
    }

    private class UpperSpinner implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent arg0) {
            upperLimit = (Integer) upperSpinner.getValue();
            
            if (upperLimit > Binomial.MAX_X) {
                upperLimit = Binomial.MAX_X;
            }
            
            if (upperLimit < lowerLimit) {
                lowerLimit = upperLimit;   
            }
            
            if (upperLimit > populationN) {
                populationN = upperLimit;
            }

            informListeners();
        }
    }
    
    private class PopulationNSpinner implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent arg0) {
            if (populationN < upperLimit) {
                upperLimit = populationN;
            }
            
            informListeners();
        }
    }
    
    private void informListeners() {
        Event event = new Event();
        event.lowerLimit = this.lowerLimit;
        event.upperLimit = this.upperLimit;
        event.populationN = this.populationN;
        event.sampleN = this.sampleN;
        for (Listener listener : listeners) {
            listener.valueChanged(event);
        }
    }
}
