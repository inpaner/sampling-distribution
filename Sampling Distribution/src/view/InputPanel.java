package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.crypto.spec.PSource.PSpecified;
import javax.swing.AbstractButton;
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
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.math3.genetics.NPointCrossover;

import model.Binomial;
import model.Generator;
import net.miginfocom.swing.MigLayout;

public class InputPanel extends JPanel {
    
    private JSpinner lowerSpinner;
    private JSpinner upperSpinner;
    private JSpinner populationSpinner;
    private JSpinner sampleSpinner;
    
    private JButton generateButton;
    
    private int lowerLimit;
    private int upperLimit;
    private int populationN;
    private int sampleN;
    
    private List<Listener> listeners = new ArrayList<>();
    
    public InputPanel() {
        /*
         * Initialize Components
         */
        
        // Generator Radio buttons
        GeneratorRadio uniformRadio = new GeneratorRadio("Uniform", Generator.UNIFORM);
        GeneratorRadio normalRadio = new GeneratorRadio("Normal", Generator.NORMAL);
        GeneratorRadio skewedRadio = new GeneratorRadio("Skewed", Generator.SKEW);
        GeneratorRadio bimodalRadio = new GeneratorRadio("Bimodal", Generator.BIMODAL);
        GeneratorRadio randomRadio = new GeneratorRadio("Random", Generator.RANDOM);
        ButtonGroup distributionGroup = new ButtonGroup();
        distributionGroup.add(uniformRadio);
        distributionGroup.add(normalRadio);
        distributionGroup.add(skewedRadio);
        distributionGroup.add(bimodalRadio);
        distributionGroup.add(randomRadio);
        uniformRadio.setSelected(true);
        
        Box radioBox = new Box(BoxLayout.X_AXIS);
        TitledBorder border = BorderFactory.createTitledBorder("Distribution");
        border.setTitleJustification(TitledBorder.LEFT);
        border.setTitlePosition(TitledBorder.TOP);
        radioBox.setBorder(border);
        radioBox.add(uniformRadio);
        radioBox.add(normalRadio);
        radioBox.add(skewedRadio);
        radioBox.add(bimodalRadio);
        radioBox.add(randomRadio);

        
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

        // population n
        JLabel populationLabel = new JLabel("N: ");
        populationSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Binomial.MAX_X, 1));
        field = ((JSpinner.DefaultEditor) populationSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        // sample n
        JLabel sampleLabel = new JLabel("n: ");
        sampleSpinner = new JSpinner(new SpinnerNumberModel(1, 0, Binomial.MAX_X, 1));
        field = ((JSpinner.DefaultEditor) sampleSpinner.getEditor());
        prefSize = field.getPreferredSize();
        prefSize = new Dimension(30, prefSize.height);
        field.setPreferredSize(prefSize);
        
        // generate button
        generateButton = new JButton("Generate");
        
        initValues();
        
        /*
         * Add internal listeners
         */
        RadioListener radioListener = new RadioListener();
        for (Enumeration<AbstractButton> buttons = distributionGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            button.addActionListener(radioListener);
        }
        lowerSpinner.addChangeListener(new LowerSpinner());
        upperSpinner.addChangeListener(new UpperSpinner());
        populationSpinner.addChangeListener(new PopulationSpinner());
        sampleSpinner.addChangeListener(new SampleSpinner());
        generateButton.addActionListener(new GenerateListener());        
        
        /*
         * Add components
         */
        setLayout(new MigLayout("", "[][30][30, center][30]"));
        add(radioBox, "span, split, wrap");
        
        add(xLabel);
        add(lowerSpinner);
        add(toLabel);
        add(upperSpinner, "wrap");
        
        add(populationLabel);
        add(populationSpinner, "wrap");
        
        add(sampleLabel);
        add(sampleSpinner, "wrap");
        
        add(generateButton, "span, split, wrap");
        
    }
    
    public interface Listener {
        void populationValuesChanged(Event event);
        void sampleValuesChanged(Event event);
        public abstract void generatorChanged(Event event, Generator generator);
        public abstract void generate(Event event);
    }
    
    public void addListener(Listener listener) {
        listeners.add(listener);
    }
    
    public class Event {
        public int lowerLimit;
        public int upperLimit;
        public int populationN;
        public int sampleN;
        
        public Event() {}
    }
    
    public void updateValues(Event event) {
        lowerSpinner.setValue(event.lowerLimit);
        upperSpinner.setValue(event.upperLimit);
        populationSpinner.setValue(event.populationN);
        sampleSpinner.setValue(event.sampleN);
    }
    
    private void initValues() {
        lowerLimit = 0;
        upperLimit = 50;
        populationN = 100;
        sampleN = 1;
        updateValues(generateEvent());
    }
    
    private Event generateEvent() {
        Event event = new Event();
        event.lowerLimit = this.lowerLimit;
        event.upperLimit = this.upperLimit;
        event.populationN = this.populationN;
        event.sampleN = this.sampleN;
        return event;
    }
    
    private void populationValuesChanged() {
        Event event = generateEvent();
        updateValues(event);
        for (Listener listener : listeners) {
            listener.populationValuesChanged(event);
        }
    }
    
    private void sampleValuesChanged() {
        Event event = generateEvent();
        updateValues(event);
        for (Listener listener : listeners) {
            listener.sampleValuesChanged(event);
        }
    }

    /*
     * Serves as a struct to contain the Generator Enum
     */
    @SuppressWarnings("serial")
    private class GeneratorRadio extends JRadioButton {
        private Generator generator;
        private GeneratorRadio(String title, Generator generator) {
            super(title);
            this.generator = generator;
        }
    }
    
    /*
     * Internal listeners
     */
    
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
            
            populationValuesChanged();
        }
    }

    private class UpperSpinner implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent ev) {
            upperLimit = (Integer) upperSpinner.getValue();
            
            if (upperLimit > Binomial.MAX_X) {
                upperLimit = Binomial.MAX_X;
            }
            
            if (upperLimit < lowerLimit) {
                lowerLimit = upperLimit;   
            }
            
            populationValuesChanged();
        }
    }

    private class PopulationSpinner implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent arg0) {
            populationN = (Integer) populationSpinner.getValue();
            populationValuesChanged();
        }
    }

    private class SampleSpinner implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent arg0) {
            sampleN = (Integer) sampleSpinner.getValue();
            sampleValuesChanged();
        }
    }

    private class RadioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Event event = generateEvent();
            
            GeneratorRadio radio = (GeneratorRadio) e.getSource();
            for (Listener listener : listeners) {
                listener.generatorChanged(event, radio.generator);
            }
        }
    }
    
    private class GenerateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            Event event = generateEvent();
            
            for (Listener listener : listeners) {
                listener.generate(event);
            }
        }
        
    }
}
