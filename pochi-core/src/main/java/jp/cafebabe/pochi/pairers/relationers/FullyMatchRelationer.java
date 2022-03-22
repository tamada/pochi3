package jp.cafebabe.pochi.pairers.relationers;

import jp.cafebabe.birthmarks.pairers.Relationer;

import java.util.Objects;

public class FullyMatchRelationer implements Relationer {

    @Override
    public boolean isRelate(String name1, String name2) {
        return Objects.equals(name1, name2);
    }
}
