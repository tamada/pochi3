package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.impl.VectorBirthmark;

public class CosineComparator extends AbstractComparator {
    private static final ComparatorType thisType = new ComparatorType("cosine") {
        @Override
        public ContainerType[] acceptable() {
            return new ContainerType[]{ ContainerType.Vector };
        }
    };

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new CosineComparator(config);
        }

        @Override
        public String description() {
            return "cosine similarity";
        }
    }

    public CosineComparator(Configuration config) {
        super(config, thisType);
    }

    protected Similarity calculate(Birthmark left, Birthmark right) {
        return calculateImpl((VectorBirthmark)left, (VectorBirthmark)right);
    }

    private Similarity calculateImpl(VectorBirthmark left, VectorBirthmark right) {
        double leftNorm = VectorUtils.norm(left);
        double rightNorm = VectorUtils.norm(right);
        double innerProduct = VectorUtils.innerProduct(left, right);
        return new Similarity(innerProduct / (leftNorm * rightNorm));
    }
}
