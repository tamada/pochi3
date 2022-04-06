package jp.cafebabe.birthmarks.events;

public class BirthmarkEvent {
    private final Id id;
    private final Phase phase;
    private Object source;

    private BirthmarkEvent(Id id, Phase phase, Object source) {
        this.id = id;
        this.phase = phase;
        this.source = source;
    }

    public Id id() {
        return id;
    }

    public Phase phase() {
        return phase;
    }

    public Object source() {
        return source;
    }

    public static BirthmarkEvent of(Id id, Phase phase, Object source) {
        return new BirthmarkEvent(id, phase, source);
    }

    public static enum Phase {
        Before,
        After,
        BeforeEach,
        AfterEach,
    }

    public static enum Id {
        Extraction,
        Comparison,
        Parsing,
    }
}
