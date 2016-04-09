package org.hildan.codejam.problems.coinjam;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

public class RandomCandidateGenerator implements Supplier<String> {

    private final Random random = new Random();

    private final Set<Integer> alreadyGenerated = new HashSet<>();

    private final int midPartLength;

    private final int upperBound;

    public RandomCandidateGenerator(int length) {
        midPartLength = length - 2; // without the ones at each end
        upperBound = 1 << midPartLength;
    }

    public String get() {
        int binaryNumber = random.nextInt(upperBound);
        if (alreadyGenerated.contains(binaryNumber)) {
            // duplicate found, generate a new one
            return get();
        }
        String binaryStr = Integer.toBinaryString(binaryNumber);
        return String.format("1%" + midPartLength + "s1", binaryStr).replace(' ', '0');
    }
}
