package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.AbstractGenerator;
import model.Binomial;
import model.NormalGenerator;
import model.Sampler;
import view.InputPanel;
import view.InputPanel.Event;
import view.MainFrame;
import view.MainPanel;
import view.OutputGraph;
import view.OutputTable;

public class Main {
    private MainPanel panel;
    private AbstractGenerator generator;
    private Sampler sampler;
    private GeneratorStrategy strategy;
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        MainFrame frame = new MainFrame();
        strategy = new NormalStrategy();
        generator = strategy.getGenerator(0, 10, 20);
        sampler = new Sampler(generator.getValues(), 4);
        
        
        panel = new MainPanel();
        panel.addListener(new InputListener());
        panel.update(sampler);
        frame.setPanel(panel);
        
    }
    
    private class InputListener implements InputPanel.Listener {
        @Override
        public void populationValuesChanged(Event event) {
            generator = strategy.getGenerator(
                    event.lowerLimit, event.upperLimit, event.populationN);
            sampler = new Sampler(generator.getValues(), event.sampleN);
        }

        @Override
        public void sampleValuesChanged(Event event) {
            sampler = new Sampler(generator.getValues(), event.sampleN);
            panel.update(sampler);
        }
    }

    
    
}
