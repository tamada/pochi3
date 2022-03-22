package jp.cafebabe.clpond.entities;

import java.io.Serial;
import java.io.Serializable;

public record Name(String name) implements Serializable, Comparable<Name> {
    @Serial
    private static final long serialVersionUID = -3739333182235166571L;

    @Override
    public int compareTo(Name other) {
        return name().compareTo(other.name());
    }

    public static Name of(String name) {
        return new Name(name);
    }
}
