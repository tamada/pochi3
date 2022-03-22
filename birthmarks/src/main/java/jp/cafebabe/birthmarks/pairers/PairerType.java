package jp.cafebabe.birthmarks.pairers;

import java.io.Serial;
import java.io.Serializable;

public record PairerType(String type) implements Serializable {
    @Serial
    private static final long serialVersionUID = 7074036293235948399L;
}
