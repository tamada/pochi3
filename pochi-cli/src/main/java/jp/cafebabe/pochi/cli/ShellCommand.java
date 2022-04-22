package jp.cafebabe.pochi.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;

@Command(name = "shell", description = "start interactive shell for birthmarking (not implemented yet).")
public class ShellCommand extends AbstractCommand {
    @Option(names = {"--runtime"}, description = "specify the runtime environment.  Available: ruby, python, javascript, kotlin, scala.", required = true)
    private String runtime;

    @ParentCommand
    private Pochi pochi;

    @Override
    public Integer call() {
        System.out.println("shell: not implemented yet");
        return 0;
    }
}
