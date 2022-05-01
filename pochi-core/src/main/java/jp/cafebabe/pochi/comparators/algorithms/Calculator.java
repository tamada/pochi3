package jp.cafebabe.pochi.comparators.algorithms;

import jp.cafebabe.pochi.utils.Index2D;
import jp.cafebabe.pochi.utils.Table;

import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;

public class Calculator {
    protected Table<Integer> init(int width, int height, IntBinaryOperator initializer) {
        var table = new Table<Integer>(width, height);
        IntStream.range(0, width)
                .forEach(x -> IntStream.range(0, height)
                        .forEach(y -> table.set(initializer.applyAsInt(x, y), new Index2D(x, y))));
        return table;
    }

    protected int minimum(int... values) {
        return maxmin(Integer.MAX_VALUE, Math::min, values);
    }

    protected int maximum(int... values) {
        return maxmin(Integer.MIN_VALUE, Math::max, values);
    }

    private int maxmin(int identify, IntBinaryOperator operator, int... values) {
        return IntStream.of(values)
                .reduce(identify, operator);
    }
}
