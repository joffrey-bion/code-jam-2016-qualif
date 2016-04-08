package org.hildan.codejam.problems.countingsheep;

import java.io.BufferedReader;
import java.io.IOException;

import org.hildan.codejam.io.CodeJamRunner;

/**
 * Code Jam 2016 Qualification Round. Problem A: "Counting Sheep".
 * <p>
 * Bleatrix Trotter the sheep has devised a strategy that helps her fall asleep faster. First, she picks a number N.
 * Then she starts naming N, 2 × N, 3 × N, and so on. Whenever she names a number, she thinks about all of the digits in
 * that number. She keeps track of which digits (0, 1, 2, 3, 4, 5, 6, 7, 8, and 9) she has seen at least once so far as
 * part of any number she has named. Once she has seen each of the ten digits at least once, she will fall asleep.
 * <p>
 * Bleatrix must start with N and must always name (i + 1) × N directly after i × N. For example, suppose that Bleatrix
 * picks N = 1692. She would count as follows:
 * <p>
 * N = 1692. Now she has seen the digits 1, 2, 6, and 9. 2N = 3384. Now she has seen the digits 1, 2, 3, 4, 6, 8, and 9.
 * 3N = 5076. Now she has seen all ten digits, and falls asleep.
 * <p>
 * What is the last number that she will name before falling asleep? If she will count forever, print INSOMNIA instead.
 */
public class CountingSheep extends CodeJamRunner {

    public CountingSheep() {
        super(CountingSheep::readCase, CountingSheep::findLastNumber);
    }

    private static int readCase(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static String findLastNumber(int startNumber) {
        if (startNumber == 0) {
            return "INSOMNIA";
        }
        int seen = digitMask(startNumber);
        int number = startNumber;
        while (seen != 0b1111111111) {
            number += startNumber;
            seen |= digitMask(number);
        }
        return String.valueOf(number);
    }

    private static int digitMask(int number) {
        int mask = 0;
        while (number > 0) {
            int digit = number % 10;
            mask |= 1 << digit;
            number = number / 10;
        }
        return mask;
    }
}
