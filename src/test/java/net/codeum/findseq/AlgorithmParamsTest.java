package net.codeum.findseq;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlgorithmParamsTest {

    @Test
    public void buildParams() {
        final AlgorithmParams result = AlgorithmParams.get()
            .populationSize(5)
            .iterations(120)
            .crossoverRate(0.666)
            .mutationRate(0.071);

        assertEquals("Wrong population size parameter.", 5, result.getPopulationSize());
        assertEquals("Wrong iterations parameter.", 120, result.getIterations());
        assertEquals("Wrong crossover rate parameter.", 0.666, result.getCrossoverRate(), 0.001);
        assertEquals("Wrong mutation rate parameter.", 0.071, result.getMutationRate(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongPopulationSizeCase() {
        AlgorithmParams.get()
            .populationSize(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongIterationCountCase() {
        AlgorithmParams.get()
            .iterations(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongCrossoverRateCase() {
        AlgorithmParams.get()
            .crossoverRate(1.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongMutationRateCase() {
        AlgorithmParams.get()
            .mutationRate(-0.0000000001);
    }
}
