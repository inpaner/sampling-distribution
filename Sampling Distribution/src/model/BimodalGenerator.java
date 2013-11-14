package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;


public class BimodalGenerator extends AbstractGenerator {
    protected double mean2;
    protected double variance2;
    public BimodalGenerator(int lowerLimit, int upperLimit, int n) {
        super(lowerLimit, upperLimit, n);
        
        int range = upperLimit - lowerLimit;
        mean = rand.nextInt(range) + lowerLimit;
        mean2 = rand.nextInt(range) + lowerLimit;
        variance2 = rand.nextInt(range) + rand.nextGaussian();
        
    }

    @Override
    int getNext() {
        double meanToUse = mean;
        double varToUse = variance;
        
        if (rand.nextInt(2) == 0) {
            meanToUse = mean2;
            varToUse = variance2;
        }
        
        double stddev = Math.sqrt(varToUse);
        double retval = Math.max(lowerLimit, Math.min(upperLimit, (int) meanToUse + rand.nextGaussian() * stddev));
        return (int) retval;
    }
    

}
