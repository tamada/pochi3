package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.impl.Converter;

import java.util.Set;

public class OverlapCoefficientComparator extends AbstractComparator {
    public static final ComparatorType TYPE = new ComparatorType("overlap_coefficient") {
        @Override
        public ContainerType acceptable() {
            return ContainerType.Set;
        }
    };

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return TYPE;
        }

        @Override
        public Comparator build(Configuration config) {
            return new OverlapCoefficientComparator(config);
        }
        @Override
        public String description() {
            return "overlap coefficient similarity";
        }
    }

    public OverlapCoefficientComparator(Configuration config){
        super(config, TYPE);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        Set<Element> intersection = SetUtils.intersect(left, right);
        var denominator = Math.min(Converter.toSet(left).size(), Converter.toSet(right).size());
        return new Similarity((1.0 * intersection.size()) / denominator);
    }
}
