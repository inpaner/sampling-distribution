package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class Sampler {
    
    private final int n;
    private final ICombinatoricsVector<Integer> values;
    private Generator<Integer> generator;
    private Double meanOfMeans;
    private Map<Double, Integer> meansCount;
    private Double varianceOfMeans;
    private List<String> permutations;
    private List<Double> permutationMeans;
    
    public Sampler(Map<Integer, Integer> mappedValues, int n) {
        this.n = n;

        // Flatten map
        values = Factory.createVector();
        for (Integer number : mappedValues.keySet()) {
            int count = mappedValues.get(number);
            for (int i = 0; i < count; i++) {
                values.addValue(number);
            }
        }
    }
    
    public static void main(String[] args) {
        AbstractGenerator n = new SkewGenerator(0, 30, 50);
        Map<Integer, Integer> values = n.getValues();
        boolean log = true;
        if (log) {
            for (Integer number : values.keySet()) {
                int count = values.get(number);
                System.out.println(number + ": " + count);
            }
        }
        Sampler sampler = new Sampler(values, 3);
        
        Map<Double, Integer> meanOfMeans = sampler.getMeansCount();
        if (log) {
            for (Double mean : meanOfMeans.keySet()) {
                int count = meanOfMeans.get(mean);
                System.out.println(mean + ": " + count);
            }
        }
        System.out.println("Orig mean: " + n.getActualMean());
        System.out.println("Mean of means: " + sampler.getMeanOfMeans());
        System.out.println("Orig variance: " + n.getActualVariance());
        System.out.println("Variance of means: " + sampler.getVarianceOfMeans());
    }
        
    
    private void solve() {
        double sumOfMeans = 0.0;
        int combinationsCount = 0;
        double sumOfSquares = 0;
        meansCount = new TreeMap<>();
        permutations = new ArrayList<>();
        permutationMeans = new ArrayList<>();
        generator = Factory.createPermutationWithRepetitionGenerator(values, n);
        for (ICombinatoricsVector<Integer> permutation : generator) {
            permutations.add(permutation.getVector().toString());
            combinationsCount++;
            double sum = 0.0;
            for (Integer number : permutation) {
                sum += number;
            }
            double mean = sum / permutation.getSize();
            permutationMeans.add(mean);
            placeInMeansCount(mean);
            sumOfMeans += mean;
            sumOfSquares += Math.pow(mean, 2);
        }
        meanOfMeans = sumOfMeans / combinationsCount;
        double meanOfSquares = sumOfSquares / combinationsCount;
        varianceOfMeans = meanOfSquares - Math.pow(meanOfMeans, 2);
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
    
    public double getVarianceOfMeans() {
        if (varianceOfMeans == null) {
            solve();
        }
        return varianceOfMeans;   
    }
    
    public List<String> getPermutations() {
        return permutations;
    }
    
    public List<Double> getPermutationMeans() {
        return permutationMeans;
    }
}
