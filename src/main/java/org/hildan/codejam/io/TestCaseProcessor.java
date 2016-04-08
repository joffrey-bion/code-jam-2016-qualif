package org.hildan.codejam.io;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Interface representing an object capable of reading a test case from the given reader, solve it and return the
 * answer.
 */
@FunctionalInterface
public interface TestCaseProcessor {

    /**
     * Reads one test case from the given reader and solves it.
     *
     * @param inputReader
     *         the reader to read the test case input data from
     * @return the string corresponding to the answer of the test case
     * @throws IOException
     *         if an I/O error occurs while reading the file
     */
    String processOneCase(BufferedReader inputReader) throws IOException;
}
