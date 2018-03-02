package net.codeum.findseq;

import org.junit.Test;

public class AlgorithmTest {

    @Test
    public void testAlgorithm() {
        Algorithm.run(0, AlgorithmParams.get());

        Algorithm.run(9, AlgorithmParams.get());

        Algorithm.run(47, AlgorithmParams.get());
    }

}
