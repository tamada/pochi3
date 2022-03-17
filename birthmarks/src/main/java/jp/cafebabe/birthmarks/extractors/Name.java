package jp.cafebabe.birthmarks.extractors;

import java.io.Serializable;

public record Name(String name) implements Serializable {
    private static final long serialVersionUID = 6292229979112458768L;

    public static Name of(String name) {
        return new Name(name);
    }
}
