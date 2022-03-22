package jp.cafebabe.pochi.pairers.relationers;

import jp.cafebabe.birthmarks.pairers.Relationer;

import java.util.Objects;

public class ClassNameRelationer implements Relationer {
    @Override
    public boolean isRelate(String name1, String name2) {
        return Objects.equals(extractClassName(name1), extractClassName(name2));
    }

    private String extractClassName(String name) {
         int index = name.lastIndexOf('.');
         if(index >= 0)
             return name.substring(index + 1);
         return name;
    }
}
