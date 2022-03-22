package jp.cafebabe.birthmarks.io;

import io.vavr.control.Either;
import io.vavr.control.Try;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class ResourceFinder {
    private ResourceFinder() {
    }

    private Optional<URL> findImpl(String from) {
        return findFromPath(from)
                .or(() -> findFromClassLoader(from))
                .or(() -> findAsURL(from));
    }

    private Optional<URL> findFromPath(String from) {
        Path path = Path.of(from);
        if(Files.exists(path))
            return Optional.ofNullable(toURL(path));
        return Optional.empty();
    }

    private URL toURL(Path path) {
        return Try.of(() -> path.toUri().toURL())
                .get();
    }

    private Optional<URL> findFromClassLoader(String name) {
        return Optional.ofNullable(getClass().getResource(name));
    }

    private Optional<URL> findAsURL(String name) {
        return Try.of(() -> new URL(name))
                .toJavaOptional();
    }

    public static <T, P extends Parser<T>> Either<Throwable, T> parse(String from, P parser) {
        return find(from)
                .map(url -> parse(url, parser))
                .get();
    }

    private static <T, P extends Parser<T>> Either<Throwable, T> parse(URL url, P parser) {
        return Try.of(() -> parser.parse(url))
                .toEither();
    }

    public static Optional<URL> find(String from) {
        if(from == null)
            return Optional.empty();
        return new ResourceFinder().findImpl(from);
    }
}
