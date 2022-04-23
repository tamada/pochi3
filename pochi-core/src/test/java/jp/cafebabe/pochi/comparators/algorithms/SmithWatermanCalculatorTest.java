package jp.cafebabe.pochi.comparators.algorithms;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SmithWatermanCalculatorTest {
    private SmithWatermanCalculator<String> calculator = new SmithWatermanCalculator<>(10, -5, -7);

    @Test
    public void testBasic1() {
        var calculator = new SmithWatermanCalculator<String>(10, -5, -7);
        int distance = calculator.compute(toList("AGCGTAG"), toList("CTCGTC"));
        assertEquals(30, distance);
    }

    @Test
    public void testBasic2() {
        var calculator = new SmithWatermanCalculator<String>(3, -3, -2);
        int distance = calculator.compute(toList("TGTTACGG"), toList("GGTTGACTA"));
        assertEquals(13, distance);
    }

    @Test
    public void testBasic3() {
        var calculator = new SmithWatermanCalculator<String>(1, -1, -1);
        int distance = calculator.compute(toList("TGTTACGG"), toList("GGTTGACTA"));
        assertEquals(4, distance);
    }

    private List<String> toList(String str) {
        return str.chars()
                .mapToObj(c -> String.valueOf((char)c))
                .toList();
    }
}
