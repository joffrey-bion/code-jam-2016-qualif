package org.hildan.codejam.io;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Interface representing an object capable of reading a test case from the given reader.
 *
 * @param <C>
 *         the type of the test cases objects read by this TestCaseReader
 */
@FunctionalInterface
public interface TestCaseReader<C> {

    /**
     * Reads one test case from the given reader.
     *
     * @param reader
     *         the reader to read the test case's data from
     * @return an object representing the test case that was read
     * @throws IOException
     *         if an I/O error occurs while reading the file
     */
    C readOneCase(BufferedReader reader) throws IOException;
}
