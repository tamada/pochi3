package jp.cafebabe.birthmarks.comparators;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.utils.Stringer;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class ComparatorType implements Serializable, Stringer {
    @Serial
    private static final long serialVersionUID = -1413643964886711476L;

    private String type;

    public ComparatorType(String type) {
        this.type = type;
    }

    @Override
    public String string() {
        return type;
    }

    public String toString() {
        String acceptable = Arrays.stream(acceptable())
                .map(t -> t.name().toLowerCase())
                .collect(Collectors.joining(", "));
        return String.format("%s (accept: %s)", type, acceptable);
    }

    public int hashCode() {
        return Objects.hash("ComparatorType", string());
    }

    public boolean equals(Object other) {
        return (other instanceof ComparatorType ct)
            && Objects.equals(type, ct.type);
    }

    public abstract ContainerType[] acceptable();

    public boolean isAcceptable(ContainerType type) {
        return Arrays.stream(acceptable())
                .anyMatch(c -> c == type);
    }
}
