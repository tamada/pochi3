package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.TaskBuilder;
import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.BirthmarkType;
import jp.cafebabe.birthmarks.extractors.Extractor;
import jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
import jp.cafebabe.pochi.birthmarks.VisitorBirthmarkExtractor;

public class UsedClassesExtractorBuilder implements ExtractorBuilder, TaskBuilder<Extractor, BirthmarkType> {

    @Override
    public BirthmarkType type() {
        return BirthmarkType.of("uc");
    }

    @Override
    public Extractor build(Configuration config) {
        return new VisitorBirthmarkExtractor(config, ExtractionVisitor::new);
    }

    @Override
    public String description() {
        return "Used classes birthmarks";
    }
}
