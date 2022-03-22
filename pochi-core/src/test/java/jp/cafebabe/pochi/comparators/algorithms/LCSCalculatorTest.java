package jp.cafebabe.pochi.comparators.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LCSCalculatorTest {
    @Test
    public void testCompute() {
        List<String> list1 = Arrays.asList("myabcuuu".split(""));
        List<String> list2 = Arrays.asList("abcjju".split(""));
        double result = new LCSCalculator<String>().compute(list1, list2);

        assertEquals(4d/6d, result, 1E-6);
    }
}
