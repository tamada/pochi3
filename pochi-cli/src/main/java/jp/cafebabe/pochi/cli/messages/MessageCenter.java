package jp.cafebabe.pochi.cli.messages;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MessageCenter implements Publisher {
    private List<String> messages = new ArrayList<>();

    public void push(String message) {
        messages.add(message);
    }

    public void printAll(PrintStream out) {
        printAll(new PrintWriter(out));
    }

    public void printAll(PrintWriter out) {
        messages.forEach(out::println);
        out.flush();
    }
}
