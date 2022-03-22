package jp.cafebabe.birthmarks.entities.impl;

import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.Metadata;

import java.io.Serial;
import java.io.Serializable;

public abstract class AbstractBirthmark implements Birthmark, Serializable {
    @Serial
    private static final long serialVersionUID = -8592291121632189494L;

    private final Metadata metadata;

    AbstractBirthmark(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public final Metadata metadata() {
        return metadata;
    }
}
