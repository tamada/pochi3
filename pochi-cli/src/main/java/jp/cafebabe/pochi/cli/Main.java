package jp.cafebabe.pochi.cli;

import picocli.CommandLine;
import picocli.CommandLine.HelpCommand;
import picocli.CommandLine.Mixin;

public class Main {
    @Mixin
    private GlobalOptions globalOpts = new GlobalOptions();

    public int run(String[] args) {
        var pochi = new Pochi();
        return new CommandLine(pochi)
                .addSubcommand("help", new HelpCommand())
                .addSubcommand("compare", new CompareCommand(pochi.globalOpts))
                .addSubcommand("extract", new ExtractCommand(pochi.globalOpts))
                .addSubcommand("info", new InfoCommand(pochi.globalOpts))
                .addSubcommand("run", new RunCommand(pochi.globalOpts))
                .addSubcommand("shell", new ShellCommand(pochi.globalOpts))
                .setCaseInsensitiveEnumValuesAllowed(true)
                .execute(args);
    }

    public static void main(String[] args) {
        int exitCode = new Main().run(args);
        Runtime.getRuntime().exit(exitCode);
    }
}
