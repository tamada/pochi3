package jp.cafebabe.birthmarks.entities.impl;

import jp.cafebabe.birthmarks.entities.ContainerType;
import jp.cafebabe.birthmarks.entities.Element;
import jp.cafebabe.birthmarks.entities.Metadata;
import jp.cafebabe.birthmarks.entities.graph.Graph;

import java.io.Serial;
import java.io.Serializable;
import java.util.stream.Stream;

public class GraphBirthmark extends AbstractBirthmark implements Serializable {
    @Serial
    private static final long serialVersionUID = -7181552926875635061L;

    private final Graph<Element> graph;

    GraphBirthmark(Metadata metadata, Graph<Element> graph) {
        super(metadata);
        this.graph = graph;
    }

    @Override
    public Stream<Element> stream() {
        return graph.stream();
    }

    @Override
    public final ContainerType containerType() {
        return ContainerType.Graph;
    }

    @Override
    public long size() {
        return graph.size();
    }
}
