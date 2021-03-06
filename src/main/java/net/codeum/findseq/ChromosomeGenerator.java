package net.codeum.findseq;

import java.util.Random;

public class ChromosomeGenerator {

    public static String generate(int targetValue) {
        final Random random = new Random();
        int length = 0;

        if (targetValue > 0) {
            length = random.nextInt(getMaxChromosomeValue(targetValue));
        }

        if (length == 0) {
            length = 1;
        }

        final StringBuilder result = new StringBuilder("");

        for (int i = 0; i < length; i++) {
            final int index = random.nextInt(ChromosomeEvaluator.Gene.values().length - 1);
            result.append(ChromosomeEvaluator.Gene.values()[index].getValue());
        }

        return result.toString();
    }

    private static int getMaxChromosomeValue(int targetValue) {
        return 2 * targetValue - 1 ;
    }
}
