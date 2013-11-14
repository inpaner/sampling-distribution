package controller;

import model.AbstractGenerator;
import model.BimodalGenerator;

public class BimodalStrategy implements GeneratorStrategy {

    @Override
    public AbstractGenerator getGenerator(int lowerLimit, int upperLimit, int n) {
        return new BimodalGenerator(lowerLimit, upperLimit, n);
    }

}
