package org.hildan.codejam.problems.reversewords;

import java.io.BufferedReader;
import java.io.IOException;

import org.hildan.codejam.io.CodeJamRunner;

/**
 * Code Jam 2010 Qualification Round Africa
 */
public class ReverseWords extends CodeJamRunner {

    public ReverseWords() {
        super(ReverseWords::readArrayOfWords, ReverseWords::reverse);
    }

    private static String[] readArrayOfWords(BufferedReader reader) throws IOException {
        return reader.readLine().split(" ");
    }

    private static String reverse(String[] input) {
        StringBuilder sb = new StringBuilder();
        for (int i = input.length - 1; i >= 0; i--) {
            sb.append(input[i]);
            sb.append(' ');
        }
        if (sb.length() > 0) {
            // remove last space
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
