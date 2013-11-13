package model;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public abstract class AbstractGenerator {
    protected int lowerLimit;
    protected int upperLimit;
    protected int n;
    protected final Random rand;
    protected int mean;
    protected double variance;
    protected Map<Integer, Integer> values; 
    
    
    public static void main(String[] args) {
        NormalGenerator n = new NormalGenerator(0, 100, 1000);
        Map<Integer, Integer> values = n.getValues();
        for (Integer number : values.keySet()) {
            int count = values.get(number);
            System.out.println(number + ": " + count);
        }
        
    }
    
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
    
    public final Map<Integer, Integer>  getValues() {
        return values;
    }
    
    abstract int getNext();

}
