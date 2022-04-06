package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.impl.Converter;

import java.util.Set;

public class DiceIndexComparator extends AbstractComparator {
    private static final ComparatorType thisType = new ComparatorType("dice_index");

    public DiceIndexComparator(Configuration config){
        super(config);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        if(left.size() == 0 && right.size() == 0)
            return new Similarity(1d);
        Set<Element> intersection = SetUtils.intersect(left, right);
        long denominator = Converter.toSet(left).size() + Converter.toSet(right).size();
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
