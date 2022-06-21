package jp.cafebabe.clpond.sink;

import io.vavr.API;
import io.vavr.Predicates;
import io.vavr.control.Try;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class DataSinkHelper {
    public static FileSystem buildFileSystem(Path path){
        Map<String, String> environment = Map.of(
                "create", "true", "encoding", "utf-8");
        return buildFileSystem(path, environment).get();
    }

    private static Optional<FileSystem> buildFileSystem(Path path, Map<String, String> environment) {
        return Try.of(() -> newFileSystem(path, environment))
                .toJavaOptional();
    }

    private static FileSystem newFileSystem(Path path, Map<String, String> environment) throws IOException {
        URI uri = Try.of(() -> new URI("jar", path.toUri().toString(), null))
                .toEither()
                .getOrElseThrow(use -> new IOException(use));
        return FileSystems.newFileSystem(uri, environment);
    }

    public static OutputStream newOutputStream(FileSystem system, Path path) throws IOException{
        FileSystemProvider provider = system.provider();
        return provider.newOutputStream(path);
    }
}
