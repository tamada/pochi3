package jp.cafebabe.pochi.comparators.algorithms;

import jp.cafebabe.birthmarks.comparators.Pair;
import jp.cafebabe.pochi.utils.Index2D;
import jp.cafebabe.pochi.utils.Table;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LCSCalculator<T> extends Calculator {
    public int compute(List<T> list1, List<T> list2) {
        var table = init(list1.size() + 1, list2.size() + 1, (x, y) -> 0);
        computeCosts(table, list1, list2);
        return table.getLatestItem();
    }

    private void computeCosts(Table<Integer> table, List<T> list1, List<T> list2) {
        table.indexStream()
                .forEach(index -> findPair(index, list1, list2)
                        .ifPresent(pair -> findCost(table, index, pair)));
    }

    private Optional<Pair<T, T>> findPair(Index2D index, List<T> list1, List<T> list2) {
        if(index.x() == 0 || index.y() == 0)
            return Optional.empty();
        return Optional.of(Pair.of(list1.get(index.x() - 1), list2.get(index.y() - 1)));
    }

    private void findCost(Table<Integer> table, Index2D index, Pair<T, T> pair) {
        pair.ifAndElse(Objects::equals,
                (i1, i2) -> updateCostWithMatch(table, index),
                (i1, i2) -> updateCostWithUnmatch(table, index));
    }

    private void updateCostWithMatch(Table<Integer> table, Index2D index) {
        index.relativeOf(-1, -1)
                .ifPresent(i -> table.set(table.get(i) + 1, index));
    }

    private void updateCostWithUnmatch(Table<Integer> table, Index2D index) {
        int d1 = tableValue(table, index.relativeOf(-1, 0));
        int d2 = tableValue(table, index.relativeOf(0, -1));
        table.set(Math.max(d1, d2), index);
    }

    private int tableValue(Table<Integer> table, Optional<Index2D> index) {
        return index.map(table::get)
                .orElse(0);
    }
}
