package jp.cafebabe.pochi.cli;

import jp.cafebabe.pochi.cli.messages.AnsiColors;
import jp.cafebabe.pochi.cli.messages.MessageCenter;
import jp.cafebabe.pochi.cli.messages.Publisher;

import java.util.concurrent.Callable;

public abstract class AbstractCommand implements Callable<Integer>, Publisher<String> {
    private MessageCenter center = new MessageCenter();
    GlobalOptions globalOptions;

    public AbstractCommand(GlobalOptions globalOptions) {
        this.globalOptions = globalOptions;
        globalOptions.center = center;
    }

    public void push(String message) {
        this.center.push(message);
    }

    public void push(AnsiColors color, String message) {
        this.center.push(color.decorate(message));
    }

    public void push(Throwable t) {
        center.push(t);
    }

    public void pushf(AnsiColors color, String formatter, Object... labels) {
        this.center.push(color.decoratef(formatter, labels));
    }

    public void pushf(String formatter, Object... labels) {
        this.center.push(String.format(formatter, labels));
    }

    public int printAll() {
        return printAll(0);
    }

    public int printAll(int status) {
        center.printAll(System.out);
        return status;
    }
}
