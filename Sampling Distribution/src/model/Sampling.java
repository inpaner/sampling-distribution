package model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class Sampling {
    
    private int n;
    private ICombinatoricsVector<Integer> values;
    private Generator<Integer> generator;
    private Double meanOfMeans;
    private Map<Double, Integer> meansCount;
    
    public static void main(String[] args) {
        AbstractGenerator n = new NormalGenerator(0, 10, 10);
        Map<Integer, Integer> values = n.getValues();
        
        for (Integer number : values.keySet()) {
            int count = values.get(number);
            System.out.println(number + ": " + count);
        }
        Sampling sampling = new Sampling();
        sampling.n = 4;
        sampling.setValues(values);
        sampling.getSamples();
        System.out.println("Orig mean: " + n.getActualMean());
        System.out.println(sampling.getMeanOfMeans());
    }
    
    public void setValues(Map<Integer, Integer> mappedValues) {
        // Flatten map
        values = Factory.createVector();
        for (Integer number : mappedValues.keySet()) {
            int count = mappedValues.get(number);
            for (int i = 0; i < count; i++) {
                values.addValue(number);
            }
        }
    }
    
    public void getSamples() {
        generator = Factory.createPermutationWithRepetitionGenerator(values, n);
        for (ICombinatoricsVector<Integer> perm : generator)
            System.out.println(perm);
    }
    
    private void solve() {
        double result = 0.0;
        int combinationCount = 0;
        Map<Double, Integer> meansCount = new TreeMap<>();
        
        for (ICombinatoricsVector<Integer> perm : generator) {
            combinationCount++;
            double mean = 0.0;
            for (Integer number : perm) {
                mean += number;
            }
            mean /= perm.getSize();
            result += mean;
        }
        meanOfMeans = result / combinationCount;
    }
    
    private void placeInMeansCount(double mean) {
        Integer count = meansCount.get(mean);
        if (count == null) {
            count = 0;
        }
        count++;
        meansCount.put(mean, count);
        
    }
    
    public Map<Double, Integer> getMeansCount() {
        if (meansCount == null) {
            solve();
        }
        return meansCount;
    }
    
    public double getMeanOfMeans() {
        if (meanOfMeans == null) {
            solve();
        }
        return meanOfMeans;
    }
    
}
