package jp.cafebabe.pochi.cli.messages;

import java.util.List;

public class MessageCenter implements Publisher<String> {
    private List<String> messages;

    public void push(String message) {
        messages.add(message);
    }
}
