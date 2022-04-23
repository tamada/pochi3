package jp.cafebabe.pochi.comparators.algorithms;

import jp.cafebabe.birthmarks.comparators.Pair;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SmithWatermanCalculator<T> extends Calculator {
    private ScoreParameter params;
    private Value score = new Value(0, Index2D.of(0, 0));

    public SmithWatermanCalculator() {
        this(ScoreParameter.of(1, 1, 1));
    }

    public SmithWatermanCalculator(int match, int mismatch, int gap) {
        this(ScoreParameter.of(match, mismatch, gap));
    }

    public SmithWatermanCalculator(ScoreParameter params) {
        this.params = params;
    }

    public int compute(List<T> list1, List<T> list2) {
        Table table = init(list1.size() + 1, list2.size() + 1, (x, y) -> 0);
        computeCosts(table, list1, list2);
        System.out.println(table);
        return score.value();
    }

    private void computeCosts(Table table, List<T> list1, List<T> list2) {
        table.indexStream()
                .forEach(index -> findPair(index, list1, list2)
                        .ifPresent(pair -> computeCost(table, index, pair)));
    }

    private Optional<Pair<T, T>> findPair(Index2D index, List<T> list1, List<T> list2) {
        if(index.x() == 0 || index.y() == 0)
            return Optional.empty();
        return Optional.of(Pair.of(list1.get(index.x() - 1), list2.get(index.y() - 1)));
    }

    private void computeCost(Table table, Index2D index, Pair<T, T> pair) {
        int newValue = computeCostImpl(table, index, pair);
        table.set(newValue, index);
        score = score.update(newValue, index);
    }

    private int computeCostImpl(Table table, Index2D index, Pair<T, T> pair) {
        int d1 = tableValue(table, index.relativeOf(-1,  0)) + params.gap();
        int d2 = tableValue(table, index.relativeOf( 0, -1)) + params.gap();
        int d3 = tableValue(table, index.relativeOf(-1, -1)) +
                pair.ifAndElse((l, r) -> Objects.equals(l, r), params.match(), params.mismatch());
        return maximum(d1, d2, d3, 0);
    }

    private int tableValue(Table table, Optional<Index2D> optionalIndex) {
        return optionalIndex.map(table::get)
                .orElse(Integer.MIN_VALUE);
    }
}
