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
import view.InputPanel;
import view.InputPanel.Event;
import view.MainFrame;
import view.MainPanel;
import view.OutputGraph;
import view.OutputTable;

public class Main {
    private MainPanel mainPanel;
    private AbstractGenerator generator;
    private GeneratorStrategy strategy;
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        MainFrame frame = new MainFrame();
        mainPanel = new MainPanel();
        frame.setPanel(mainPanel);
        strategy = new NormalStrategy();
        generator = strategy.getGenerator(0, 10, 30);

    }
    
    private class InputListener implements InputPanel.Listener {
        @Override
        public void populationValuesChanged(Event event) {
            generator = strategy.getGenerator(
                    event.lowerLimit, event.upperLimit, event.populationN);
        }

        @Override
        public void sampleValuesChanged(Event event) {
            // TODO Auto-generated method stub
            
        }
    }

    
    
}
