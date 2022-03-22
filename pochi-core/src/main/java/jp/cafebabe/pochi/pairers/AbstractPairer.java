package jp.cafebabe.pochi.pairers;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.pairers.Pairer;

public abstract class AbstractPairer implements Pairer {
    private Configuration config;

    public AbstractPairer(Configuration config) {
        this.config = config;
    }

    public Configuration configuration() {
        return config;
    }
}
