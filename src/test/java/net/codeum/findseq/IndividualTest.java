package net.codeum.findseq;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class IndividualTest {

    @Test
    public void crossover() {
        final int targetValue = 71;
        final FitnessCalculator calculator = new FitnessCalculator(targetValue);

        final Individual parentOne = new Individual(ChromosomeGenerator.generate(targetValue), calculator);
        final Individual parentTwo = new Individual(ChromosomeGenerator.generate(targetValue), calculator);

        final List<Individual> result = parentOne.crossover(parentTwo, 1.0);

        assertEquals("Wrong count of children.", 2, result.size());

        final Individual childOne = result.get(0);
        final Individual childTwo = result.get(1);

        final boolean isChildOneChromosomeLengthCorrect = childOne.getChromosome().length() <= parentOne.getChromosome().length() +
            parentTwo.getChromosome().length();

        final boolean isChildTwoChromosomeLengthCorrect = childTwo.getChromosome().length() <= parentOne.getChromosome().length() +
            parentTwo.getChromosome().length();

        assertTrue("Child one has incorrect length of chromosome", isChildOneChromosomeLengthCorrect);
        assertTrue("Child two has incorrect length of chromosome", isChildTwoChromosomeLengthCorrect);
    }

    @Test
    public void mutate() {
        final int targetValue = 102;
        final FitnessCalculator calculator = new FitnessCalculator(targetValue);

        final Individual individual = new Individual(ChromosomeGenerator.generate(targetValue), calculator);

        final Individual result = individual.mutate(1.0);

        assertNotEquals("Mutated individual must be different", individual, result);
    }
}
