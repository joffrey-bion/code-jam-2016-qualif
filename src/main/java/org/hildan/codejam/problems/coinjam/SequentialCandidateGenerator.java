package org.hildan.codejam.problems.coinjam;

import java.util.function.Supplier;

public class SequentialCandidateGenerator implements Supplier<String> {

    private final int midPartLength;

    private int current = 0;

    public SequentialCandidateGenerator(int length) {
        this.midPartLength = length - 2; // without the ones at each end
    }

    public String get() {
        String binary = Integer.toBinaryString(current++);
        return String.format("1%" + midPartLength + "s1", binary).replace(' ', '0');
    }
}
