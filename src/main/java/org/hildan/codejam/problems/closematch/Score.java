package org.hildan.codejam.problems.closematch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Score {

    private final List<Integer> codersMissingDigitsPositions;

    private final List<Integer> jammersMissingDigitsPositions;

    private final char[] codersScoreStr;

    private final char[] jammersScoreStr;

    public Score(String codersScoreIncomplete, String jammersScoreIncomplete) {
        this.codersMissingDigitsPositions = findMissingDigitsPositions(codersScoreIncomplete);
        this.jammersMissingDigitsPositions = findMissingDigitsPositions(jammersScoreIncomplete);
        this.codersScoreStr = codersScoreIncomplete.toCharArray();
        this.jammersScoreStr = jammersScoreIncomplete.toCharArray();

        List<Integer> posToRemove = new ArrayList<>(codersMissingDigitsPositions.size());
        for (Integer pos : codersMissingDigitsPositions) {
            if (!jammersMissingDigitsPositions.contains(pos)) {
                codersScoreStr[pos] = jammersScoreStr[pos];
                posToRemove.add(pos);
            }
        }
        codersMissingDigitsPositions.removeAll(posToRemove);
        posToRemove.clear();
        for (Integer pos : jammersMissingDigitsPositions) {
            if (!codersMissingDigitsPositions.contains(pos)) {
                jammersScoreStr[pos] = codersScoreStr[pos];
                posToRemove.add(pos);
            }
        }

    }

    public int scoreLength() {
        return codersScoreStr.length;
    }

    public int missingDigitsCount() {
        return codersMissingDigitsPositions.size() + jammersMissingDigitsPositions.size();
    }

    public long[] completeWith(long number) {
        Stack<Character> digits = digits(number);
        for (int i = digits.size(); i < missingDigitsCount(); i++) {
            digits.push('0');
        }
        for (Integer pos : codersMissingDigitsPositions) {
            codersScoreStr[pos] = digits.pop();
        }
        for (Integer pos : jammersMissingDigitsPositions) {
            jammersScoreStr[pos] = digits.pop();
        }
        long codersScore = Long.parseLong(String.valueOf(codersScoreStr));
        long jammersScore = Long.parseLong(String.valueOf(jammersScoreStr));
        return new long[]{codersScore, jammersScore};
    }

    private static Stack<Character> digits(long number) {
        Stack<Character> digits = new Stack<>();
        while (number > 0) {
            int digit = (int) (number % 10);
            digits.push(String.valueOf(digit).charAt(0));
            number = number / 10;
        }
        return digits;
    }

    private static List<Integer> findMissingDigitsPositions(String score) {
        List<Integer> missingPositions = new ArrayList<>();
        for (int i = 0; i < score.length(); i++) {
            if (score.charAt(i) == '?') {
                missingPositions.add(i);
            }
        }
        return missingPositions;
    }
}
