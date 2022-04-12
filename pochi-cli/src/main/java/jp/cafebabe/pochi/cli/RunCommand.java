package jp.cafebabe.pochi.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.nio.file.Path;
import java.util.List;

@Command(name = "run", description = "run the given scripts for birthmarking")
public class RunCommand extends AbstractCommand {
    @Option(names = {"--runtime"}, description = "specify the runtime environment.  Available: ruby, python, javascript, kotlin, scala.", required = true)
    private String runtime;

    @Parameters(paramLabel = "SCRIPTs...", arity = "1..*", description = "script files for birthmarking")
    private List<Path> scripts;

    public RunCommand() {
        this(new GlobalOptions());
    }

    public RunCommand(GlobalOptions globalOptions) {
        super(globalOptions);
    }

    @Override
    public Integer call() {
        System.out.println("run");
        return 0;
    }
}
