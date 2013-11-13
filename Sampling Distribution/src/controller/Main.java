package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Binomial;

import view.InputPanel;
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
        inputPanel = mainPanel.getInputPanel();
        initInputs();
        outputTable = mainPanel.getOutputTable();
        outputGraph = mainPanel.getOutputGraph();
        inputPanel.addUpdateListener(update());
        inputPanel.addLowerSpinnerListener(lowerSpinner());
        
        inputPanel.addUpperSpinnerListener(upperSpinner());
        
        inputPanel.addNSpinnerListener(nSpinner());
        inputPanel.addPSliderListener(pSlider());
        
    }
    
    private void initInputs() {
        inputPanel.setXLower(0);
        inputPanel.setXUpper(5);
        inputPanel.setN(10);
        inputPanel.setP(0.3);
    }
    
    private ActionListener update() {
        return new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ev) {
            inputPanel.setPSlider(inputPanel.getP());
            updateOutputs(inputPanel.getXLower(),
                         inputPanel.getXUpper(),
                         inputPanel.getN(),
                         inputPanel.getP());     
        }
        };
    }
    
    private ChangeListener lowerSpinner() {
        return new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent arg0) {
            int xLower = inputPanel.getXLower();
            int xUpper = inputPanel.getXUpper();
            int n = inputPanel.getN();
            double p = inputPanel.getP();
            
            if (xLower < 0) {
                xLower = 0;
            }
            
            if (xLower > xUpper || inputPanel.isSingle()) {
                xUpper = xLower;
                inputPanel.setXUpper(xUpper);
                inputPanel.setXLower(xLower);   
            }
            
            if (xLower >= n) {
                n = xUpper;
                inputPanel.setN(n);
            }
            
            
            updateOutputs(xLower, xUpper, n, p);
        }
        };
    }
    

    
    private ChangeListener upperSpinner() {
        return new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent arg0) {
            int xLower = inputPanel.getXLower();
            int xUpper = inputPanel.getXUpper();
            int n = inputPanel.getN();
            double p = inputPanel.getP();
            
            if (xUpper > Binomial.MAX_X) {
                xUpper = Binomial.MAX_X;
            }
            
            if (xUpper < xLower) {
                xLower = xUpper;
                inputPanel.setXUpper(xUpper);
                inputPanel.setXLower(xLower);   
            }
            
            if (xUpper > n) {
                n = xUpper;
                inputPanel.setN(n);
            }
            
            updateOutputs(xLower, xUpper, n, p);
        }
        };
    }
    
    private ChangeListener nSpinner() {
        return new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent arg0) {
            int xLower = inputPanel.getXLower();
            int xUpper = inputPanel.getXUpper();
            int n = inputPanel.getN();
            double p = inputPanel.getP();
            
            if (n < xUpper) {
                xUpper = n;
                inputPanel.setXUpper(xUpper);
            }
            
            updateOutputs(xLower, xUpper, n, p);
        }
        };
    }
    
        

    
    private ChangeListener pSlider() {
        return new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent arg0) {
            int xLower = inputPanel.getXLower();
            int xUpper = inputPanel.getXUpper();
            int n = inputPanel.getN();
            double p = inputPanel.getPSlider();
            
            inputPanel.setP(p);
            
            updateOutputs(xLower, xUpper, n, p);
        }
        };
    }
    
    private boolean verifyValues(int xLower, int xUpper, int n, double p) {
        boolean verified = true;
        
        if (xLower > xUpper || 
                xLower > n ||
                xUpper > n ||
                xLower < 0 ||
                p > 1 ||
                p < 0)
            
            verified = false;
        
        return verified;
    }
    
    private void updateOutputs(int xLower, int xUpper, int n, double p) {
        if (verifyValues(xLower, xUpper, n, p)) {
            Binomial binomial = new Binomial(xLower, xUpper, n, p);
            inputPanel.setBValue(binomial.probability());
            outputTable.updateData(binomial);
            outputGraph.updateData(binomial);
        }
    }
    
}
