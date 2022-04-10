package jp.cafebabe.pochi.birthmarks;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.BuilderFactory;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ExtractorBuilderFactory implements BuilderFactory<ExtractorBuilder, BirthmarkType> {
    private final List<String> availableTypes = List.of("uc");

    public Optional<ExtractorBuilder> builder(String type) {
        if(isKGramType(type))
            return Optional.of(new KGramBasedExtractorBuilder(findKValue(type)));
        else if(Objects.equals("uc", type))
            return Optional.of(new UsedClassesExtractorBuilder());
        return Optional.empty();
    }

    public Stream<ExtractorBuilder> builders() {
        return Stream.concat(IntStream.rangeClosed(1, 6)
                .mapToObj(KGramBasedExtractorBuilder::new),
                Stream.of(new UsedClassesExtractorBuilder()));
    }

    public Stream<BirthmarkType> availables() {
        return Stream.of(BirthmarkType.of("k-gram"), BirthmarkType.of("uc"));
    }

    private int findKValue(String type) {
        String number = type.replace("-gram", "");
        return Try.of(() -> Integer.parseInt(number))
                .getOrElse(-1);
    }

    public boolean available(String type) {
        return availableTypes.contains(type) ||
                isKGramType(type);
    }

    private boolean isKGramType(String type) {
        if(type.endsWith("-gram")) {
            String number = type.replace("-gram", "");
            return Try.of(() -> Integer.parseInt(number))
                    .map(n -> true)
                    .getOrElse(() -> false);
        }
        return false;
    }
}
