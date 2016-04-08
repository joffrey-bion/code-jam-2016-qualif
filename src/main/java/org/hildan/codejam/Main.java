package org.hildan.codejam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.hildan.codejam.io.CodeJamRunner;
import org.hildan.codejam.problems.countingsheep.CountingSheep;

public class Main {

    private static final CodeJamRunner runner = new CountingSheep();

    public static void main(String[] args) {
        if (args.length == 0) {
            // no args, run with standard I/O
            runner.run(System.in, System.out);
            return;
        }
        int outFlagIndex = Arrays.binarySearch(args, "-o");
        if (outFlagIndex > -1) {
            runAndPrintToFile(args, outFlagIndex);
        } else {
            runAndPrintToStdOut(args);
        }
    }

    private static void runAndPrintToFile(String[] args, int outputFlagIndex) {
        if (args.length != 3) {
            System.err.println("Too many files specified, only one input and one output should be specified when "
                    + "using the output file flag -o");
            System.exit(1);
        }
        if (outputFlagIndex > 1) {
            System.err.println("Output flag -o should be followed by a filename");
            System.exit(1);
        }
        String inputFileName = args[outputFlagIndex == 0 ? 2 : 0];
        String outputFileName = args[outputFlagIndex + 1];
        runOnFiles(inputFileName, outputFileName);
    }

    private static void runAndPrintToStdOut(String[] inputFiles) {
        for (String inputFilename : inputFiles) {
            runOnFiles(inputFilename, inputFilename.replace(".in", ".out").replace("inputs", "outputs"));
        }
    }

    private static void runOnFiles(String inputFileName, String outputFileName) {
        Path filePath = Paths.get(outputFileName);
        Path parentDir = filePath.getParent();
        if (parentDir != null) {
            try {
                Files.createDirectories(parentDir);
            } catch (IOException e) {
                System.err.println("could not create directories for output file: " + e.getMessage());
            }
        }
        try (InputStream inputStream = new FileInputStream(inputFileName);
             PrintStream outputStream = new PrintStream(new FileOutputStream(outputFileName), true)) {
            runner.run(inputStream, outputStream, System.out);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
