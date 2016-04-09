package org.hildan.codejam.problems.coinjam;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class JamCoinGenerator implements Supplier<JamCoin> {

    private final Supplier<String> candidatesGenerator;

    public JamCoinGenerator(Supplier<String> candidatesGenerator) {
        this.candidatesGenerator = candidatesGenerator;
    }

    public JamCoin get() {
        return Stream.generate(candidatesGenerator)
                     .map(JamCoin::fromCandidate)
                     .filter(Objects::nonNull)
                     .findFirst()
                     .orElse(null);
    }
}
