package jp.cafebabe.birthmarks.comparators;

import jp.cafebabe.birthmarks.BirthmarkException;
import jp.cafebabe.birthmarks.entities.Birthmark;

public class ComparisonException extends BirthmarkException {
    private Pair<Birthmark, Birthmark> pair;

    public ComparisonException(Birthmark left, Birthmark right) {
        super();
        this.pair = Pair.of(left, right);
    }

    public ComparisonException(String message, Birthmark left, Birthmark right) {
        super(message);
        this.pair = Pair.of(left, right);
    }

    public Birthmark left() {
        return pair.left();
    }

    public Birthmark right() {
        return pair.right();
    }
}
