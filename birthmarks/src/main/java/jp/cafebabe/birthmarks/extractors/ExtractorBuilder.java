package jp.cafebabe.birthmarks.extractors;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;

import java.util.Objects;

public interface ExtractorBuilder extends TaskBuilder<Extractor, BirthmarkType> {
    Extractor build(Configuration config);

    BirthmarkType type();

    default boolean matchType(String name) {
        return Objects.equals(name, type().type());
    }
}
