package jp.cafebabe.pochi.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

import java.nio.file.Path;
import java.util.List;

@Command(name = "run", description = "run the given scripts for birthmarking")
public class RunCommand extends AbstractCommand {
    @Option(names = {"--runtime"}, description = "specify the runtime environment.  Available: ruby, python, javascript, kotlin, scala.", required = true)
    private String runtime;

    @Parameters(paramLabel = "SCRIPTs...", arity = "1..*", description = "script files for birthmarking")
    private List<Path> scripts;

    @ParentCommand
    private Pochi pochi;

    @Override
    public Integer call() {
        System.out.println("run");
        return 0;
    }
}
