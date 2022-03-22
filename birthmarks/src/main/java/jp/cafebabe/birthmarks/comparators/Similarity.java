package jp.cafebabe.birthmarks.comparators;

import java.io.Serial;
import java.io.Serializable;

public record Similarity(double value) implements Comparable<Similarity>, Serializable {
    @Serial
    private static final long serialVersionUID = -8526226752891898334L;

    public boolean isCloseTo(Similarity other, double delta) {
        return Math.abs(value - other.value()) < delta;
    }

    @Override
    public int compareTo(Similarity other) {
        return Double.compare(value, other.value);
    }
}
