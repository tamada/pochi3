package jp.cafebabe.birthmarks.extractors;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;

public interface ExtractorBuilder {
    Extractor build(Configuration config);

    BirthmarkType type();
}
