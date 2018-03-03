package net.codeum.findseq;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChromosomeEvaluatorTest {

    @Test
    public void evaluateSimpleCase() {
        // 1 + 5 * 6 = 36
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeEvaluator.Gene.ONE.getValue(),
            ChromosomeEvaluator.Gene.PLUS.getValue(),
            ChromosomeEvaluator.Gene.FIVE.getValue(),
            ChromosomeEvaluator.Gene.MULT.getValue(),
            ChromosomeEvaluator.Gene.SIX.getValue()
        ));

        final int result = ChromosomeEvaluator.evaluateChromosome(chromosome);

        assertEquals("Wrong result of evaluation", 36, result);
    }

    @Test
    public void evaluateAllOperationsAndDigitsIncluded() {
        // 9 + 8 - 7 * 6 / 5 + 4 - 3 * 2 - 1 / 5 = 5
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeEvaluator.Gene.NINE.getValue(),
            ChromosomeEvaluator.Gene.PLUS.getValue(),
            ChromosomeEvaluator.Gene.EIGHT.getValue(),
            ChromosomeEvaluator.Gene.MINUS.getValue(),
            ChromosomeEvaluator.Gene.SEVEN.getValue(),
            ChromosomeEvaluator.Gene.MULT.getValue(),
            ChromosomeEvaluator.Gene.SIX.getValue(),
            ChromosomeEvaluator.Gene.DIV.getValue(),
            ChromosomeEvaluator.Gene.FIVE.getValue(),
            ChromosomeEvaluator.Gene.PLUS.getValue(),
            ChromosomeEvaluator.Gene.FOUR.getValue(),
            ChromosomeEvaluator.Gene.MINUS.getValue(),
            ChromosomeEvaluator.Gene.THREE.getValue(),
            ChromosomeEvaluator.Gene.MULT.getValue(),
            ChromosomeEvaluator.Gene.TWO.getValue(),
            ChromosomeEvaluator.Gene.MINUS.getValue(),
            ChromosomeEvaluator.Gene.ONE.getValue(),
            ChromosomeEvaluator.Gene.DIV.getValue(),
            ChromosomeEvaluator.Gene.FIVE.getValue()
        ));

        final int result = ChromosomeEvaluator.evaluateChromosome(chromosome);

        assertEquals("Wrong result of evaluation", 5, result);
    }

    @Test
    public void evaluateCaseWithSomeWrongGenes() {
        // 1 + 5 * 6 = 36
        final String chromosome = String.join("", Arrays.asList(
            "$",
            ChromosomeEvaluator.Gene.ONE.getValue(),
            ChromosomeEvaluator.Gene.PLUS.getValue(),
            "$",
            ChromosomeEvaluator.Gene.FIVE.getValue(),
            "$",
            "$",
            ChromosomeEvaluator.Gene.MULT.getValue(),
            ChromosomeEvaluator.Gene.SIX.getValue()
        ));

        final int result = ChromosomeEvaluator.evaluateChromosome(chromosome);

        assertEquals("Wrong result of evaluation", 36, result);
    }

    @Test
    public void evaluateCaseWithOneGene() {
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeEvaluator.Gene.ONE.getValue()
        ));

        final int result = ChromosomeEvaluator.evaluateChromosome(chromosome);

        assertEquals("Wrong result of evaluation", 1, result);
    }

    @Test
    public void evaluateCaseWithOneOpGene() {
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeEvaluator.Gene.PLUS.getValue()
        ));

        final int result = ChromosomeEvaluator.evaluateChromosome(chromosome);

        assertEquals("Wrong result of evaluation", Integer.MAX_VALUE, result);
    }

    @Test
    public void evaluateCaseWithMessOfGenes() {
        final String chromosome = String.join("", Arrays.asList(
            "$",
            ChromosomeEvaluator.Gene.DIV.getValue(),
            ChromosomeEvaluator.Gene.ONE.getValue(),
            ChromosomeEvaluator.Gene.TWO.getValue(),
            ChromosomeEvaluator.Gene.THREE.getValue(), // 3
            ChromosomeEvaluator.Gene.PLUS.getValue(),
            "$",
            ChromosomeEvaluator.Gene.MULT.getValue(),  // *
            ChromosomeEvaluator.Gene.FIVE.getValue(),
            ChromosomeEvaluator.Gene.FOUR.getValue(),
            "$",
            ChromosomeEvaluator.Gene.EIGHT.getValue(), // 8
            ChromosomeEvaluator.Gene.MINUS.getValue(),  // -
            ChromosomeEvaluator.Gene.SIX.getValue(),   // 6
            ChromosomeEvaluator.Gene.MINUS.getValue()
            // 3 * 8 - 6 = 18
        ));

        final int result = ChromosomeEvaluator.evaluateChromosome(chromosome);

        assertEquals("Wrong result of evaluation", 18, result);
    }

    @Test
    public void toExpressionBasicCase() {
        // 1 + 5 * 6 = 36
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeEvaluator.Gene.ONE.getValue(),
            ChromosomeEvaluator.Gene.PLUS.getValue(),
            ChromosomeEvaluator.Gene.FIVE.getValue(),
            ChromosomeEvaluator.Gene.MULT.getValue(),
            ChromosomeEvaluator.Gene.SIX.getValue()
        ));

        final String result = ChromosomeEvaluator.toExpression(chromosome);

        assertEquals("Wrong result of evaluation", "1 + 5 * 6", result);
    }

    @Test
    public void toExpressionWithMessOfGenes() {
        final String chromosome = String.join("", Arrays.asList(
            "$",
            ChromosomeEvaluator.Gene.DIV.getValue(),
            ChromosomeEvaluator.Gene.ONE.getValue(),
            ChromosomeEvaluator.Gene.TWO.getValue(),
            ChromosomeEvaluator.Gene.THREE.getValue(), // 3
            ChromosomeEvaluator.Gene.PLUS.getValue(),
            "$",
            ChromosomeEvaluator.Gene.MULT.getValue(),  // *
            ChromosomeEvaluator.Gene.FIVE.getValue(),
            ChromosomeEvaluator.Gene.FOUR.getValue(),
            "$",
            ChromosomeEvaluator.Gene.EIGHT.getValue(), // 8
            ChromosomeEvaluator.Gene.MINUS.getValue(),  // -
            ChromosomeEvaluator.Gene.SIX.getValue(),   // 6
            ChromosomeEvaluator.Gene.MINUS.getValue()
            // 3 * 8 - 6 = 18
        ));

        final String result = ChromosomeEvaluator.toExpression(chromosome);

        assertEquals("Wrong result of evaluation", "3 * 8 - 6", result);
    }
}
