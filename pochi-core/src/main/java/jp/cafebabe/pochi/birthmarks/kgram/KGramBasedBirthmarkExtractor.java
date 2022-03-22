package jp.cafebabe.pochi.birthmarks.kgram;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.extractors.AbstractExtractor;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import org.objectweb.asm.ClassVisitor;

public class KGramBasedBirthmarkExtractor extends AbstractExtractor {
    private final int kValue;

    public KGramBasedBirthmarkExtractor(int kValue, Configuration config) {
        super(config);
        this.kValue = kValue;
    }

    @Override
    public PochiClassVisitor visitor(ClassVisitor parent) {
        return new KGramBasedBirthmarkExtractionVisitor(parent, configuration(), kValue);
    }
}
