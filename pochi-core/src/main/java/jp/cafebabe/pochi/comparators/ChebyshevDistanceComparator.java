package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.impl.Converter;
import jp.cafebabe.pochi.comparators.algorithms.ChebyshevDistanceCalculator;

public class ChebyshevDistanceComparator extends AbstractComparator {
    public static final ComparatorType TYPE = new ComparatorType("chebyshev_distance") {
        @Override
        public ContainerType acceptable() {
            return ContainerType.Vector;
        }
    };

    public ChebyshevDistanceComparator(Configuration config) {
        super(config, TYPE);
    }

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return TYPE;
        }

        @Override
        public Comparator build(Configuration config) {
            return new ChebyshevDistanceComparator(config);
        }

        @Override
        public String description() {
            return "chebyshev distance similarity (vector)";
        }
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        var calculator = new ChebyshevDistanceCalculator<Element>();
        var distance = calculator.distance(Converter.toFrequency(left),
                Converter.toFrequency(right));
        return new Similarity(1.0 / (1 + distance));
    }
}
