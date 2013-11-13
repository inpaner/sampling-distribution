package model;

import java.util.Map;

/**
 * http://stackoverflow.com/a/13548135
 */
public class SkewGenerator extends AbstractGenerator {
    public static void main(String[] args) {
        SkewGenerator n = new SkewGenerator(0, 100, 1000);
        Map<Integer, Integer> values = n.getValues();
        
        for (Integer number : values.keySet()) {
            int count = values.get(number);
            System.out.println(number + ": " + count);
        }
        System.out.println("T Mean: " + n.skew);
        System.out.println("A Mean: " + n.getActualMean());
        System.out.println("T Variance: " + n.variance);
        System.out.println("A Variance: " + n.getActualVariance());
    }
    
    public double skew;
    protected SkewGenerator(int lowerLimit, int upperLimit, int n) {
        super(lowerLimit, upperLimit, n);
        
        skew = rand.nextInt(upperLimit);
        generateValues();
        
    }
    
    
    @Override
    int getNext() {
        double stddev = Math.sqrt(variance);
        double retval = Math.max(lowerLimit, Math.min(upperLimit, (int) skew + rand.nextGaussian() * stddev));
        return (int) retval;
    }

}
