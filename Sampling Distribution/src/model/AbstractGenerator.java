package model;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public abstract class AbstractGenerator {
    protected int lowerLimit;
    protected int upperLimit;
    protected int n;
    protected final Random rand;
    protected double mean;
    protected double variance;
    protected Map<Integer, Integer> values; 
    
    protected AbstractGenerator(int lowerLimit, int upperLimit, int n) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.n = n;
        rand = new Random();
        mean = (upperLimit + lowerLimit) / 2;
        
        generateValues();
    }
    
    private void generateValues() {
        int range = upperLimit - lowerLimit;
        variance = rand.nextInt(range) + rand.nextGaussian();
        
        values = new TreeMap<>();
        
        for (int i = 0; i < n; i++) {
            int nextValue = getNext();
            Integer count = values.get(nextValue);
            if (count == null) {
                count = 0;
            }
            count++;
            values.put(nextValue, count);
        }

    }
    
    public double getActualMean() {
        double result = 0;
        for (Integer number : values.keySet()) {
            int count = values.get(number);
            result = result + number * count;
        }
        return result / n;
    }
    
    public double getActualVariance() {
        double meanOfSquares = 0;
        for (Integer number : values.keySet()) {
            int count = values.get(number);
            meanOfSquares = meanOfSquares + Math.pow(number, 2) * count;
        }
        meanOfSquares /= n;
        double squareOfMeans = Math.pow(getActualMean(), 2);
        return meanOfSquares - squareOfMeans;
    }
    
    public final Map<Integer, Integer>  getValues() {
        return values;
    }
    
    abstract int getNext();

}
