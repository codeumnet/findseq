package net.codeum.findseq;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChromosomeTranslatorTest {

    @Test
    public void translateSimpleCase() {
        // 1 + 5 * 6 = 36
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.ONE.getCode(),
            ChromosomeTranslator.Gene.PLUS.getCode(),
            ChromosomeTranslator.Gene.FIVE.getCode(),
            ChromosomeTranslator.Gene.MULT.getCode(),
            ChromosomeTranslator.Gene.SIX.getCode()
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", 36, result);
    }

    @Test
    public void translateAllOperationsAndDigitsIncluded() {
        // 9 + 8 - 7 * 6 / 5 + 4 - 3 * 2 - 1 / 5 = 5
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.NINE.getCode(),
            ChromosomeTranslator.Gene.PLUS.getCode(),
            ChromosomeTranslator.Gene.EIGHT.getCode(),
            ChromosomeTranslator.Gene.MINUS.getCode(),
            ChromosomeTranslator.Gene.SEVEN.getCode(),
            ChromosomeTranslator.Gene.MULT.getCode(),
            ChromosomeTranslator.Gene.SIX.getCode(),
            ChromosomeTranslator.Gene.DIV.getCode(),
            ChromosomeTranslator.Gene.FIVE.getCode(),
            ChromosomeTranslator.Gene.PLUS.getCode(),
            ChromosomeTranslator.Gene.FOUR.getCode(),
            ChromosomeTranslator.Gene.MINUS.getCode(),
            ChromosomeTranslator.Gene.THREE.getCode(),
            ChromosomeTranslator.Gene.MULT.getCode(),
            ChromosomeTranslator.Gene.TWO.getCode(),
            ChromosomeTranslator.Gene.MINUS.getCode(),
            ChromosomeTranslator.Gene.ONE.getCode(),
            ChromosomeTranslator.Gene.DIV.getCode(),
            ChromosomeTranslator.Gene.FIVE.getCode()
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", 5, result);
    }

    @Test
    public void translateCaseWithSomeWrongGenes() {
        // 1 + 5 * 6 = 36
        final String chromosome = String.join("", Arrays.asList(
            "1111",
            ChromosomeTranslator.Gene.ONE.getCode(),
            ChromosomeTranslator.Gene.PLUS.getCode(),
            "1110",
            ChromosomeTranslator.Gene.FIVE.getCode(),
            "1111",
            "1110",
            ChromosomeTranslator.Gene.MULT.getCode(),
            ChromosomeTranslator.Gene.SIX.getCode()
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", 36, result);
    }

    @Test
    public void translateCaseWithOneGene() {
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.ONE.getCode()
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", 1, result);
    }

    @Test
    public void translateCaseWithOneOpGene() {
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.PLUS.getCode()
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", Integer.MAX_VALUE, result);
    }

    @Test
    public void translateCaseWithMessOfGenes() {
        final String chromosome = String.join("", Arrays.asList(
            "1111",
            ChromosomeTranslator.Gene.DIV.getCode(),
            ChromosomeTranslator.Gene.ONE.getCode(),
            ChromosomeTranslator.Gene.TWO.getCode(),
            ChromosomeTranslator.Gene.THREE.getCode(), // 3
            ChromosomeTranslator.Gene.PLUS.getCode(),
            "1111",
            ChromosomeTranslator.Gene.MULT.getCode(),  // *
            ChromosomeTranslator.Gene.FIVE.getCode(),
            ChromosomeTranslator.Gene.FOUR.getCode(),
            "1111",
            ChromosomeTranslator.Gene.EIGHT.getCode(), // 8
            ChromosomeTranslator.Gene.MINUS.getCode(),  // -
            ChromosomeTranslator.Gene.SIX.getCode(),   // 6
            ChromosomeTranslator.Gene.MINUS.getCode()
            // 3 * 8 - 6 = 18
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", 18, result);
    }

    @Test
    public void toExpressionBasicCase() {
        // 1 + 5 * 6 = 36
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.ONE.getCode(),
            ChromosomeTranslator.Gene.PLUS.getCode(),
            ChromosomeTranslator.Gene.FIVE.getCode(),
            ChromosomeTranslator.Gene.MULT.getCode(),
            ChromosomeTranslator.Gene.SIX.getCode()
        ));

        final String result = ChromosomeTranslator.toExpression(chromosome);

        assertEquals("Wrong result of translation", "1 + 5 * 6", result);
    }

    @Test
    public void toExpressionWithMessOfGenes() {
        final String chromosome = String.join("", Arrays.asList(
            "1111",
            ChromosomeTranslator.Gene.DIV.getCode(),
            ChromosomeTranslator.Gene.ONE.getCode(),
            ChromosomeTranslator.Gene.TWO.getCode(),
            ChromosomeTranslator.Gene.THREE.getCode(), // 3
            ChromosomeTranslator.Gene.PLUS.getCode(),
            "1111",
            ChromosomeTranslator.Gene.MULT.getCode(),  // *
            ChromosomeTranslator.Gene.FIVE.getCode(),
            ChromosomeTranslator.Gene.FOUR.getCode(),
            "1111",
            ChromosomeTranslator.Gene.EIGHT.getCode(), // 8
            ChromosomeTranslator.Gene.MINUS.getCode(),  // -
            ChromosomeTranslator.Gene.SIX.getCode(),   // 6
            ChromosomeTranslator.Gene.MINUS.getCode()
            // 3 * 8 - 6 = 18
        ));

        final String result = ChromosomeTranslator.toExpression(chromosome);

        assertEquals("Wrong result of translation", "3 * 8 - 6", result);
    }
}
