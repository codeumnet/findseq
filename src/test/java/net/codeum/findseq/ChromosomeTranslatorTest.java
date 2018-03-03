package net.codeum.findseq;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChromosomeTranslatorTest {

    @Test
    public void translateSimpleCase() {
        // 1 + 5 * 6 = 36
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.ONE.getValue(),
            ChromosomeTranslator.Gene.PLUS.getValue(),
            ChromosomeTranslator.Gene.FIVE.getValue(),
            ChromosomeTranslator.Gene.MULT.getValue(),
            ChromosomeTranslator.Gene.SIX.getValue()
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", 36, result);
    }

    @Test
    public void translateAllOperationsAndDigitsIncluded() {
        // 9 + 8 - 7 * 6 / 5 + 4 - 3 * 2 - 1 / 5 = 5
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.NINE.getValue(),
            ChromosomeTranslator.Gene.PLUS.getValue(),
            ChromosomeTranslator.Gene.EIGHT.getValue(),
            ChromosomeTranslator.Gene.MINUS.getValue(),
            ChromosomeTranslator.Gene.SEVEN.getValue(),
            ChromosomeTranslator.Gene.MULT.getValue(),
            ChromosomeTranslator.Gene.SIX.getValue(),
            ChromosomeTranslator.Gene.DIV.getValue(),
            ChromosomeTranslator.Gene.FIVE.getValue(),
            ChromosomeTranslator.Gene.PLUS.getValue(),
            ChromosomeTranslator.Gene.FOUR.getValue(),
            ChromosomeTranslator.Gene.MINUS.getValue(),
            ChromosomeTranslator.Gene.THREE.getValue(),
            ChromosomeTranslator.Gene.MULT.getValue(),
            ChromosomeTranslator.Gene.TWO.getValue(),
            ChromosomeTranslator.Gene.MINUS.getValue(),
            ChromosomeTranslator.Gene.ONE.getValue(),
            ChromosomeTranslator.Gene.DIV.getValue(),
            ChromosomeTranslator.Gene.FIVE.getValue()
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", 5, result);
    }

    @Test
    public void translateCaseWithSomeWrongGenes() {
        // 1 + 5 * 6 = 36
        final String chromosome = String.join("", Arrays.asList(
            "1111",
            ChromosomeTranslator.Gene.ONE.getValue(),
            ChromosomeTranslator.Gene.PLUS.getValue(),
            "1110",
            ChromosomeTranslator.Gene.FIVE.getValue(),
            "1111",
            "1110",
            ChromosomeTranslator.Gene.MULT.getValue(),
            ChromosomeTranslator.Gene.SIX.getValue()
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", 36, result);
    }

    @Test
    public void translateCaseWithOneGene() {
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.ONE.getValue()
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", 1, result);
    }

    @Test
    public void translateCaseWithOneOpGene() {
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.PLUS.getValue()
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", Integer.MAX_VALUE, result);
    }

    @Test
    public void translateCaseWithMessOfGenes() {
        final String chromosome = String.join("", Arrays.asList(
            "1111",
            ChromosomeTranslator.Gene.DIV.getValue(),
            ChromosomeTranslator.Gene.ONE.getValue(),
            ChromosomeTranslator.Gene.TWO.getValue(),
            ChromosomeTranslator.Gene.THREE.getValue(), // 3
            ChromosomeTranslator.Gene.PLUS.getValue(),
            "1111",
            ChromosomeTranslator.Gene.MULT.getValue(),  // *
            ChromosomeTranslator.Gene.FIVE.getValue(),
            ChromosomeTranslator.Gene.FOUR.getValue(),
            "1111",
            ChromosomeTranslator.Gene.EIGHT.getValue(), // 8
            ChromosomeTranslator.Gene.MINUS.getValue(),  // -
            ChromosomeTranslator.Gene.SIX.getValue(),   // 6
            ChromosomeTranslator.Gene.MINUS.getValue()
            // 3 * 8 - 6 = 18
        ));

        final int result = ChromosomeTranslator.translateChromosome(chromosome);

        assertEquals("Wrong result of translation", 18, result);
    }

    @Test
    public void toExpressionBasicCase() {
        // 1 + 5 * 6 = 36
        final String chromosome = String.join("", Arrays.asList(
            ChromosomeTranslator.Gene.ONE.getValue(),
            ChromosomeTranslator.Gene.PLUS.getValue(),
            ChromosomeTranslator.Gene.FIVE.getValue(),
            ChromosomeTranslator.Gene.MULT.getValue(),
            ChromosomeTranslator.Gene.SIX.getValue()
        ));

        final String result = ChromosomeTranslator.toExpression(chromosome);

        assertEquals("Wrong result of translation", "1 + 5 * 6", result);
    }

    @Test
    public void toExpressionWithMessOfGenes() {
        final String chromosome = String.join("", Arrays.asList(
            "1111",
            ChromosomeTranslator.Gene.DIV.getValue(),
            ChromosomeTranslator.Gene.ONE.getValue(),
            ChromosomeTranslator.Gene.TWO.getValue(),
            ChromosomeTranslator.Gene.THREE.getValue(), // 3
            ChromosomeTranslator.Gene.PLUS.getValue(),
            "1111",
            ChromosomeTranslator.Gene.MULT.getValue(),  // *
            ChromosomeTranslator.Gene.FIVE.getValue(),
            ChromosomeTranslator.Gene.FOUR.getValue(),
            "1111",
            ChromosomeTranslator.Gene.EIGHT.getValue(), // 8
            ChromosomeTranslator.Gene.MINUS.getValue(),  // -
            ChromosomeTranslator.Gene.SIX.getValue(),   // 6
            ChromosomeTranslator.Gene.MINUS.getValue()
            // 3 * 8 - 6 = 18
        ));

        final String result = ChromosomeTranslator.toExpression(chromosome);

        assertEquals("Wrong result of translation", "3 * 8 - 6", result);
    }
}
