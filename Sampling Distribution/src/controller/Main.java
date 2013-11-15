package controller;

import model.AbstractGenerator;
import model.Generator;
import model.Sampler;
import view.InputPanel;
import view.InputPanel.Event;
import view.MainFrame;
import view.MainPanel;

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
        strategy = new UniformStrategy();
        generator = strategy.getGenerator(0, 50, 100);
        sampler = new Sampler(generator.getValues(), 1);
        
        panel = new MainPanel();
        panel.addListener(new InputListener());
        panel.update(sampler);
        frame.setPanel(panel);
    }
    
    private void updateEverything(Event event) {
        generator = strategy.getGenerator(
                event.lowerLimit, event.upperLimit, event.populationN);
        sampler = new Sampler(generator.getValues(), event.sampleN);
        panel.update(sampler);
    }
    
    private class InputListener implements InputPanel.Listener {
        @Override
        public void populationValuesChanged(Event event) {
            updateEverything(event);
        }

        @Override
        public void sampleValuesChanged(Event event) {
            sampler = new Sampler(generator.getValues(), event.sampleN);
            panel.update(sampler);
        }
        
        @Override
        public void generatorChanged(Event event, Generator generator) {
            switch(generator) {
            case BIMODAL:
                strategy = new BimodalStrategy();
                break;
            case NORMAL:
                strategy = new NormalStrategy();
                break;
            case RANDOM:
                strategy = new RandomStrategy();
                break;
            case SKEW:
                strategy = new SkewStrategy();
                break;
            case UNIFORM:
                strategy = new UniformStrategy();
                break;
            default:
                break;
            }
            updateEverything(event);
        }
        
        @Override
        public void generate(Event event) {
            generator.randomize();
            updateEverything(event);
        }

    }

}
