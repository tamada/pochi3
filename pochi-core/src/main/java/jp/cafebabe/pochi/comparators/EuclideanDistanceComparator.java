package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.impl.Converter;
import jp.cafebabe.birthmarks.entities.impl.VectorBirthmark;
import jp.cafebabe.pochi.comparators.algorithms.EditDistanceCalculator;

import java.util.List;

public class EuclideanDistanceComparator extends AbstractComparator {
    public static final ComparatorType TYPE = new ComparatorType("euclidean_distance") {
        @Override
        public ContainerType acceptable() {
            return ContainerType.Vector;
        }
    };

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return TYPE;
        }

        @Override
        public Comparator build(Configuration config) {
            return new EuclideanDistanceComparator(config);
        }

        @Override
        public String description() {
            return "euclidean distance similarity";
        }
    }

    public EuclideanDistanceComparator(Configuration config){
        super(config, TYPE);
    }

    @Override
    protected Similarity calculate(Birthmark left, Birthmark right) {
        var leftVector = Converter.toFrequency(left);
        var rightVector = Converter.toFrequency(right);
        var keys = SetUtils.union(left, right);
        var distance = Math.sqrt(keys.stream()
                .mapToLong(key -> difference(key, leftVector, rightVector)).sum());
        return new Similarity(1.0 / (1 + distance));
    }

    private long difference(Element key, VectorBirthmark left, VectorBirthmark right) {
        var v1 = left.getOrDefault(key, 0L);
        var v2 = right.getOrDefault(key, 0L);
        return (v1 - v2) * (v1 - v2);
    }
}
