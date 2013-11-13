package model;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class NormalGenerator {
    private final int lowerLimit;
    private final int upperLimit;
    private final int n;
    private final Random rand;
    private int mean;
    private double variance;
    
    public static void main(String[] args) {
        NormalGenerator n = new NormalGenerator(0, 100, 1000);
        Map<Integer, Integer> values = n.getValues();
        for (Integer number : values.keySet()) {
            int count = values.get(number);
            System.out.println(number + ": " + count);
        }
        System.out.println(n.variance);
    }
    
    public NormalGenerator(int lowerLimit, int upperLimit, int n) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.n = n;
        rand = new Random();
        mean = (upperLimit + lowerLimit) / 2;
        
        int range = upperLimit - lowerLimit;
        variance = rand.nextInt(range) + rand.nextGaussian();
    }
    
    public Map<Integer, Integer> getValues() {
        Map<Integer, Integer> result = new TreeMap<>();
        
        for (int i = 0; i < n; i++) {
            int nextValue = getNext();
            Integer count = result.get(nextValue);
            if (count == null) {
                count = 0;
            }
            count++;
            result.put(nextValue, count);
        }

        return result;
    }
    
    private int getNext() {
        double sigma = rand.nextGaussian();
        int result = 0;
        result = (int) (mean + sigma * Math.sqrt(variance)); 
        
        return result;
    }
    

}
