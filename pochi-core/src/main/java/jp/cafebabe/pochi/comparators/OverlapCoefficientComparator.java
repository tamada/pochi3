package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.impl.Converter;

import java.util.Set;

public class OverlapCoefficientComparator extends AbstractComparator {
    private static final ComparatorType thisType = new ComparatorType("jaccard_index");

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new OverlapCoefficientComparator(config);
        }
    }
    public OverlapCoefficientComparator(Configuration config){
        super(config);
    }

    @Override
    public ContainerType[] acceptableTypes() {
        return new ContainerType[] {
                ContainerType.Set,
                ContainerType.List,
                ContainerType.Vector,
        };
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        if(left.size() == 0 && right.size() == 0)
            return new Similarity(1d);
        Set<Element> intersection = SetUtils.intersect(left, right);
        var denominator = Math.min(Converter.toSet(left).size(), Converter.toSet(right).size());
        return new Similarity((1.0 * intersection.size()) / denominator);
    }
}
