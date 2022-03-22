package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.VisitorBirthmarkExtractor;

public class UsedClassesExtractorBuilder implements ExtractorBuilder {

    @Override
    public BirthmarkType type() {
        return BirthmarkType.of("uc");
    }

    @Override
    public Extractor build(Configuration config) {
        return new VisitorBirthmarkExtractor(config, ExtractionVisitor::new);
    }
}
