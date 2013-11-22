package controller;

import model.AbstractGenerator;
import model.RandomGenerator;

public class RandomStrategy implements GeneratorStrategy {

    @Override
    public AbstractGenerator getGenerator(int lowerLimit, int upperLimit, int n) {
        return new RandomGenerator(lowerLimit, upperLimit, n);
    }
}
