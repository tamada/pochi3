package jp.cafebabe.pochi.comparators.algorithms;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.pochi.utils.Index2D;
import jp.cafebabe.pochi.utils.Table;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EditDistanceCalculator<T> extends Calculator {
    private static final int BIG_NUMBER = 10;

    public int compute(List<T> list1, List<T> list2) {
        var table = init(list1.size() + 1, list2.size() + 1, (x, y) -> {
            if(x == 0)      return y;
            else if(y == 0) return x;
            else return 0;
        });
        computeCosts(table, list1, list2);
        return table.cost();
    }

    private void computeCosts(Table<Integer> table, List<T> list1, List<T> list2) {
        table.indexStream()
                .forEach(index -> findPair(index, list1, list2)
                        .ifPresent(pair -> computeCost(table, index, pair)));
    }

    private Optional<Pair<T, T>> findPair(Index2D index, List<T> list1, List<T> list2) {
        if(index.x() == 0 || index.y() == 0)
            return Optional.empty();
        return Optional.of(Pair.of(list1.get(index.x() - 1), list2.get(index.y() - 1)));
    }

    private void computeCost(Table<Integer> table, Index2D index, Pair<T, T> pair) {
        int newValue = computeCostImpl(table, index, pair);
        table.set(newValue, index);
    }

    private int computeCostImpl(Table<Integer> table, Index2D index, Pair<T, T> pair) {
        int d1 = tableValue(table, index.relativeOf(-1,  0)) + 1;
        int d2 = tableValue(table, index.relativeOf( 0, -1)) + 1;
        int d3 = tableValue(table, index.relativeOf(-1, -1)) +
                pair.ifAndElse((l, r) -> Objects.equals(l, r), 0, 1);
        return minimum(d1, d2, d3);
    }

    private int tableValue(Table<Integer> table, Optional<Index2D> optionalIndex) {
        return optionalIndex.map(table::get)
                .orElse(BIG_NUMBER);
    }
}
