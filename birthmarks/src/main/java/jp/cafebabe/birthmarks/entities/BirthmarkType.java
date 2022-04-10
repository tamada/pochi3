package jp.cafebabe.birthmarks.entities;

import jp.cafebabe.birthmarks.utils.Stringer;

import java.io.Serial;
import java.io.Serializable;

public record BirthmarkType(String type) implements Serializable, Stringer {
    @Serial
    private static final long serialVersionUID = -934480767958956079L;

    public static BirthmarkType of(String name) {
        return new BirthmarkType(name);
    }

    public String string() {
        return type();
    }
}
