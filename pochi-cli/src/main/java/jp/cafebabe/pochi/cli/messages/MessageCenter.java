package jp.cafebabe.pochi.cli.messages;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MessageCenter implements Publisher<String> {
    private List<String> messages = new ArrayList<>();

    public void push(String message) {
        messages.add(message);
    }

    public void push(String message, AnsiColors color) {
        messages.add(color.decorate(message));
    }

    public void push(Throwable t) {
        push(t, AnsiColors.RED_BOLD);
    }

    public void push(Throwable t, AnsiColors color) {
        messages.add(color.decoratef("Error: %s (%s)", t.getMessage(), t.getClass().getName()));
    }

    public void printAll(PrintStream out) {
        printAll(new PrintWriter(out));
    }

    public void printAll(PrintWriter out) {
        messages.forEach(out::println);
        out.flush();
    }
}
