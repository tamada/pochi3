package jp.cafebabe.pochi.cli;

import jp.cafebabe.pochi.cli.messages.MessageCenter;

import java.util.concurrent.Callable;

public abstract class AbstractCommand implements Callable<Integer> {
    private MessageCenter center = new MessageCenter();
    GlobalOptions globalOptions;

    public AbstractCommand(GlobalOptions globalOptions) {
        this.globalOptions = globalOptions;
        globalOptions.center = center;
    }

    public void push(String message) {
        this.center.push(message);
    }

    public void push(Throwable t) {
        center.push(t);
    }

    public void pushf(String formatter, Object... labels) {
        this.center.push(String.format(formatter, labels));
    }

    public int printAll() {
        center.printAll(System.out);
        return 0;
    }
}
