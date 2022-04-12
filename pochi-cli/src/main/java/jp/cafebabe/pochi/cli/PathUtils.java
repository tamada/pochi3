package jp.cafebabe.pochi.cli;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class PathUtils {
    private Path pochiHome;

    public Path pochiHome(String relative) {
        return pochiHome().resolve(Path.of(relative));
    }

    public Path pochiHome() {
        if (pochiHome == null)
            pochiHome = findPochiHome();
        return pochiHome;
    }

    private Path findPochiHome() {
        var home = Path.of(System.getenv("HOME"));
        return Stream.concat(pochiHomeFromSysEnvProperty().stream(),
                        Stream.of(home.resolve(Path.of(".pochi")), Path.of("/usr/local/opt/pochi"),
                                Path.of("/opt/homebrew/opt/pochi"), Path.of(".")))
                .filter(Files::exists)
                .findFirst().get();
    }

    private Optional<Path> pochiHomeFromSysEnvProperty() {
        var pochiHome = Optional.ofNullable(System.getenv("POCHI_HOME"));
        var home = pochiHome.or(() -> Optional.ofNullable(System.getProperty("pochi.home")))
                .map(Path::of);
        System.out.println(home);
        return home;
    }
}
