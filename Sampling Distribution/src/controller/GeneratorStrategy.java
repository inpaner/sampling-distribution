package controller;

import model.AbstractGenerator;

public interface GeneratorStrategy {
    AbstractGenerator getGenerator(int lowerLimit, int upperLimit, int n);
}
