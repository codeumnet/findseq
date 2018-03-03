package net.codeum.findseq;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ChromosomeTranslator {

    enum Gene {
        ZERO(0),
        ONE( 1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        PLUS("+"),
        MINUS("-"),
        MULT("*"),
        DIV("/"),
        NONE();

        private final String value;
        private final int intValue;

        Gene() {
            this.value = null;
            this.intValue = -1;
        }

        Gene(String value) {
            this.value = value;
            this.intValue = -1;
        }

        Gene(int value) {
            this.intValue = value;
            this.value = String.valueOf(value);
        }

        public static Gene getByValue(String value) {
            if (value == null) {
                return Gene.NONE;
            }

            for (Gene gene : values()) {
                if (value.equalsIgnoreCase(gene.value)) {
                    return gene;
                }
            }

            return Gene.NONE;
        }

        public String getValue() {
            return value;
        }

        public boolean isDigit() {
            return this.intValue > -1;
        }

        public int getIntValue() {
            return intValue;
        }
    }

    public static int translateChromosome(String chromosome) {
        final List<Gene> genes = toGenes(chromosome);

        if (genes.isEmpty()) {
            return Integer.MAX_VALUE;
        }

        return calculateResult(genes);
    }

    public static String toExpression(String chromosome) {
        return String.join(" ",
            toGenes(chromosome).stream()
                .map(Gene::getValue)
                .collect(Collectors.toList())
        );
    }

    private static List<Gene> toGenes(String chromosome) {
        final List<Gene> genes = new LinkedList<>();
        boolean expectDigit = true;
        for (int i = 0; i < chromosome.length(); i++) {
            final String geneCode = Character.toString(chromosome.charAt(i));
            final Gene gene = Gene.getByValue(geneCode);

            if (Gene.NONE.equals(gene)) {
                continue;
            }

            if (!genes.isEmpty() && Gene.ZERO.equals(gene) && Gene.DIV.equals(genes.get(genes.size() - 1))) {
                continue;
            }

            if (expectDigit && gene.isDigit()) {
                genes.add(gene);
                expectDigit = false;
                continue;
            }

            if (!expectDigit && !gene.isDigit()) {
                genes.add(gene);
                expectDigit = true;
                continue;
            }

            // rewrite the last gene
            if (!genes.isEmpty()) {
                genes.set(genes.size() - 1, gene);
            }
        }

        if (!genes.isEmpty()
            && !genes.get(genes.size() - 1).isDigit()) {
            genes.remove(genes.size() - 1);
        }

        return genes;
    }

    private static int calculateResult(List<Gene> genes) {
        Gene nextOp = Gene.PLUS;
        int result = 0;
        for (Gene gene : genes) {
            if (gene.isDigit()) {
                result = performOp(result, gene.getIntValue(), nextOp);
            } else {
                nextOp = gene;
            }
        }
        return result;
    }

    private static int performOp(int left, int right, Gene operation) {
        int result = left;

        switch (operation) {
            case PLUS:
                result = left + right;
                break;
            case MINUS:
                result = left - right;
                break;
            case MULT:
                result = left * right;
                break;
            case DIV:
                if (right > 0) {
                    result = left / right;
                } else {
                    result = Integer.MAX_VALUE;
                }
                break;
        }

        return result;
    }
}
