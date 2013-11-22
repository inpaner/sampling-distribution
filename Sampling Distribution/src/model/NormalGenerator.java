package model;

public class NormalGenerator extends AbstractGenerator {
    
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
