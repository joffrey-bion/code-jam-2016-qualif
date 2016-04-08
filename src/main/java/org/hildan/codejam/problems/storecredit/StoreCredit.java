package org.hildan.codejam.problems.storecredit;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import org.hildan.codejam.io.CodeJamRunner;

/**
 * Code Jam 2010 Qualification Round Africa
 */
public class StoreCredit extends CodeJamRunner {

    public StoreCredit() {
        super(StoreCredit::readCase, StoreCredit::findProducts);
    }

    private static class TestCase {
        public final int credit;

        public final int[] itemsPrices;

        public TestCase(int credit, int[] itemsPrices) {
            this.credit = credit;
            this.itemsPrices = itemsPrices;
        }
    }

    private static TestCase readCase(BufferedReader reader) throws IOException {
        int credit = Integer.parseInt(reader.readLine());
        reader.readLine(); // reads the number of items, which we don't need
        int[] itemsPrices = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        return new TestCase(credit, itemsPrices);
    }

    private static String findProducts(TestCase testCase) {
        // TODO
        return "";
    }
}
