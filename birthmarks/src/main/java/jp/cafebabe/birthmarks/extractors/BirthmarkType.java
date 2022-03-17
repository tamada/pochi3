package jp.cafebabe.birthmarks.extractors;

import java.io.Serializable;

public record BirthmarkType(String type) implements Serializable {
    private static final long serialVersionUID = -934480767958956079L;
}
