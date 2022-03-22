package jp.cafebabe.birthmarks.extractors;

import jp.cafebabe.birthmarks.BirthmarkException;
import jp.cafebabe.clpond.entities.Entry;

public class ExtractionException extends BirthmarkException {
    private Entry from;

    public ExtractionException(Throwable parent, Entry from) {
        super(parent);
        this.from = from;
    }

    public ExtractionException(Entry from) {
        this.from = from;
    }

    public ExtractionException(String message, Entry from) {
        super(message);
        this.from = from;
    }

    public Entry from() {
        return from;
    }
}
