package controller;

import model.AbstractGenerator;
import model.UniformGenerator;

public class UniformStrategy implements GeneratorStrategy {

    @Override
    public AbstractGenerator getGenerator(int lowerLimit, int upperLimit, int n) {
        return new UniformGenerator(lowerLimit, upperLimit, n);
    }
}
