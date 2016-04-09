package org.hildan.codejam.problems.fractiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FractilesVisual {

    public static void main(String[] args) {
        int K = 3;
        int C = 2;
        for (String seq : generateSequencesWithOneGold(K)) {
            System.out.println(iterate(seq, C));
        }
    }

    private static String generateLead(int K) {
        return IntStream.range(0, K).mapToObj(i -> "L").collect(Collectors.joining());
    }

    private static List<String> generateSequencesWithOneGold(int K) {
        String leadSequence = generateLead(K);
        List<String> sequences = new ArrayList<>(K);
        for (int i = 0; i < K; i++) {
            StringBuilder sb = new StringBuilder(leadSequence);
            sb.setCharAt(i, 'G');
            sequences.add(sb.toString());
        }
        return sequences;
    }

    private static String iterate(String initialSequence, int C) {
        String currentSequence = initialSequence;
        for (int c = 1; c < C; c++) {
            currentSequence = transform(initialSequence, currentSequence);
        }
        return currentSequence;
    }

    private static String transform(String initialSequence, String currentSequence) {
        StringBuilder sb = new StringBuilder(currentSequence.length() * initialSequence.length());
        for (int i = 0; i < currentSequence.length(); i++) {
            if (currentSequence.charAt(i) == 'G') {
                //noinspection ReplaceAllDot
                sb.append(initialSequence.replaceAll(".", "G"));
            } else {
                sb.append(initialSequence);
            }
        }
        return sb.toString();
    }
}
