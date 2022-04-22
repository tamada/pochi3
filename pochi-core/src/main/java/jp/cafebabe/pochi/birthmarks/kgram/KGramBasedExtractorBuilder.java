package jp.cafebabe.pochi.birthmarks.kgram;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;

public class KGramBasedExtractorBuilder implements ExtractorBuilder, TaskBuilder<Extractor, BirthmarkType> {
    private final int kValue;

    public KGramBasedExtractorBuilder() {
        this(3);
    }

    public KGramBasedExtractorBuilder(int kValue) {
        this.kValue = kValue;
    }

    @Override
    public BirthmarkType type() {
        return BirthmarkType.of(kValue + "-gram");
    }

    @Override
    public Extractor build(Configuration config) {
        return new KGramBasedBirthmarkExtractor(kValue, config);
    }

    @Override
    public String description() {
        return String.format("opcodes' %d-gram based birthmark", kValue);
    }
}
