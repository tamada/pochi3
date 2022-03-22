package jp.cafebabe.pochi.pairers;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Pairer;
import jp.cafebabe.birthmarks.utils.Namer;

public abstract class AbstractPairer<T extends Namer> implements Pairer<T> {
    private Configuration config;

    public AbstractPairer(Configuration config) {
        this.config = config;
    }

    public Configuration configuration() {
        return config;
    }
}
