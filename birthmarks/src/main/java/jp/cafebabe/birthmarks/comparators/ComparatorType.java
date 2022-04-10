package jp.cafebabe.birthmarks.comparators;

import jp.cafebabe.birthmarks.utils.Stringer;

import java.io.Serial;
import java.io.Serializable;

public record ComparatorType(String type) implements Serializable, Stringer {
    @Serial
    private static final long serialVersionUID = -1413643964886711476L;

    @Override
    public String string() {
        return type();
    }
}
