package jp.cafebabe.pochi.cli;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.pochi.cli.messages.MessageCenter;
import jp.cafebabe.pochi.cli.messages.Publisher;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

import java.util.concurrent.Callable;

@Command(name="pochi", versionProvider = VersionProvider.class, description = "Java Birthmark Toolkit")
public class Pochi implements Callable<Integer>, Publisher {
    private MessageCenter center = new MessageCenter();

    @Spec
    private CommandSpec spec;

    @Mixin
    private GlobalOptions globalOpts = new GlobalOptions();

    public Pochi() {
    }

    public Configuration config() {
        return globalOpts.config();
    }

    @Override
    public Integer call() {
        spec.commandLine().usage(System.out);
        return 0;
    }

    @Override
    public void push(String item) {
        center.push(item);
    }

    public int printAll() {
        return printAll(0);
    }

    public int printAll(int status) {
        center.printAll(System.out);
        return status;
    }
}
