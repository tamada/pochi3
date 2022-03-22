package jp.cafebabe.pochi.birthmarks.uc;

import jp.cafebabe.birthmarks.config.Configuration;
import org.objectweb.asm.Type;

import java.util.Arrays;
import java.util.stream.Stream;

class Helper {
    private final Names names = new Names();
    private final Configuration context;

    public Helper(Configuration context){
        this.context = context;
    }

    public Stream<String> list() {
        return names.stream();
    }

    public void addAll(String[] names){
        if(names != null)
            Arrays.stream(names)
            .forEach(this::add);
    }

    public void add(String name){
        String normalizedName = normalize(name);
        if(context.match(normalizedName))
            names.add(normalizedName);
    }

    public void add(Type type){
        Type strippedType = stripType(type);
        if(strippedType.getSort() == Type.OBJECT)
            add(strippedType.getClassName());
    }

    String normalize(String givenName){
        String name = givenName;
        if(name.startsWith("L") && name.endsWith(";"))
            name = name.substring(1, name.length() - 1);
        return name.replace('/', '.');
    }

    Type stripType(Type givenType){
        Type type = givenType;
        while(type.getSort() == Type.ARRAY)
            type = type.getElementType();
        return type;
    }
}
