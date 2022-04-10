package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.pochi.comparators.algorithms.EditDistanceCalculator;

import java.util.List;

public class EditDistanceComparator extends AbstractComparator {
    private static final ComparatorType thisType = new ComparatorType("edit_distance");

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new EditDistanceComparator(config);
        }

        @Override
        public String description() {
            return "";
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
        List<Element> leftList = SetUtils.list(left);
        List<Element> rightList = SetUtils.list(right);
        return new Similarity(calculate(leftList, rightList));
    }

    private double calculate(List<Element> left, List<Element> right){
        int distance = new EditDistanceCalculator<Element>()
                .compute(left, right);
        return 1d - (1.0 * distance / Math.max(left.size(), right.size()));
    }
}
