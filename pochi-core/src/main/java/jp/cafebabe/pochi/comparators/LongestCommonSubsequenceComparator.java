package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.pochi.comparators.algorithms.LCSCalculator;

import java.util.List;

public class LongestCommonSubsequenceComparator extends AbstractComparator {
    public static final ComparatorType TYPE = new ComparatorType("lcs") {
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
            return new LongestCommonSubsequenceComparator(config);
        }

        @Override
        public String description() {
            return "longest common subsequence similarity";
        }
    }

    public LongestCommonSubsequenceComparator(Configuration config) {
        super(config, TYPE);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        var result = new LCSCalculator<Element>()
                .compute(SetUtils.list(left), SetUtils.list(right));
        return new Similarity(1d * result / Math.min(left.size(), right.size()));
    }
}
