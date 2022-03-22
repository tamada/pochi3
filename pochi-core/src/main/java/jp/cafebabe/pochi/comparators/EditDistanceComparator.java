package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.pochi.comparators.algorithms.EditDistanceCalculator;

import java.util.List;

public class EditDistanceComparator extends AbstractComparator {
    private static final ComparatorType thisType = new ComparatorType("EditDistance");

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new EditDistanceComparator(config);
        }
    }

    public EditDistanceComparator(Configuration config){
        super(config);
    }

    @Override
    public ContainerType[] acceptableTypes() {
        return new ContainerType[] {
                ContainerType.Set,
                ContainerType.List,
        };
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        List<Element> leftList = toList(left);
        List<Element> rightList = toList(right);
        return new Similarity(calculate(leftList, rightList));
    }

    private List<Element> toList(Birthmark birthmark){
        return birthmark.stream().toList();
    }

    private double calculate(List<Element> left, List<Element> right){
        int distance = new EditDistanceCalculator<Element>()
                .compute(left, right);
        return 1d - (1.0 * distance / Math.max(left.size(), right.size()));
    }
}
