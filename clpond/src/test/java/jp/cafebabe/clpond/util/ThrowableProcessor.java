package jp.cafebabe.clpond.util;

public interface ThrowableProcessor<E extends Exception> {
    void perform() throws E;
}
