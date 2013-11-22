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
        double retval = -1;
        do {
            retval = (int) mean + rand.nextGaussian() * stddev;
        } while (retval < lowerLimit || retval > upperLimit);
        
        return (int) retval;
    }

}
