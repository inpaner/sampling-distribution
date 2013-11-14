package controller;

import model.AbstractGenerator;
import model.SkewGenerator;

public class SkewStrategy implements GeneratorStrategy {

    @Override
    public AbstractGenerator getGenerator(int lowerLimit, int upperLimit, int n) {
        return new SkewGenerator(lowerLimit, upperLimit, n);
    }

}
