package org.hildan.codejam.problems.coinjam;

import java.math.BigInteger;

class PrimeMath {

    private static final BigInteger TWO = new BigInteger("2");

    private static final BigInteger THREE = new BigInteger("3");

    private static final BigInteger DIVISOR_LIMIT = new BigInteger("100");

    public static BigInteger smallestDivisor(BigInteger number) throws NumberIsPrimeException {
        // special case for 2, then we only tackle odd numbers
        if (number.remainder(TWO).equals(BigInteger.ZERO)) {
            return TWO;
        }

        BigInteger divisor = THREE;
        while (divisor.compareTo(DIVISOR_LIMIT) <= 0) {
            if (number.remainder(divisor).equals(BigInteger.ZERO)) {
                return divisor;
            }
            divisor = divisor.add(TWO);
        }
        throw new NumberIsPrimeException();
    }

    public static class NumberIsPrimeException extends RuntimeException {
    }
}
