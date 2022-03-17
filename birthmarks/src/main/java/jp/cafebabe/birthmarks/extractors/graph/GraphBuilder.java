package jp.cafebabe.birthmarks.extractors.graph;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraphBuilder<E> {
    private List<E> elements;
    private int[][] relations;

    public GraphBuilder(Stream<E> stream) {
        this.elements = stream.collect(Collectors.toList());
        this.relations = new int[elements.size()][elements.size()];
    }

    public Graph<E> build() {
        return new Graph<>(elements, relations);
    }

    public GraphBuilder<E> relateMutually(E e1, E e2) {
        return relateMutually(e1, e2, 1);
    }

    public GraphBuilder<E> relateMutually(E e1, E e2, int weight) {
        checkArguments(elements, e1, e2);
        int index1 = elements.indexOf(e1);
        int index2 = elements.indexOf(e2);
        relations[index1][index2] = weight;
        relations[index2][index1] = weight;
        return this;
    }

    public GraphBuilder<E> relateTo(E e1, E e2, int weight) {
        checkArguments(elements, e1, e2);
        relations[elements.indexOf(e1)][elements.indexOf(e2)] = weight;
        return this;
    }

    public GraphBuilder<E> relateTo(E e1, E e2) {
        return relateTo(e1, e2, 1);
    }

    static <E> void checkArguments(List<E> elements, E e1, E e2) {
        if(!elements.contains(e1))
            throw new NoSuchElementException("the former argument not found");
        if(!elements.contains(e2))
            throw new NoSuchElementException("the latter argument not found");
    }
}
