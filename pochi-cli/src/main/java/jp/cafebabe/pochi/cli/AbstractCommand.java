package jp.cafebabe.pochi.cli;

import jp.cafebabe.pochi.cli.messages.MessageCenter;

import java.util.concurrent.Callable;

public abstract class AbstractCommand implements Callable<Integer> {
    private MessageCenter center = new MessageCenter();

    public void push(String message) {
        this.center.push(message);
    }
}
