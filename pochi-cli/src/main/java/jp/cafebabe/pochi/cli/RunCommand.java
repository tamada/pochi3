package jp.cafebabe.pochi.cli;

import io.vavr.control.Try;
import jp.cafebabe.pochi.cli.messages.AnsiColors;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Command(name = "run", description = "run the given scripts for birthmarking.")
public class RunCommand extends AbstractCommand {
    @Option(names = {"-E", "--engine"}, paramLabel = "ENGINE_NAME",
            description = "specify the script engine.  Available values are shown in info subcommand. Default: groovy.", defaultValue = "groovy")
    private String runtime = "groovy";

    @Option(names = {"-e"}, paramLabel = "ONE_LINER", description = "specify a command line script.")
    private String oneliner = null;

    @Parameters(index = "0", paramLabel = "SCRIPT", arity = "0..1",
            description = "specify a script file for birthmarking.")
    private Path script;

    @Parameters(index = "1..", paramLabel = "ARGS", description = "args for the script file.")
    private List<String> args = new ArrayList<>();

    @ParentCommand
    private Pochi pochi;

    @Override
    public Integer call() {
        printFactories();

        if(!isValid())
            return 1;
        var engine = buildScriptEngine(runtime);
        engine.map(e -> execute(e))
                .orElseGet(() -> pushError(2, "%s: script engine not found", runtime));
        return pochi.flush();
    }

    private int pushError(int status, String formatter, Object... labels) {
        pushf(AnsiColors.RED_BOLD, formatter, labels);
        return status;
    }

    private boolean isValid() {
        if(oneliner == null && script == null)
            return pushError(3, "No script file and command-line script were specified") != 0;
        if(oneliner != null && script != null)
            return pushError(4, "Both of script file and command-line script were specified") != 0;
        if(script != null && !Files.exists(script))
            return pushError(5, "%s: file not found", script) != 0;
        return true;
    }

    private int execute(ScriptEngine engine) {
        return Try.withResources(() -> openReader(oneliner, script))
                .of(in -> executeImpl(engine, in))
                .onFailure(this::push)
                .get();
    }

    private Reader openReader(String oneliner, Path script) {
        if(oneliner != null)
            return new StringReader(oneliner);
        return Try.of(() -> Files.newBufferedReader(script))
                .get();
    }

    private int executeImpl(ScriptEngine engine, Reader in) {
        return Try.of(() -> engine.eval(in))
                .onFailure(this::push)
                .onSuccess(this::printIfNeeded)
                .map(r -> 0)
                .get();
    }

    private void printIfNeeded(Object result) {
        if(result == null || !(result instanceof Void))
            return;
        pushf("Result: %s", result.toString());
    }

    private Optional<ScriptEngine> buildScriptEngine(String runtime) {
        var manager = new ScriptEngineManager();
        var result = Optional.ofNullable(manager.getEngineByName(runtime));
        result.ifPresent(engine -> initialize(engine));
        return result;
    }

    private void initialize(ScriptEngine engine) {
        engine.put("args", args);
        engine.put("FILE", script != null? script: "ONE_LINER");
        engine.put("pochi", new BirthmarkSystem(pochi));
        engine.put("jsonier", new Jsonier());
    }

    private void printFactories() {
        var manager = new ScriptEngineManager();
        manager.getEngineFactories().stream()
                .forEach(this::printFactory);
    }

    private void printFactory(ScriptEngineFactory factory) {
        pochi.pushf("%10s %12s %s", factory.getLanguageName(),
                "(" + factory.getEngineName() +")", factory.getLanguageVersion());
    }
}
