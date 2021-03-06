package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.pochi.comparators.algorithms.SmithWatermanCalculator;

import java.util.List;

public class SmithWatermanComparator extends AbstractComparator {
    public static final ComparatorType TYPE = new ComparatorType("smith_waterman") {
        @Override
        public ContainerType acceptable() {
            return ContainerType.List;
        }
    };

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return TYPE;
        }

        @Override
        public Comparator build(Configuration config) {
            return new SmithWatermanComparator(config);
        }

        @Override
        public String description() {
            return "smith-waterman similarity (list)";
        }
    }

    public SmithWatermanComparator(Configuration config){
        super(config, TYPE);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        List<Element> leftList = SetUtils.list(left);
        List<Element> rightList = SetUtils.list(right);
        return new Similarity(calculate(leftList, rightList));
    }

    private double calculate(List<Element> left, List<Element> right){
        int distance = new SmithWatermanCalculator<Element>()
                .compute(left, right);
        return 1d - (1.0 * distance / Math.min(left.size(), right.size()));
    }
}
