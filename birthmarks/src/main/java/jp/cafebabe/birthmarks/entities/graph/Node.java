package jp.cafebabe.birthmarks.entities.graph;

import java.util.stream.Stream;

public class Node<E> {
    private final E element;
    private final Graph<E> graph;

    Node(E element, Graph<E> graph) {
        this.element = element;
        this.graph = graph;
    }

    public E element() {
        return element;
    }

    public Stream<Node<E>> relatedNodes() {
        return graph.stream().filter(e -> graph.hasRelation(element, e))
                .map(e -> new Node<>(e, graph));
    }
}
