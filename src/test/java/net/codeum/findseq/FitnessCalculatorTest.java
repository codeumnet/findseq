package net.codeum.findseq;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FitnessCalculatorTest {

    @Test
    public void calculateFitness() {
        // 5 + 5 * 6 / 2 = 30
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.FIVE.getValue(),
            ChromosomeTranslator.Gene.PLUS.getValue(),
            ChromosomeTranslator.Gene.FIVE.getValue(),
            ChromosomeTranslator.Gene.MULT.getValue(),
            ChromosomeTranslator.Gene.SIX.getValue(),
            ChromosomeTranslator.Gene.DIV.getValue(),
            ChromosomeTranslator.Gene.TWO.getValue()
        ));

        final int targetValue = 10;

        final double fitness = new FitnessCalculator(targetValue).calculateFitness(chromosome);

        assertEquals("", 1.0 / 21, fitness, 0.001);
    }

    @Test
    public void randomTest() {
        final Random random = new Random();
        final int targetValue = 10 + random.nextInt(10000);

        final String chromosome = ChromosomeGenerator.generate(targetValue);

        final double fitness = new FitnessCalculator(targetValue).calculateFitness(chromosome);

        assertTrue("Fitness must be greater than or equal zero", fitness >= 0);
    }
}
