package org.hildan.codejam.problems.gettingdigits;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.hildan.codejam.io.CodeJamRunner;

/**
 * Code Jam 2016 Qualification Round. Problem A: "Getting the Digits".
 * <p>
 * You just made a new friend at an international puzzle conference, and you asked for a way to keep in touch. You found
 * the following note slipped under your hotel room door the next day:
 * <p>
 * "Salutations, new friend! I have replaced every digit of my phone number with its spelled-out uppercase English
 * representation ("ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE" for the digits 0
 * through 9, in that order), and then reordered all of those letters in some way to produce a string S. It's up to you
 * to use S to figure out how many digits are in my phone number and what those digits are, but I will tell you that my
 * phone number consists of those digits in nondecreasing order. Give me a call... if you can!"
 * <p>
 * You would to like to call your friend to tell him that this is an obnoxious way to give someone a phone number, but
 * you need the phone number to do that! What is it?
 * <p>
 * Input
 * <p>
 * The first line of the input gives the number of test cases, T. T test cases follow. Each consists of one line with a
 * string S of uppercase English letters.
 * <p>
 * Output
 * <p>
 * For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is
 * a string of digits: the phone number.
 * <p>
 * Limits
 * <p>
 * 1 ≤ T ≤ 100. A unique answer is guaranteed to exist. Small dataset
 * <p>
 * 3 ≤ length of S ≤ 20.
 * <p>
 * Large dataset
 * <p>
 * 3 ≤ length of S ≤ 2000.
 */
public class GettingTheDigits extends CodeJamRunner {

    private static final String[] NUMS =
            {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};

    private static final Map<Integer, Map<Character, Integer>> NUM_FREQS;

    static {
        NUM_FREQS = new HashMap<>();
        for (int i = 0; i < NUMS.length; i++) {
            NUM_FREQS.put(i, countOccurrences(NUMS[i]));
        }
        System.out.println(NUM_FREQS);
    }

    public GettingTheDigits() {
        super(GettingTheDigits::readCase, GettingTheDigits::getPhoneNumber);
    }

    private static String readCase(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    private static String getPhoneNumber(String mixedLetters) {
        Map<Character, Integer> frequencies = countOccurrences(mixedLetters);
        int[] digitsCount = findDigits(frequencies);
        return createString(digitsCount);
    }

    private static Map<Character, Integer> countOccurrences(String s) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer freq = frequencies.get(c);
            if (freq == null) {
                freq = 0;
            }
            frequencies.put(c, freq + 1);
        }
        return frequencies;
    }

    private static int[] findDigits(Map<Character, Integer> frequencies) {
        int[] digitsCount = new int[10];
        digitsCount[0] = removeObviousDigit(0, 'Z', frequencies);
        digitsCount[2] = removeObviousDigit(2, 'W', frequencies);
        digitsCount[4] = removeObviousDigit(4, 'U', frequencies);
        digitsCount[6] = removeObviousDigit(6, 'X', frequencies);
        digitsCount[8] = removeObviousDigit(8, 'G', frequencies);

        digitsCount[5] = removeObviousDigit(5, 'F', frequencies); // removed FOUR already
        digitsCount[7] = removeObviousDigit(7, 'V', frequencies); // removed FIVE already
        digitsCount[1] = removeObviousDigit(1, 'O', frequencies); // removed FOUR already
        digitsCount[3] = removeObviousDigit(3, 'H', frequencies); // removed EIGHT already

        Integer freq9 = frequencies.get('E');
        digitsCount[9] = freq9 == null ? 0 : freq9; // count remaining Es for the 9s

        return digitsCount;
    }

    private static int removeObviousDigit(int digit, char definingChar, Map<Character, Integer> frequencies) {
        Integer freq = frequencies.get(definingChar);
        if (freq == null) {
            return 0;
        }
        removeOccurrences(digit, freq, frequencies);
        return freq;
    }

    private static void removeOccurrences(int digit, int count, Map<Character, Integer> frequencies) {
        for (Entry<Character, Integer> freq : NUM_FREQS.get(digit).entrySet()) {
            Integer freqInInput = frequencies.get(freq.getKey());
            if (freqInInput == null) {
                continue;
            }
            int newCount = freqInInput - freq.getValue() * count;
            if (newCount < 0) {
                throw new RuntimeException("oops, got below 0 for char " + freq.getKey() + " in digit " + digit);
            }
            frequencies.put(freq.getKey(), newCount);
        }
    }

    private static String createString(int[] digitsCount) {
        StringBuilder sb = new StringBuilder();
        for (int digit = 0; digit < 10; digit++) {
            for (int i = 0; i < digitsCount[digit]; i++) {
                sb.append(String.valueOf(digit));
            }
        }
        return sb.toString();
    }
}
