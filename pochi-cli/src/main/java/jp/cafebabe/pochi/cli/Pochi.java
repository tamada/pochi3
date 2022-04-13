package jp.cafebabe.pochi.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

import java.util.concurrent.Callable;

@Command(name="pochi", versionProvider = VersionProvider.class, description = "Java Birthmark Toolkit")
public class Pochi implements Callable<Integer> {
    @Mixin
    GlobalOptions globalOpts = new GlobalOptions();

    @Override
    public Integer call() {
        return 0;
    }
}
