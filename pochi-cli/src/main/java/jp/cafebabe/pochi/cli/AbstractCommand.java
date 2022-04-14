package jp.cafebabe.pochi.cli;

import jp.cafebabe.pochi.cli.messages.MessageCenter;
import jp.cafebabe.pochi.cli.messages.Publisher;

import java.util.concurrent.Callable;

public abstract class AbstractCommand implements Callable<Integer>, Publisher {
    private MessageCenter center = new MessageCenter();

    public AbstractCommand() {
        this.center = center;
    }

    public void push(String message) {
        this.center.push(message);
    }
}
