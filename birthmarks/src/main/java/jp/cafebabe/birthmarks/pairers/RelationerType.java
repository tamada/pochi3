package jp.cafebabe.birthmarks.pairers;

import jp.cafebabe.birthmarks.utils.Stringer;

import java.io.Serial;
import java.io.Serializable;

public record RelationerType(String type) implements Serializable, Stringer {
    @Serial
    private static final long serialVersionUID = -51324818724193119L;

    public String string() {
        return type();
    }

    public static RelationerType of(String type) {
        return new RelationerType(type);
    }
}
