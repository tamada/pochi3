package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;

import java.util.Set;

public class SimpsonIndexComparator extends AbstractComparator {
    private static final ComparatorType thisType = new ComparatorType("SimpsonIndex");

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new SimpsonIndexComparator(config);
        }
    }

    public SimpsonIndexComparator(Configuration config){
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
        if (left.elementCount() == 0 && right.elementCount() == 0)
            return new Similarity(1d);
        else if (left.elementCount() == 0 || right.elementCount() == 0)
            return new Similarity(0d);
        return calculateImpl(left, right);
    }

    private Similarity calculateImpl(Birthmark left, Birthmark right) {
        Set<Element> intersection = SetUtils.intersect(left, right);
        int denominator = Math.min(left.elementCount(), right.elementCount());
        return new Similarity(1.0 * intersection.size() / denominator);
    }
}
