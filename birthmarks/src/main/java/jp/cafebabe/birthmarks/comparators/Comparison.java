package jp.cafebabe.birthmarks.comparators;

import jp.cafebabe.birthmarks.entities.Birthmark;

import java.io.Serial;
import java.io.Serializable;

public class Comparison implements Comparable<Comparison>, Serializable {
    @Serial
    private static final long serialVersionUID = 4696041585469364687L;

    private final Pair<Birthmark, Birthmark> pair;
    private final Similarity similarity;

    public Comparison(Birthmark left, Birthmark right, Similarity similarity) {
        this(Pair.of(left, right), similarity);
    }

    public Comparison(Pair<Birthmark, Birthmark> pair, Similarity similarity) {
        this.pair = pair;
        this.similarity = similarity;
    }

    public boolean isStolen(double threshold) {
        return similarity.isStolen(threshold);
    }

    public void accept(ComparisonVisitor visitor) {
        visitor.visit(pair.left(), pair.right(), similarity);
    }

    @Override
    public int compareTo(Comparison other) {
        return similarity.compareTo(other.similarity);
    }
}
