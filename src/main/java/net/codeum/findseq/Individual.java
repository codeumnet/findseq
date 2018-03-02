package net.codeum.findseq;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Individual {

    private String chromosome;
    private double fitness;
    private final FitnessCalculator calculator;

    public Individual(String chromosome, FitnessCalculator calculator) {
        this.chromosome = chromosome;
        this.calculator = calculator;
        this.fitness = this.calculator.calculateFitness(chromosome);
    }

    public String getChromosome() {
        return this.chromosome;
    }

    public double getFitness() {
        return this.fitness;
    }

    public List<Individual> crossover(Individual thatIndividual, double crossoverRate) {
        final List<Individual> result = new ArrayList<>();

        final Random random = new Random();
        if (random.nextDouble() >= crossoverRate) {
            result.add(this);
            result.add(thatIndividual);
            return result;
        }

        final int thisCrossoverPos = random.nextInt(this.chromosome.length() / 4);
        final int thatCrossoverPos = random.nextInt(thatIndividual.getChromosome().length() / 4);

        result.add(new Individual(this.chromosome.substring(0, 4 * thisCrossoverPos)
            + thatIndividual.getChromosome().substring(4 * thatCrossoverPos), this.calculator));

        result.add(new Individual(thatIndividual.getChromosome().substring(0, 4 * thatCrossoverPos)
            + this.chromosome.substring(4 * thisCrossoverPos), this.calculator));

        return result;
    }

    public Individual mutate(double mutationRate) {
        final StringBuilder result = new StringBuilder(this.chromosome);
        final Random random = new Random();

        for (int i = 0; i < this.chromosome.length(); i++) {
            if (random.nextDouble() < mutationRate) {
                result.setCharAt(i, Character.forDigit(1 - Character.getNumericValue(result.charAt(i)), 10));
            }
        }

        return new Individual(result.toString(), this.calculator);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Individual that = (Individual) o;
        return Double.compare(that.fitness, fitness) == 0 &&
            Objects.equals(chromosome, that.chromosome);
    }

    @Override
    public int hashCode() {

        return Objects.hash(chromosome, fitness);
    }
}
