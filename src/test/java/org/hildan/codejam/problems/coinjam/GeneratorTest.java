package org.hildan.codejam.problems.coinjam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneratorTest {

    @Test
    public void testCandidates() {
        SequentialCandidateGenerator gen = new SequentialCandidateGenerator(4);
        List<String> expected = Arrays.asList("1001", "1011", "1101", "1111");
        List<String> candidates = Stream.generate(gen).limit(4).collect(Collectors.toList());
        assertEquals(expected, candidates);
    }

}