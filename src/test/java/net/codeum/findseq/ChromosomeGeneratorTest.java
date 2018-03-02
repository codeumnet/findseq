package net.codeum.findseq;

import java.util.Random;
import java.util.regex.Pattern;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChromosomeGeneratorTest {

    @Test
    public void generateLessThan10() {
        final Random random = new Random();
        final String result = ChromosomeGenerator.generate(random.nextInt(10));

        assertTrue("Chromosome should not be empty", result.length() > 0);
        assertTrue("Chromosome length should be multiple to 4", result.length() % 4 == 0);
        assertTrue("Result contains wrong symbols", Pattern.matches("^[01]+$", result));
    }

    @Test
    public void generateLessThan100() {
        final Random random = new Random();
        final String result = ChromosomeGenerator.generate(random.nextInt(100));

        assertTrue("Chromosome should not be empty", result.length() > 0);
        assertTrue("Chromosome length should be multiple to 4", result.length() % 4 == 0);
        assertTrue("Result contains wrong symbols", Pattern.matches("^[01]+$", result));
    }

    @Test
    public void generateLessThan1000000() {
        final Random random = new Random();
        final String result = ChromosomeGenerator.generate(random.nextInt(1000000));

        assertTrue("Chromosome should not be empty", result.length() > 0);
        assertTrue("Chromosome length should be multiple to 4", result.length() % 4 == 0);
        assertTrue("Result contains wrong symbols", Pattern.matches("^[01]+$", result));
    }

    @Test
    public void getMaxChromosomeValue() {
        assertEquals("Wrong max chromosome value", 246, ChromosomeGenerator.getMaxChromosomeValue(123));
    }
}
