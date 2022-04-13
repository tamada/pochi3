package jp.cafebabe.birthmarks.entities;

import java.io.Serializable;
import java.io.Writer;

public interface Element extends Serializable {
    long intValue();

    String value();

    double doubleValue();

    void marshal(Writer out);
}
