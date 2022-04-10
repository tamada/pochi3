package jp.cafebabe.birthmarks.pairers;

import jp.cafebabe.birthmarks.utils.Stringer;

import java.io.Serial;
import java.io.Serializable;

public record PairerType(String type) implements Serializable, Stringer {
    @Serial
    private static final long serialVersionUID = 7074036293235948399L;

    public String string() {
        return type();
    }
}
