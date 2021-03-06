package org.hildan.codejam.problems.coinjam;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.hildan.codejam.io.CodeJamRunner;

/**
 * Code Jam 2016 Qualification Round. Problem C: "Coin Jam"
 * <p>
 * A jamcoin is a string of N ≥ 2 digits with the following properties:
 * <p>
 * Every digit is either 0 or 1. The first digit is 1 and the last digit is 1. If you interpret the string in any base
 * between 2 and 10, inclusive, the resulting number is not prime. Not every string of 0s and 1s is a jamcoin. For
 * example, 101 is not a jamcoin; its interpretation in base 2 is 5, which is prime. But the string 1001 is a jamcoin:
 * in bases 2 through 10, its interpretation is 9, 28, 65, 126, 217, 344, 513, 730, and 1001, respectively, and none of
 * those is prime.
 * <p>
 * We hear that there may be communities that use jamcoins as a form of currency. When sending someone a jamcoin, it is
 * polite to prove that the jamcoin is legitimate by including a nontrivial divisor of that jamcoin's interpretation in
 * each base from 2 to 10. (A nontrivial divisor for a positive integer K is some positive integer other than 1 or K
 * that evenly divides K.) For convenience, these divisors must be expressed in base 10.
 * <p>
 * For example, for the jamcoin 1001 mentioned above, a possible set of nontrivial divisors for the base 2 through 10
 * interpretations of the jamcoin would be: 3, 7, 5, 6, 31, 8, 27, 5, and 77, respectively.
 * <p>
 * Can you produce J different jamcoins of length N, along with proof that they are legitimate?
 */
public class CoinJam extends CodeJamRunner {

    public CoinJam() {
        super(CoinJam::readCase, CoinJam::generateOutput);
    }

    private static Constraints readCase(BufferedReader reader) throws IOException {
        String[] input = reader.readLine().split(" ");
        int jamCoinsLength = Integer.parseInt(input[0]);
        int nJamCoins = Integer.parseInt(input[1]);
        return new Constraints(nJamCoins, jamCoinsLength);
    }

    private static String generateOutput(Constraints constraints) {
        Supplier<String> candidatesGenerator = new SequentialCandidateGenerator(constraints.jamCoinLength);
        JamCoinGenerator generator = new JamCoinGenerator(candidatesGenerator);
        StringBuilder sb = new StringBuilder(constraints.numberOfJamCoins * (constraints.jamCoinLength + 10));
        sb.append("\n"); // to leave Case #x on its own line
        for (int i = 0; i < constraints.numberOfJamCoins; i++) {
            String outputLine = jamCoinToOutput(generator.get());
            System.out.println(outputLine);
            sb.append(outputLine);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String jamCoinToOutput(JamCoin jamCoin) {
        return jamCoin.toString() + " " + Arrays.stream(jamCoin.getDivisors())
                                                .map(BigInteger::toString)
                                                .collect(Collectors.joining(" "));
    }
}
