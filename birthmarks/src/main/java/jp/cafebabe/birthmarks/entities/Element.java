package jp.cafebabe.birthmarks.entities;

import jp.cafebabe.birthmarks.io.Marshaller;

import java.io.Serializable;

public interface Element extends Serializable {
    long intValue();

    String value();

    double doubleValue();

    void marshal(Marshaller out);
}
