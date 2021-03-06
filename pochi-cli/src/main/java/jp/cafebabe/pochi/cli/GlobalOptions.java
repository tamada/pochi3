package jp.cafebabe.pochi.cli;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.config.ConfigurationParser;
import jp.cafebabe.pochi.cli.messages.MessageCenter;
import picocli.CommandLine.Option;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class GlobalOptions {
    @Option(names = {"-C", "--config"}, description = "specify the configuration file.")
    private Optional<Path> configPath = Optional.empty();

    @Option(names = {"-h", "--help"}, description = "print this message and exit.", usageHelp = true)
    private boolean helpFlag;

    @Option(names = {"-V", "--version"}, description = "print version and exit.", versionHelp = true)
    private boolean versionFlag;

    @Option(names = {"-P", "--property"}, description = "specify the property key and its value")
    private Map<String, String> properties = new HashMap<>();

    private PathUtils pathUtils = new PathUtils();
    private Configuration config;
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
        if(config == null) {
            config = openConfig()
                    .orElseGet(() -> Configuration.defaultConfig(properties));
        }
        return config;
    }

    private Optional<Configuration> openConfig() {
        return configPath()
                .flatMap(this::openStream)
                .flatMap(in -> Try.of(() -> new ConfigurationParser().parse(in, properties))
                        .onFailure(t -> center.push(t))
                        .toJavaOptional());
    }

    private Optional<InputStream> openStream(Path p) {
        return Try.of(() -> Files.newInputStream(p))
                .onFailure(t -> center.push(t))
                .toJavaOptional();
    }
}
