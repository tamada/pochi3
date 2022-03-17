package jp.cafebabe.birthmarks.extractors.impl;

import jp.cafebabe.birthmarks.extractors.Birthmark;
import jp.cafebabe.birthmarks.extractors.Metadata;

import java.io.Serializable;

public abstract class AbstractBirthmark implements Birthmark, Serializable {
    private static final long serialVersionUID = -8592291121632189494L;

    private Metadata metadata;

    AbstractBirthmark(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public final Metadata metadata() {
        return metadata;
    }
}
