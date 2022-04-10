package jp.cafebabe.pochi.cli;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

@Command(name = "shell", description = "start interactive shell for birthmarking")
public class ShellCommand extends AbstractCommand {
    @Option(names = {"--runtime"}, description = "specify the runtime environment.  Available: ruby, python, javascript, kotlin, scala.", required = true)
    private String runtime;

    @Override
    public Integer call() {
        System.out.println("shell");
        return 0;
    }
}
