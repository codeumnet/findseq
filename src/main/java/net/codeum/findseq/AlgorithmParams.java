package net.codeum.findseq;

public class AlgorithmParams {

    private double crossoverRate = 0.7;
    private double mutationRate = 0.2;
    private int populationSize = 100;
    private int iterations = 100;
    private boolean elitism = true;

    private AlgorithmParams() {}

    public static AlgorithmParams get() {
        return new AlgorithmParams();
    }

    public AlgorithmParams crossoverRate(double crossoverRate) {
        if (crossoverRate < 0 || crossoverRate > 1) {
            throw new IllegalArgumentException("Probability of crossover must be between 0 and 1.");
        }
        this.crossoverRate = crossoverRate;
        return this;
    }

    public AlgorithmParams mutationRate(double mutationRate) {
        if (mutationRate < 0 || mutationRate > 1) {
            throw new IllegalArgumentException("Probability of mutation must be between 0 and 1.");
        }
        this.mutationRate = mutationRate;
        return this;
    }

    public AlgorithmParams populationSize(int populationSize) {
        if (populationSize < 1) {
            throw new IllegalArgumentException("Wrong population size");
        }
        this.populationSize = populationSize;
        return this;
    }

    public AlgorithmParams iterations(int iterations) {
        if (iterations < 1) {
            throw new IllegalArgumentException("Wrong iterations count");
        }
        this.iterations = iterations;
        return this;
    }

    public AlgorithmParams elitism(boolean elitism) {
        this.elitism = elitism;
        return this;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getIterations() {
        return iterations;
    }

    public boolean isElitism() {
        return elitism;
    }
}
