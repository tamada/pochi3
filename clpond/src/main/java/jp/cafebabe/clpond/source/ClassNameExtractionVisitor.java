package jp.cafebabe.clpond.source;

import jp.cafebabe.clpond.entities.ClassName;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.io.InputStream;

import static org.objectweb.asm.ClassReader.*;

class ClassNameExtractionVisitor extends ClassVisitor {
    private String className;

    public ClassNameExtractionVisitor() {
        super(Opcodes.ASM9);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
        this.className = name;
    }

    public static ClassName extractClassName(InputStream in) throws IOException{
        ClassNameExtractionVisitor visitor = new ClassNameExtractionVisitor();
        new ClassReader(in).accept(visitor, SKIP_CODE | SKIP_DEBUG | SKIP_FRAMES);
        return new ClassName(visitor.className);
    }
}
