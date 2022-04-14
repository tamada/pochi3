package jp.cafebabe.pochi.cli;

import io.vavr.control.Either;
import io.vavr.control.Try;
import jp.cafebabe.pochi.cli.messages.Publisher;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class DestCreator {
    private Publisher publisher;

    public DestCreator(Publisher publisher) {
        this.publisher = publisher;
    }

    public void dest(Optional<String> dest, Consumer<PrintWriter> action) {
        var d = dest(dest);
        action.accept(d);
        d.flush();
    }

    public PrintWriter dest(Optional<String> dest) {
        return createDest(dest)
                .peekLeft(message -> publisher.push(message))
                .get();
    }

    private Either<String, PrintWriter> createDest(Optional<String> dest) {
        return dest.map(this::openDest)
                .orElseGet(this::systemOut);
    }

    private Either<String, PrintWriter> systemOut() {
        return Either.right(new PrintWriter(System.out));
    }

    private Either<String, PrintWriter> openDest(String dest) {
        if(Objects.equals(dest, "-"))
            return systemOut();
        return Try.of(() -> new PrintWriter(Files.newBufferedWriter(Path.of(dest))))
                .toEither().mapLeft(e -> e.getMessage());
    }
}
