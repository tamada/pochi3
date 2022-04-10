package jp.cafebabe.pochi.cli;

import io.vavr.control.Either;
import io.vavr.control.Try;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class DestCreator {
    public static Either<String, PrintWriter> createDest(Optional<String> dest) {
        return dest.map(DestCreator::openDest)
                .orElseGet(() -> Either.right(new PrintWriter(System.out)));
    }

    private static Either<String, PrintWriter> openDest(String dest) {
        return Try.of(() -> new PrintWriter(Files.newBufferedWriter(Path.of(dest))))
                .toEither().mapLeft(e -> e.getMessage());
    }
}
