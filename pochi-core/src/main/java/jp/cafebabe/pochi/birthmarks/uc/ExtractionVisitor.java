package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.birthmarks.entities.impl.Builder;
import jp.cafebabe.clpond.entities.Entry;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureReader;

import java.util.Arrays;

class ExtractionVisitor extends PochiClassVisitor {
    protected Helper helper;

    public ExtractionVisitor(ClassVisitor visitor, Configuration context){
        super(visitor, context);
        this.helper = new Helper(context);
    }

    @Override
    public Birthmark build(Entry entry) {
        return new Builder()
                .metadata(entry, "uc")
                .build(helper.list(), ContainerType.List);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces){
        addSignatureClass(signature);
        helper.add(superName);
        helper.addAll(interfaces);
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc,
            String signature, Object value){
        addDescriptor(desc);
        addSignatureClass(signature);
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions){
        helper.addAll(exceptions);
        addMethodDescriptor(desc);
        addSignatureClass(signature);
        return new ExtractionMethodVisitor(
                super.visitMethod(access, name, desc, signature, exceptions), helper, this);
    }

    void addSignatureClass(String signature){
        if(signature == null)
            return;
        SignatureReader reader = new SignatureReader(signature);
        reader.accept(new SignatureWriter(helper));
    }

    void addMethodDescriptor(String desc){
        helper.add(Type.getReturnType(desc));
        Type[] args = Type.getArgumentTypes(desc);
        Arrays.stream(args)
        .forEach(helper::add);
    }

    void addDescriptor(String desc){
        helper.add(Type.getType(desc));
    }
}
