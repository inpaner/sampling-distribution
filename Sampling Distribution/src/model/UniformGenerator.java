package model;

public class UniformGenerator extends AbstractGenerator {

    public UniformGenerator(int lowerLimit, int upperLimit, int n) {
        super(lowerLimit, upperLimit, n);
    }

    @Override
    int getNext() {
        int range = upperLimit - lowerLimit;
        return rand.nextInt(range + 1) + lowerLimit;
    }
}
