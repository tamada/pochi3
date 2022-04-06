package jp.cafebabe.birthmarks.events;

public interface BirthmarkListener {
    void beforeEach(BirthmarkEvent e);

    void afterEach(BirthmarkEvent e);

    void before(BirthmarkEvent e);

    void after(BirthmarkEvent e);
}
