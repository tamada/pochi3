package jp.cafebabe.pochi.cli;

import picocli.CommandLine;

public class Main {
    private GlobalOptions globalOpts = new GlobalOptions();

    public Integer call(String[] args) {
        return new CommandLine(globalOpts)
                .execute(args);
    }

    public static void main(String[] args) {
        int exitCode = new Main().call(args);
        System.exit(exitCode);
    }
}
