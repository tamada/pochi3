package jp.cafebabe.birthmarks.extractors.graph;

import jp.cafebabe.birthmarks.comparators.Pair;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class Graph<E> implements Serializable {
    private static final long serialVersionUID = -7264502399177421550L;

    private List<E> elements;
    private int[][] relations;

    Graph(List<E> elements, int[][] relations) {
        this.elements = elements;
        this.relations = relations;
    }

    public int size() {
        return elements.size();
    }

    public Stream<E> stream() {
        return elements.stream();
    }

    public Stream<Node<E>> nodes() {
        return elements.stream()
                .map(e -> new Node<>(e, this));
    }

    public Node<E> node(E item) {
        if(!elements.contains(item))
            throw new NoSuchElementException(item + ": no such element");
        return new Node<>(item, this);
    }

    public Node<E> node(int index) {
        return new Node<>(elements.get(index), this);
    }

    public int relation(E e1, E e2) {
        var pair = indexPair(e1, e2);
        return relations[pair.left()][pair.right()];
    }

    public boolean hasRelation(E e1, E e2) {
        var pair = indexPair(e1, e2);
        return relations[pair.left()][pair.right()] != 0;
    }

    private Pair<Integer, Integer> indexPair(E e1, E e2) {
        GraphBuilder.checkArguments(elements, e1, e2);
        return new Pair<>(elements.indexOf(e1), elements.indexOf(e2));
    }
}
