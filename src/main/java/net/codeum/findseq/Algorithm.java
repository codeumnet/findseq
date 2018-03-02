package net.codeum.findseq;

import java.util.List;

public class Algorithm {

    public static String run(int targetValue, AlgorithmParams params) {
        final FitnessCalculator calculator = new FitnessCalculator(targetValue);
        Population population = new Population(params.getPopulationSize(), calculator);
        population.generate();

        for (int step = 0; step < params.getIterations(); step++) {
            Population newPopulation = new Population(params.getPopulationSize(), calculator);

            if (params.isElitism()) {
                newPopulation.addIndividual(population.getTheLeader());
            }

            while (newPopulation.getInitializedIndividualCount() < params.getPopulationSize()) {
                final Individual parentOne = population.selectWithRouletteWheel();
                final Individual parentTwo = population.selectWithRouletteWheel();

                final List<Individual> children = parentOne.crossover(parentTwo, params.getCrossoverRate());
                children.forEach(child -> {
                    child = child.mutate(params.getMutationRate());
                    if (newPopulation.getInitializedIndividualCount() < params.getPopulationSize()) {
                        newPopulation.addIndividual(child);
                    }
                });
            }

            population = newPopulation;

            if (calculator.calculateFitness(population.getTheLeader().getChromosome()) == 1) {
                break;
            }
        }

        final String result = population.getTheLeader().getChromosome();

        if (calculator.calculateFitness(result) == 1) {
            System.out.println(String.format("For target number %d was found expression '%s'", targetValue,
                ChromosomeTranslator.toExpression(result)));
        } else {
            System.out.println(String.format("For target number %d were not found any expressions", targetValue));
        }

        return result;
    }
}
