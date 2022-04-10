package jp.cafebabe.pochi.cli.messages;

public interface Publisher<T> {
    void push(T item);
}
