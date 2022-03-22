package jp.cafebabe.birthmarks.extractors;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.clpond.entities.Entry;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public abstract class PochiClassVisitor extends ClassVisitor {
    private Configuration context;

    public PochiClassVisitor(ClassVisitor parent, Configuration config) {
        super(Opcodes.ASM9, parent);
        this.context = config;
    }

    protected Configuration context() {
        return context;
    }

    public abstract Birthmark build(Entry entry) throws ExtractionException;
}
