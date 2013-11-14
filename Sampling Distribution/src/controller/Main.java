package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Binomial;
import view.InputPanel;
import view.InputPanel.Event;
import view.MainFrame;
import view.MainPanel;
import view.OutputGraph;
import view.OutputTable;

public class Main {
    private MainPanel mainPanel;
    private InputPanel inputPanel;
    private OutputTable outputTable;
    private OutputGraph outputGraph;
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        MainFrame frame = new MainFrame();
        mainPanel = new MainPanel();
        frame.setPanel(mainPanel);
        
    }
    
    private class InputListener implements InputPanel.Listener {
        @Override
        public void valueChanged(Event event) {
            
        }
    }

    
    
}
