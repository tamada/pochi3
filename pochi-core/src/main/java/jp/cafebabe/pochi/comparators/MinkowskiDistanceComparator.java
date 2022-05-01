package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.impl.Converter;
import jp.cafebabe.pochi.comparators.algorithms.MinkowskiDistanceCalculator;

public class MinkowskiDistanceComparator extends AbstractComparator {
    public static final ComparatorType EUCLIDEAN = new ComparatorType("euclidean_distance") {
        @Override
        public ContainerType acceptable() {
            return ContainerType.Vector;
        }
    };
    public static final ComparatorType MANHATTAN = new ComparatorType("manhattan_distance") {
        @Override
        public ContainerType acceptable() {
            return ContainerType.Vector;
        }
    };

    private int pvalue;

    public MinkowskiDistanceComparator(Configuration config, ComparatorType type, int p) {
        super(config, type);
        this.pvalue = p;
    }

    public static final class EuclideanBuilder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return EUCLIDEAN;
        }

        @Override
        public Comparator build(Configuration config) {
            return new MinkowskiDistanceComparator(config, EUCLIDEAN, 2);
        }

        @Override
        public String description() {
            return "euclidean distance similarity";
        }
    }

    public static final class ManhattanBuilder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return MANHATTAN;
        }

        @Override
        public Comparator build(Configuration config) {
            return new MinkowskiDistanceComparator(config, MANHATTAN, 1);
        }

        @Override
        public String description() {
            return "euclidean distance similarity";
        }
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        var calculator = new MinkowskiDistanceCalculator<Element>(pvalue);
        var distance = calculator.distance(Converter.toFrequency(left),
                Converter.toFrequency(right));
        return new Similarity(1.0 / (1 + distance));
    }
}
