package org.hildan.codejam.problems.coinjam;

import java.math.BigInteger;
import java.util.stream.IntStream;

import org.hildan.codejam.problems.coinjam.PrimeMath.NumberIsPrimeException;

class JamCoin {

    private final String jamCoin;

    private final BigInteger[] divisors;

    public JamCoin(String jamCoin, BigInteger[] divisors) {
        this.jamCoin = jamCoin;
        this.divisors = divisors;
    }

    /**
     * Returns null if not a JamCoin.
     *
     * @param jamCoinCandidate
     *         the candidate to analyze
     * @return the created JamCoin, or null if the candidate was not a JamCoin
     */
    static JamCoin fromCandidate(String jamCoinCandidate) {
        try {
            BigInteger[] divisors = IntStream.rangeClosed(2, 10)
                                             .mapToObj(base -> new BigInteger(jamCoinCandidate, base))
                                             .map(PrimeMath::smallestDivisor)
                                             .toArray(BigInteger[]::new);
            return new JamCoin(jamCoinCandidate, divisors);
        } catch (NumberIsPrimeException e) {
            return null;
        }
    }

    public BigInteger[] getDivisors() {
        return divisors;
    }

    public String toString() {
        return jamCoin;
    }
}
