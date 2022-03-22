package jp.cafebabe.pochi.comparators;

import jp.cafebabe.birthmarks.comparators.*;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.impl.VectorBirthmark;

public class CosineComparator extends AbstractComparator {
    private static final ComparatorType thisType = new ComparatorType("cosine");

    public static final class Builder implements ComparatorBuilder {
        @Override
        public ComparatorType type() {
            return thisType;
        }

        @Override
        public Comparator build(Configuration config) {
            return new CosineComparator(config);
        }
    }

    public CosineComparator(Configuration config) {
        super(config);
    }

    @Override
    public ContainerType[] acceptableTypes() {
        return new ContainerType[] {
                ContainerType.Vector,
        };
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
