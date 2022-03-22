package jp.cafebabe.pochi.comparators.algorithms;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditDistanceCalculatorTest {
    private EditDistanceCalculator<String> calculator = new EditDistanceCalculator<>();

    @Test
    public void testBasic() {
        int distance = calculator.compute(List.of("a", "b", "c"), List.of("b", "c", "d", "e"));
        assertEquals(3, distance);
    }
}
