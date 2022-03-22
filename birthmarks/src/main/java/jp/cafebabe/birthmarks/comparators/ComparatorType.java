package jp.cafebabe.birthmarks.comparators;

import java.io.Serial;
import java.io.Serializable;

public record ComparatorType(String type) implements Serializable {
    @Serial
    private static final long serialVersionUID = -1413643964886711476L;
}
