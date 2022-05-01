package jp.cafebabe.pochi.comparators.algorithms;

import jp.cafebabe.birthmarks.utils.Vectorable;

import java.util.stream.Stream;

public class MinkowskiDistanceCalculator<T> {
    private final int pvalue;

    public MinkowskiDistanceCalculator(int p) {
        this.pvalue = p;
    }

    public double distance(Vectorable<T, Long> left, Vectorable<T, Long> right) {
        return Math.pow(Stream.concat(left.keyStream(), right.keyStream())
                .distinct().mapToDouble(key -> calculate(key, left, right))
                .sum(), 1.0 / pvalue);
    }

    private double calculate(T key, Vectorable<T, Long> v1, Vectorable<T, Long> v2) {
        return calculate(v1.getOrDefault(key, 0L), v2.getOrDefault(key, 0L));
    }

    private double calculate(long v1, long v2) {
        return Math.pow(Math.abs(v1 - v2), pvalue);
    }
}

