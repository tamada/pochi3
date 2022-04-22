package jp.cafebabe.birthmarks.extractors;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;

import java.util.Objects;

public interface ExtractorBuilder extends TaskBuilder<Extractor, BirthmarkType> {
    BirthmarkType type();

    Extractor build(Configuration config);

    default boolean matchType(String name) {
        return Objects.equals(name, type().string());
    }
}
