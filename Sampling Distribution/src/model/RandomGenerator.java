package model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class RandomGenerator extends AbstractGenerator {
    private static final int SENSITIVITY = 1000;
    public RandomGenerator(int lowerLimit, int upperLimit, int n) {
        super(lowerLimit, upperLimit, n);
    }

    @Override
    int getNext() {
        return 0;
    }
    
    @Override
    protected void generateValues() {
        // randomize distribution
        int seedCount = 0;
        List<Integer> distribution = new ArrayList<>();
        
        for (int i = lowerLimit; i <= upperLimit; i++) {
            int seed = rand.nextInt(SENSITIVITY);
            seedCount += seed;
            distribution.add(seed);
        }
        
        /*
         * Place count of numbers into map. Percentage of the 
         * random seed against total seeds is used to calculate
         * count. 
         */
        values = new TreeMap<>();
        int mapIndex = 0;
        int numbersCount = 0;
        for (int i = lowerLimit; i <= upperLimit; i++) {
            int count = n * distribution.get(mapIndex) / seedCount;
            values.put(i, count);
            numbersCount += count;
            mapIndex++;
        }

    }
}
