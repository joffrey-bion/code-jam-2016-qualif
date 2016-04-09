package org.hildan.codejam.problems.revengeofpancakes;

import java.io.BufferedReader;
import java.io.IOException;

import org.hildan.codejam.io.CodeJamRunner;

/**
 * Code Jam 2016 Qualification Round. Problem B: "Revenge of the Pancakes".
 * <p>
 * The Infinite House of Pancakes has just introduced a new kind of pancake! It has a happy face made of chocolate chips
 * on one side (the "happy side"), and nothing on the other side (the "blank side").
 * <p>
 * You are the head waiter on duty, and the kitchen has just given you a stack of pancakes to serve to a customer. Like
 * any good pancake server, you have X-ray pancake vision, and you can see whether each pancake in the stack has the
 * happy side up or the blank side up. You think the customer will be happiest if every pancake is happy side up when
 * you serve them.
 * <p>
 * You know the following maneuver: carefully lift up some number of pancakes (possibly all of them) from the top of the
 * stack, flip that entire group over, and then put the group back down on top of any pancakes that you did not lift up.
 * When flipping a group of pancakes, you flip the entire group in one motion; you do not individually flip each
 * pancake. Formally: if we number the pancakes 1, 2, ..., N from top to bottom, you choose the top i pancakes to flip.
 * Then, after the flip, the stack is i, i-1, ..., 2, 1, i+1, i+2, ..., N. Pancakes 1, 2, ..., i now have the opposite
 * side up, whereas pancakes i+1, i+2, ..., N have the same side up that they had up before.
 * <p>
 * For example, let's denote the happy side as + and the blank side as -. Suppose that the stack, starting from the top,
 * is --+-. One valid way to execute the maneuver would be to pick up the top three, flip the entire group, and put them
 * back down on the remaining fourth pancake (which would stay where it is and remain unchanged). The new state of the
 * stack would then be -++-. The other valid ways would be to pick up and flip the top one, the top two, or all four. It
 * would not be valid to choose and flip the middle two or the bottom one, for example; you can only take some number
 * off the top.
 * <p>
 * You will not serve the customer until every pancake is happy side up, but you don't want the pancakes to get cold, so
 * you have to act fast! What is the smallest number of times you will need to execute the maneuver to get all the
 * pancakes happy side up, if you make optimal choices?
 */
public class RevengeOfPancakes extends CodeJamRunner {

    public RevengeOfPancakes() {
        super(RevengeOfPancakes::countOperations);
    }

    private static String countOperations(BufferedReader reader) throws IOException {
        String inputStack = reader.readLine();
        int numberOfRuns = countRuns(inputStack);
        boolean endsWithPlus = inputStack.charAt(inputStack.length() - 1) == '+';
        return countOperations(numberOfRuns, endsWithPlus).toString();
    }

    private static int countRuns(String stack) {
        int runsCount = 0;
        Boolean lastIsPlus = null;
        for (char c : stack.toCharArray()) {
            boolean isPlus = c == '+';
            if (lastIsPlus == null || lastIsPlus ^ isPlus) {
                runsCount++;
            }
            lastIsPlus = isPlus;
        }
        return runsCount;
    }

    private static Integer countOperations(int numberOfRuns, boolean endsWithPlus) {
        return endsWithPlus ? numberOfRuns - 1: numberOfRuns;
    }
}
