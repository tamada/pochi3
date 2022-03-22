package jp.cafebabe.birthmarks.config;

import java.io.Serial;
import java.io.Serializable;

public record Snippet(String value) implements Serializable{
    @Serial
    private static final long serialVersionUID = 8096441643141119024L;
}
