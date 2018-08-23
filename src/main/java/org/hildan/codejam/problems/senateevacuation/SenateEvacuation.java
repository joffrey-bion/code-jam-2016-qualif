package org.hildan.codejam.problems.senateevacuation;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.hildan.codejam.io.CodeJamRunner;

/**
 * Code Jam 2016 Qualification Round. Problem A: "Senate Evacuation".
 */
public class SenateEvacuation extends CodeJamRunner {

    public SenateEvacuation() {
        super(SenateEvacuation::readCase, SenateEvacuation::process);
    }

    private static Comparator<Party> biggestFirst = Comparator.comparing(Party::getMembersCount).reversed();

    private static class Party {
        public final Character letter;

        public int membersCount;

        public Party(Character letter, int membersCount) {
            this.letter = letter;
            this.membersCount = membersCount;
        }

        public int getMembersCount() {
            return membersCount;
        }

        public String toString() {
            return letter + " " + membersCount;
        }
    }

    private static List<Party> readCase(BufferedReader reader) throws IOException {
        int nbParties = Integer.parseInt(reader.readLine());
        String[] partiesStr = reader.readLine().split(" ");
        List<Party> parties = new ArrayList<>(partiesStr.length);
        for (int i = 0; i < partiesStr.length; i++) {
            parties.add(new Party((char) ('A' + i), Integer.parseInt(partiesStr[i])));
        }
        return parties;
    }

    private static String process(List<Party> parties) {
        StringBuilder sb = new StringBuilder();
        String step;
        while ((step = step(parties)) != null) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(step);
        }
        return sb.toString();
    }

    private static String step(List<Party> parties) {
        parties.sort(biggestFirst);
        Party p1 = parties.get(0);
        Party p2 = parties.get(1);
        if (p1.getMembersCount() == 0) {
            return null;
        }
        int diff = p1.getMembersCount() - p2.getMembersCount();
        if (diff == 0 && (parties.size() <= 2 || parties.get(2).membersCount < p1.membersCount)) {
            p1.membersCount--;
            p2.membersCount--;
            return p1.letter + "" + p2.letter;
        } else {
            p1.membersCount--;
            return p1.letter + "";
        }
    }
}
