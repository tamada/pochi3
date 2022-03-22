package jp.cafebabe.pochi.birthmarks;

import io.vavr.control.Try;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.kgram.KGramBasedExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.uc.UsedClassesExtractorBuilder;

import java.util.List;
import java.util.Optional;

public class ExtractorBuilderFactory {
    private List<String> availableTypes = List.of("uc");

    public Optional<ExtractorBuilder> builder(String type) {
        if(isKGramType(type))
            return Optional.of(new KGramBasedExtractorBuilder(findKValue(type)));
        return switch(type) {
            case "uc" -> Optional.of(new UsedClassesExtractorBuilder());
            default -> Optional.empty();
        };
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
