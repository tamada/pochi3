package jp.cafebabe.birthmarks.extractors.impl;

import jp.cafebabe.birthmarks.extractors.ContainerType;
import jp.cafebabe.birthmarks.extractors.Element;
import jp.cafebabe.birthmarks.extractors.Metadata;
import jp.cafebabe.birthmarks.extractors.graph.Graph;

import java.io.Serializable;
import java.util.stream.Stream;

public class GraphBirthmark extends AbstractBirthmark implements Serializable {
    private static final long serialVersionUID = -7181552926875635061L;

    private Graph<Element> graph;

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
    public int elementCount() {
        return graph.size();
    }
}
