package jp.cafebabe.pochi.birthmarks;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ExtractorBuilderFactory {
    private final List<String> availableTypes = List.of("uc");

    public Optional<ExtractorBuilder> builder(String type) {
        if(isKGramType(type))
            return Optional.of(new KGramBasedExtractorBuilder(findKValue(type)));
        else if(Objects.equals("uc", type))
            return Optional.of(new UsedClassesExtractorBuilder());
        return Optional.empty();
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
