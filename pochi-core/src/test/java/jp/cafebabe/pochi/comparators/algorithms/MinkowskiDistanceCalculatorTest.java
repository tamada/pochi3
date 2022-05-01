package jp.cafebabe.pochi.comparators.algorithms;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.birthmarks.utils.Vectorable;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinkowskiDistanceCalculatorTest {
    @Test
    public void testEuclideanDistance() {
        var calculator = new MinkowskiDistanceCalculator<String>(2);
        var v1 = new Vec(Map.of("a", 1L, "b", 2L, "c", 3L));
        var v2 = new Vec(Map.of("a", 2L, "d", 4L));
        assertEquals(Math.sqrt(30), calculator.distance(v1, v2), 1E-7);
    }

    @Test
    public void testManhattanDistance() {
        var calculator = new MinkowskiDistanceCalculator<String>(1);
        var v1 = new Vec(Map.of("a", 1L, "b", 2L, "c", 3L));
        var v2 = new Vec(Map.of("a", 2L, "d", 4L));
        assertEquals(10d, calculator.distance(v1, v2), 1E-7);
    }

    private static final class Vec implements Vectorable<String, Long> {
        private Map<String, Long> map;

        public Vec(Map<String, Long> map) {
            this.map = map;
        }

        @Override
        public Stream<String> keyStream() {
            return map.keySet().stream();
        }

        @Override
        public Stream<Long> valueStream() {
            return map.values().stream();
        }

        @Override
        public Stream<Pair<String, Long>> vectorStream() {
            return map.entrySet().stream()
                    .map(entry -> Pair.of(entry.getKey(), entry.getValue()));
        }

        @Override
        public Long getOrDefault(String key, Long defaultValue) {
            return map.getOrDefault(key, defaultValue);
        }
    }
}
