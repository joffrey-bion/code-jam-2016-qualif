package org.hildan.codejam.problems.fashionpolice;

import java.io.BufferedReader;
import java.io.IOException;

import org.hildan.codejam.io.CodeJamRunner;

/**
 * Code Jam 2016 Qualification Round. Problem C: "Fashion Police".
 */
public class FashionPolice extends CodeJamRunner {

    public FashionPolice() {
        super(FashionPolice::processCase);
    }

    private static String processCase(BufferedReader reader) throws IOException {
        String[] input = reader.readLine().split(" ");
        int jackets = Integer.parseInt(input[0]);
        int pants = Integer.parseInt(input[1]);
        int shirts = Integer.parseInt(input[2]);
        int limit = Integer.parseInt(input[3]);

        StringBuilder out = new StringBuilder();
        int count = 0;
        for (int j = 0; j < jackets; j++) {
            for (int p = 0; p < pants; p++) {
                int rank = p / limit;
                for (int s = (rank * limit); s < (rank + 1) * limit && s < shirts; s++) {
                    out.append(line(j + 1, p + 1, s + 1)).append("\n");
//                    System.out.println(line(j + 1, p + 1, s + 1));
                    count++;
                }
            }
        }

        if (out.length() > 0) {
            out.deleteCharAt(out.length() - 1);
        }

        out.insert(0, count + "\n");
        return out.toString();
    }

    private static String line(int j, int p, int s) {
        return j + " " + p + " " + s;
    }
}
