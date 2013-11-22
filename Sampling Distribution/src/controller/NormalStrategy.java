package controller;

import model.AbstractGenerator;
import model.NormalGenerator;

public class NormalStrategy implements GeneratorStrategy {

    @Override
    public AbstractGenerator getGenerator(int lowerLimit, int upperLimit, int n) {
        return new NormalGenerator(lowerLimit, upperLimit, n);
    }
}
