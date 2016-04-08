package org.hildan.codejam.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.function.Function;

/**
 * A generic runner for all Google Code Jam problems.
 */
public class CodeJamRunner {

    private final TestCaseProcessor caseProcessor;

    /**
     * Creates a new CodeJamRunner using the given functions.
     *
     * @param caseProcessor
     *         a function reading a test case from the given {@link BufferedReader}, resolving it, and returning the
     *         answer String
     */
    public CodeJamRunner(TestCaseProcessor caseProcessor) {
        this.caseProcessor = caseProcessor;
    }

    /**
     * Creates a new CodeJamRunner using the given functions.
     *
     * @param caseInputReader
     *         a function using the given {@link BufferedReader} to parse the input as a test case object of class C
     * @param caseSolver
     *         a function resolving the given test case C and returning the answer String
     * @param <C>
     *         the type of the test cases handled by this runner
     */
    public <C> CodeJamRunner(TestCaseReader<C> caseInputReader, Function<C, String> caseSolver) {
        this(reader -> caseSolver.apply(caseInputReader.readOneCase(reader)));
    }

    /**
     * Processes the given input stream to read test cases and solve them. The output is written to all the given output
     * streams.
     *
     * @param inputStream
     *         the input stream to read the test cases from. The first line should be the number of test cases to read.
     *         The other lines depend on the problem, and will be read test by case by the internal {@link
     *         TestCaseProcessor}.
     * @param outputStreams
     *         the streams to output the results to
     */
    public void run(InputStream inputStream, PrintStream... outputStreams) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int nbOfTestCases;
            try {
                nbOfTestCases = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.err.println("The input should start with the number of test cases");
                return;
            }

            for (int i = 1; i <= nbOfTestCases; i++) {
                String caseAnswer = caseProcessor.processOneCase(reader);
                String outputLine = "Case #" + i + ": " + caseAnswer;
                for (PrintStream outputStream : outputStreams) {
                    outputStream.println(outputLine);
                }
            }
            for (PrintStream outputStream : outputStreams) {
                outputStream.flush();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
