package jp.cafebabe.pochi.cli.messages;

public interface Publisher {
    void push(String item);

    default void push(AnsiColors color, String message) {
        push(color.decorate(message));
    }

    default void push(Throwable t) {
        push(AnsiColors.RED_BOLD, t);
    }

    default void push(AnsiColors color, Throwable t) {
        push(color.decoratef("Error: %s (%s)", t.getMessage(), t.getClass().getName()));
    }

    default void pushf(String formatter, Object... params) {
        push(String.format(formatter, params));
    }

    default void pushf(AnsiColors color, String formatter, Object... params) {
        push(color, String.format(formatter, params));
    }
}
