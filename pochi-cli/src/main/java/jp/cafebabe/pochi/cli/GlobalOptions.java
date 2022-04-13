package jp.cafebabe.pochi.cli;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.config.ConfigurationParser;
import jp.cafebabe.pochi.cli.messages.MessageCenter;
import picocli.CommandLine.Option;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class GlobalOptions {
    @Option(names = {"-c", "--config"}, description = "specify the configuration file.")
    private Optional<Path> configPath = Optional.empty();

    @Option(names = {"-h", "--help"}, description = "print this message", usageHelp = true)
    private boolean helpFlag;

    @Option(names = {"-V", "--version"}, description = "print version and exit", versionHelp = true)
    private boolean versionFlag;

    private PathUtils pathUtils = new PathUtils();
    MessageCenter center;

    public Path pochiHome() {
        return pathUtils.pochiHome();
    }

    public Optional<Path> configPath() {
        return Stream.concat(configPath.stream(),
                        Stream.of(Path.of(".config.json"),
                                pathUtils.pochiHome("resources/config.json")))
                .filter(Files::exists)
                .findFirst();
    }

    public Configuration config() {
        return openConfig()
                .orElseGet(() -> Configuration.defaultConfig());
    }

    private Optional<Configuration> openConfig() {
        return configPath()
                .flatMap(this::openStream)
                .flatMap(in -> Try.of(() -> new ConfigurationParser().parse(in))
                        .onFailure(t -> center.push(t))
                        .toJavaOptional());
    }

    private Optional<InputStream> openStream(Path p) {
        return Try.of(() -> Files.newInputStream(p))
                .onFailure(t -> center.push(t))
                .toJavaOptional();
    }
}
