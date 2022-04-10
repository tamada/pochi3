package jp.cafebabe.pochi.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name="pochi", versionProvider = VersionProvider.class, description = "Java Birthmark Toolkit",
        subcommands = { HelpCommand.class, CompareCommand.class, ExtractCommand.class, InfoCommand.class, RunCommand.class, ShellCommand.class })
public class GlobalOptions implements Callable<Integer> {
    @Option(names = {"-c", "--config"}, description = "specify the configuration file.")
    private Path configPath;

    @Option(names = {"-h", "--help"}, description = "print this message", usageHelp = true)
    private boolean helpFlag;

    @Option(names = {"-V", "--version"}, description = "print version and exit", versionHelp = true)
    private boolean versionFlag;

    @Override
    public Integer call() throws Exception {
        System.out.println("GlobalOptions#call");
        return 0;
    }
}
