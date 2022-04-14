package jp.cafebabe.pochi.cli;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.entities.Birthmarks;
import jp.cafebabe.birthmarks.io.BirthmarkParser;
import jp.cafebabe.pochi.cli.messages.Publisher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

class BirthmarkLoader {
    private Publisher publisher;

    public BirthmarkLoader(Publisher publisher) {
        this.publisher = publisher;
    }

    public Birthmarks load(List<Path> birthmarks) {
        BirthmarkParser parser = new BirthmarkParser();
        if (isReadFromStdin(birthmarks))
            return readFromStdin(parser, birthmarks);
        return readFromPaths(parser, birthmarks);
    }

    private boolean isReadFromStdin(List<Path> birthmarks) {
        return birthmarks.size() == 0 ||
                (birthmarks.size() == 1 && birthmarks.contains(Path.of("-")));
    }

    private Birthmarks readFromStdin(BirthmarkParser parser, List<Path> birthmarks) {
        return Try.of(() -> parser.parse(System.in))
                .get();
    }

    private Birthmarks readFromPaths(BirthmarkParser parser, List<Path> birthmarks) {
        return birthmarks.stream()
                .map(p -> readJson(parser, p))
                .reduce(new Birthmarks(Stream.empty()), (b1, b2) -> b1.merge(b2));
    }

    private Birthmarks readJson(BirthmarkParser parser, Path path) {
        return Try.withResources(() -> Files.newInputStream(path))
                .of(in -> parser.parse(in))
                .onFailure(publisher::push)
                .get();
    }
}
