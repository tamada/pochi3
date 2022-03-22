package jp.cafebabe.birthmarks.entities;

import java.io.Serial;
import java.io.Serializable;

public record BirthmarkType(String type) implements Serializable {
    @Serial
    private static final long serialVersionUID = -934480767958956079L;

    public static BirthmarkType of(String name) {
        return new BirthmarkType(name);
    }
}
