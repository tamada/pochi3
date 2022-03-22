package jp.cafebabe.pochi.birthmarks;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.extractors.AbstractExtractor;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.birthmarks.utils.BiSupplier;
import org.objectweb.asm.ClassVisitor;

public class VisitorBirthmarkExtractor extends AbstractExtractor {
    private BiSupplier<ClassVisitor, Configuration, PochiClassVisitor> supplier;

    public VisitorBirthmarkExtractor(Configuration config,
                                     BiSupplier<ClassVisitor, Configuration, PochiClassVisitor> supplier) {
        super(config);
        this.supplier = supplier;
    }

    @Override
    public PochiClassVisitor visitor(ClassVisitor parent) {
        return supplier.get(parent, configuration());
    }
}
