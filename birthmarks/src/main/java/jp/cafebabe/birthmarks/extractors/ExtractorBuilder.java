package jp.cafebabe.birthmarks.extractors;

public interface ExtractorBuilder {
    Extractor build();

    BirthmarkType type();
}
