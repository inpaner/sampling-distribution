package model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class RandomGenerator extends AbstractGenerator {
    private static final int SENSITIVITY = 1000;
    protected RandomGenerator(int lowerLimit, int upperLimit, int n) {
        super(lowerLimit, upperLimit, n);
    }

    @Override
    int getNext() {
        return 0;
    }
    
    @Override
    protected void generateValues() {
        // randomize distribution
        int totalCount = 0;
        List<Integer> distribution = new ArrayList<>();
        
        for (int i = lowerLimit; i <= upperLimit; i++) {
            int value = rand.nextInt(SENSITIVITY);
            totalCount += value;
            distribution.add(value);
        }
        
        values = new TreeMap<>();
        int index = 0;
        for (int i = lowerLimit; i <= upperLimit; i++) {
            int count = n * distribution.get(index) / totalCount;
            values.put(i, count);
            index++;
        }
    }
}
