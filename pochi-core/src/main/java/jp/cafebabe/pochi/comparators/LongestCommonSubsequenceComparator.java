package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.pochi.comparators.algorithms.LCSCalculator;

import java.util.List;

public class LongestCommonSubsequenceComparator extends AbstractComparator {
    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return new ComparatorType("lcs");
        }

        @Override
        public Comparator build(Configuration config) {
            return new LongestCommonSubsequenceComparator(config);
        }
    }

    public LongestCommonSubsequenceComparator(Configuration config) {
        super(config);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        if(left.size() == 0 && right.size() == 0)
            return new Similarity(1d);
        var result = new LCSCalculator<Element>()
                .compute(SetUtils.list(left), SetUtils.list(right));
        return new Similarity(result);
    }

    @Override
    public ContainerType[] acceptableTypes() {
        return new ContainerType[] {
                ContainerType.List,
        };
    }
}
