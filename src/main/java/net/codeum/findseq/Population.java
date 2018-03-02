package net.codeum.findseq;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Population {

    private final Individual[] population;
    private final FitnessCalculator calculator;

    private int initializedIndividualCount = 0;

    public Population(int size, FitnessCalculator calculator) {
        this.population = new Individual[size];
        this.calculator = calculator;
    }

    public void generate() {
        for (int i = 0; i < this.population.length; i++) {
            addIndividual(new Individual(ChromosomeGenerator.generate(this.calculator.getTargetValue()),
                this.calculator));
        }
    }

    public Individual selectWithRouletteWheel() {
        Individual result = this.population[0];

        final double sum = Arrays.stream(this.population)
            .map(Individual::getFitness)
            .reduce(Double::sum).get();

        double threshold = new Random().nextDouble() * sum;
        for (int i = 0; i < this.population.length; i++) {
            threshold = threshold - this.population[i].getFitness();
            if (threshold < 0) {
                result = this.population[i];
                break;
            }
        }
        return result;
    }

    public void addIndividual(Individual individual) {
        if (this.initializedIndividualCount == this.population.length) {
            throw new IllegalArgumentException("Index out of bounds!");
        }

        this.population[this.initializedIndividualCount] = individual;
        this.initializedIndividualCount++;
    }

    public Individual getTheLeader() {
        return Arrays.stream(this.population)
            .max((a, b) -> {
                if (a.getFitness() < b.getFitness()) {
                    return -1;
                }
                if (a.getFitness() > b.getFitness()) {
                    return 1;
                }
                return 0;
            })
            .get();
    }

    public int getInitializedIndividualCount() {
        return initializedIndividualCount;
    }

    @Override
    public String toString() {
        final List<Integer> list = Arrays.stream(this.population)
            .map(individual -> ChromosomeTranslator.translateChromosome(individual.getChromosome()))
            .collect(Collectors.toList());

        return list.toString();
    }
}
