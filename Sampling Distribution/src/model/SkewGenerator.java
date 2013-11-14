package model;

import java.util.Map;

/**
 * http://stackoverflow.com/a/13548135
 */
public class SkewGenerator extends AbstractGenerator {
    
    public SkewGenerator(int lowerLimit, int upperLimit, int n) {
        super(lowerLimit, upperLimit, n);
        int range = upperLimit - lowerLimit;
        mean = rand.nextInt(range) + lowerLimit;
    }
    
    @Override
    int getNext() {
        double stddev = Math.sqrt(variance);
        double retval = Math.max(lowerLimit, Math.min(upperLimit, (int) mean + rand.nextGaussian() * stddev));
        return (int) retval;
    }

}
