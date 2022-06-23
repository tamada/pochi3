package jp.cafebabe.pochi.cli;

import picocli.CommandLine;
import picocli.CommandLine.HelpCommand;

public class Main {
    public int run(String[] args) {
        var pochi = new Pochi();
        return new CommandLine(pochi)
                .setCaseInsensitiveEnumValuesAllowed(true)
                .execute(args);
    }

    public static void main(String[] args) {
        int exitCode = new Main().run(args);
        Runtime.getRuntime().exit(exitCode);
    }
}
