package model;

import java.util.Map;

public class NormalGenerator extends AbstractGenerator {
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
        super(lowerLimit, upperLimit, n);
    }
    
    int getNext() {
        double sigma = rand.nextGaussian();
        int result = 0;
        result = (int) (mean + sigma * Math.sqrt(variance)); 
        
        return result;
    }
    

}
