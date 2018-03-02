package net.codeum.findseq;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ChromosomeTranslator {

    enum Gene {
        ZERO("0000", 0),
        ONE("0001", 1),
        TWO("0010", 2),
        THREE("0011", 3),
        FOUR("0100", 4),
        FIVE("0101", 5),
        SIX("0110", 6),
        SEVEN("0111", 7),
        EIGHT("1000", 8),
        NINE("1001", 9),
        PLUS("1010", "+"),
        MINUS("1011", "-"),
        MULT("1100", "*"),
        DIV("1101", "/"),
        NONE();

        private final String code;
        private final String value;
        private final int intValue;

        Gene() {
            this.code = null;
            this.value = null;
            this.intValue = -1;
        }

        Gene(String code, String value) {
            this.code = code;
            this.value = value;
            this.intValue = -1;
        }

        Gene(String code, int value) {
            this.code = code;
            this.intValue = value;
            this.value = String.valueOf(value);
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

        public String getCode() {
            return code;
        }

        public static Gene getByCode(String code) {
            if (code == null) {
                return Gene.NONE;
            }

            for (Gene gene : values()) {
                if (code.equalsIgnoreCase(gene.code)) {
                    return gene;
                }
            }

            return Gene.NONE;
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
        for (int i = 0; i <= chromosome.length() - 4; i += 4) {
            final String geneCode = chromosome.substring(i, i + 4);
            final Gene gene = Gene.getByCode(geneCode);

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
