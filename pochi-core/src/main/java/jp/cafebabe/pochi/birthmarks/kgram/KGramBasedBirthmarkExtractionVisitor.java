package jp.cafebabe.pochi.birthmarks.kgram;

import jp.cafebabe.birthmarks.config.Configuration;
import jp.cafebabe.birthmarks.entities.Birthmark;
import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.elements.ObjectElement;
import jp.cafebabe.birthmarks.entities.impl.Builder;
import jp.cafebabe.birthmarks.extractors.ExtractionException;
import jp.cafebabe.birthmarks.extractors.PochiClassVisitor;
import jp.cafebabe.clpond.entities.Entry;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class KGramBasedBirthmarkExtractionVisitor extends PochiClassVisitor {
    private final Map<String, List<Integer>> opcodes = new LinkedHashMap<>();
    private final KGramBuilder<Integer> builder;

    public KGramBasedBirthmarkExtractionVisitor(ClassVisitor parent, Configuration context, int kValue) {
        super(parent, context);
        builder = new KGramBuilder<>(kValue);
    }

    @Override
    public Birthmark build(Entry entry) throws ExtractionException {
        return new Builder().metadata(entry, builder.name())
                .build(buildElements(opcodes), ContainerType.List);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        List<Integer> list = createList(name + desc);
        return createVisitor(access, name, desc, signature, exceptions, list);
    }

    private List<Integer> createList(String key){
        List<Integer> list = opcodes.getOrDefault(key, new ArrayList<>());
        opcodes.put(key, list);
        return list;
    }

    private MethodVisitor createVisitor(int access, String name, String desc, String signature, String[] exceptions, List<Integer> list) {
        MethodVisitor visitor = super.visitMethod(access, name, desc, signature, exceptions);
        return new OpcodeExtractionMethodVisitor(visitor, list);
    }

    private Stream<Element> buildElements(Map<String, List<Integer>> map) {
        return map.values()
                .stream().flatMap(builder::build)
                .map(ObjectElement::new);
    }
}
