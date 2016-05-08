package org.hildan.codejam.problems.closematch;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import org.hildan.codejam.io.CodeJamRunner;

/**
 * Code Jam 2016 Qualification Round. Problem B: "Close Match".
 * <p>
 * Problem
 * <p>
 * You are attending the most important game in sports history. The Oceania Coders are playing the Eurasia Jammers in
 * the Centrifugal Bumble-Puppy world finals. Unfortunately, you were sleep deprived from all the anticipation, so you
 * fell asleep during the game!
 * <p>
 * The scoreboard is currently displaying both scores, perhaps with one or more leading zeroes (because the scoreboard
 * displays a fixed number of digits). While you were asleep, some of the lights on the scoreboard were damaged by
 * strong ball hits, so one or more of the digits in one or both scores are not being displayed.
 * <p>
 * You think close games are more exciting, and you would like to imagine that the scores are as close as possible. Can
 * you fill in all of the missing digits in a way that minimizes the absolute difference between the scores? If there is
 * more than one way to attain the minimum absolute difference, choose the way that minimizes the Coders' score. If
 * there is more than one way to attain the minimum absolute difference while also minimizing the Coders' score, choose
 * the way that minimizes the Jammers' score.
 * <p>
 * Input
 * <p>
 * The first line of the input gives the number of test cases, T. T cases follow. Each case consists of one line with
 * two non-empty strings C and J of the same length, composed only of decimal digits and question marks, representing
 * the score as you see it for the Coders and the Jammers, respectively. There will be at least one question mark in
 * each test case.
 * <p>
 * Output
 * <p>
 * For each test case, output one line containing Case #x: c j, where x is the test case number (starting from 1), c is
 * C with the question marks replaced by digits, and j is J with the question marks replaced by digits, such that the
 * absolute difference between the integers represented by c and j is minimized. If there are multiple solutions with
 * the same absolute difference, use the one in which c is minimized; if there are multiple solutions with the same
 * absolute difference and the same value of c, use the one in which j is minimized.
 * <p>
 * Limits
 * <p>
 * 1 ≤ T ≤ 200. C and J have the same length. Small dataset
 * <p>
 * 1 ≤ the length of C and J ≤ 3.
 * <p>
 * Large dataset
 * <p>
 * 1 ≤ the length of C and J ≤ 18.
 */
public class CloseMatch extends CodeJamRunner {

    public CloseMatch() {
        super(CloseMatch::readCase, CloseMatch::getCompletedScores);
    }

    private static Score readCase(BufferedReader reader) throws IOException {
        String[] scores = reader.readLine().split(" ");
        return new Score(scores[0], scores[1]);
    }

    private static String getCompletedScores(Score score) {
        int missingDigitsCount = score.missingDigitsCount();
        long max = (long) Math.pow(10, missingDigitsCount);
        long smallestDiff = Long.MAX_VALUE;
        long[] bestScores = new long[2];
        for (long l = 0; l <= max; l++) {
            long[] scores = score.completeWith(l);
            long diff = Math.abs(scores[0] - scores[1]);
            if (diff < smallestDiff) {
                System.out.println("better diff found " + diff + " for scores " + Arrays.toString(scores));
                bestScores = scores;
                smallestDiff = diff;
            }
        }
        return formatScores(bestScores, score.scoreLength());
    }

    private static String formatScores(long[] scores, int length) {
        return String.format("%0" + length + "d %0" + length + "d", scores[0], scores[1]);
    }
}
