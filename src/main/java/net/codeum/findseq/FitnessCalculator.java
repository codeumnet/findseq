package net.codeum.findseq;

import static java.lang.Math.abs;

public class FitnessCalculator {

    private final int targetValue;

    public FitnessCalculator(int targetValue) {this.targetValue = targetValue;}

    public double calculateFitness(String chromosome) {
        return 1.0 / (1 + abs(this.targetValue - ChromosomeTranslator.translateChromosome(chromosome)));
    }

    public int getTargetValue() {
        return targetValue;
    }
}
