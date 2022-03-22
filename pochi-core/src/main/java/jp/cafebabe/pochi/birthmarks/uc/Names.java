package jp.cafebabe.pochi.birthmarks.uc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Names {
    private List<String> list = new ArrayList<>();

    public void add(String name){
        list.add(name);
    }

    public Stream<String> stream() {
        return list.stream().sorted();
    }
}
