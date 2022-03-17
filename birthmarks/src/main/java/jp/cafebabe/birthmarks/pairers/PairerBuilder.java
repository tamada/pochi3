package jp.cafebabe.birthmarks.pairers;

import jp.cafebabe.birthmarks.config.Configuration;

public interface PairerBuilder {
    PairerType type();

    Pairer build(Configuration config);
}
