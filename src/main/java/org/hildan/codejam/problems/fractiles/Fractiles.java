package org.hildan.codejam.problems.fractiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hildan.codejam.io.CodeJamRunner;

/**
 * Code Jam 2016 Qualification Round. Problem D: "Fractiles".
 * <p>
 * Long ago, the Fractal civilization created artwork consisting of linear rows of tiles. They had two types of tile
 * that they could use: gold (G) and lead (L).
 * <p>
 * Each piece of Fractal artwork is based on two parameters: an original sequence of K tiles, and a complexity C. For a
 * given original sequence, the artwork with complexity 1 is just that original sequence, and the artwork with
 * complexity X+1 consists of the artwork with complexity X, transformed as follows: <ul> <li>replace each L tile in the
 * complexity X artwork with another copy of the original sequence</li> <li>replace each G tile in the complexity X
 * artwork with K G tiles</li> </ul> For example, for an original sequence of LGL, the pieces of artwork with complexity
 * 1 through 3 are: <ul> <li>C = 1: LGL (which is just the original sequence)</li> <li>C = 2: LGLGGGLGL</li> <li>C = 3:
 * LGLGGGLGLGGGGGGGGGLGLGGGLGL</li> </ul>
 * <p>
 * You have just discovered a piece of Fractal artwork, but the tiles are too dirty for you to tell what they are made
 * of. Because you are an expert archaeologist familiar with the local Fractal culture, you know the values of K and C
 * for the artwork, but you do not know the original sequence. Since gold is exciting, you would like to know whether
 * there is at least one G tile in the artwork. Your budget allows you to hire S graduate students, each of whom can
 * clean one tile of your choice (out of the KC tiles in the artwork) to see whether the tile is G or L.
 * <p>
 * Is it possible for you to choose a set of no more than S specific tiles to clean, such that no matter what the
 * original pattern was, you will be able to know for sure whether at least one G tile is present in the artwork? If so,
 * which tiles should you clean?
 * <p>
 * INPUT: The first line of the input gives the number of test cases, T. T test cases follow. Each consists of one line
 * with three integers: K, C, and S.
 * <p>
 * OUTPUT: For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1)
 * and y is either IMPOSSIBLE if no set of tiles will answer your question, or a list of between 1 and S positive
 * integers, which are the positions of the tiles that will answer your question. The tile positions are numbered from 1
 * for the leftmost tile to KC for the rightmost tile. Your chosen positions may be in any order, but they must all be
 * different.
 */
public class Fractiles extends CodeJamRunner {

    public Fractiles() {
        super(Fractiles::process);
    }

    private static String process(BufferedReader reader) throws IOException {
        String[] input = reader.readLine().split(" ");
        int K = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);
        int S = Integer.parseInt(input[2]);
        int nbTilesToCHeck = getNumberOfTilesToCheck(K, C);
        if (S < nbTilesToCHeck) {
            return "IMPOSSIBLE";
        }
        return findTiles(K, C, nbTilesToCHeck).stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    private static int getNumberOfTilesToCheck(int K, int C) {
        // each optimal tile checked rules out C possibilities
        int nToCheck = K / C;
        boolean divisible = K % C == 0;
        return divisible ? nToCheck : nToCheck + 1;
    }

    private static List<Long> findTiles(int K, int C, int nbTilesToCheck) {
        List<Long> tiles = new ArrayList<>(nbTilesToCheck);
        for (int s = 0; s < nbTilesToCheck; s++) {
            tiles.add(findTile(K, C, s) + 1); // one based
        }
        return tiles;
    }

    private static long findTile(int K, int C, int s) {
        long tileIndex = 0;
        for (int i = 1; i <= C; i++) {
            int lineNum = C * s + i - 1;
            if (lineNum >= K) {
                break;
            }
            tileIndex += (long) Math.pow(K, C - i) * lineNum;
        }
        return tileIndex;
    }
}
