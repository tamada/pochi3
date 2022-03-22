package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;

import java.util.Set;

public class DiceIndexComparator extends AbstractComparator {
    private static ComparatorType thisType = new ComparatorType("DiceIndex");

    public DiceIndexComparator(Configuration config){
        super(config);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        if(left.elementCount() == 0 && right.elementCount() == 0)
            return new Similarity(1d);
        Set<Element> intersection = SetUtils.intersect(left, right);
        int denominator = left.elementCount() + right.elementCount();
        return new Similarity(2.0 * intersection.size() / denominator);
    }

    @Override
    public ContainerType[] acceptableTypes() {
        return new ContainerType[] {
                ContainerType.Set,
                ContainerType.List,
                ContainerType.Vector,
        };
    }

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new DiceIndexComparator(config);
        }
    }
}
