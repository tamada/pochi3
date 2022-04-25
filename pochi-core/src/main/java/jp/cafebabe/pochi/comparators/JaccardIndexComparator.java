package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;

import java.util.Set;

public class JaccardIndexComparator extends AbstractComparator {
    private static final ComparatorType thisType = new ComparatorType("jaccard_index") {
        @Override
        public ContainerType acceptable() {
            return ContainerType.Set;
        }
    };

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new JaccardIndexComparator(config);
        }

        @Override
        public String description() {
            return "jaccard index";
        }
    }

    public JaccardIndexComparator(Configuration config){
        super(config, thisType);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        if(left.size() == 0 && right.size() == 0)
            return new Similarity(1d);
        Set<Element> intersection = SetUtils.intersect(left, right);
        Set<Element> union = SetUtils.union(left, right);
        return new Similarity((1.0 * intersection.size()) / union.size());
    }
}
