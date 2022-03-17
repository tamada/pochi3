module jp.cafebabe.pochi.birthmarks {
    requires io.vavr;
    requires com.google.gson;
    requires jp.cafebabe.pochi.clpond;

    exports jp.cafebabe.birthmarks.extractors;
    exports jp.cafebabe.birthmarks.extractors.elements;
    exports jp.cafebabe.birthmarks.extractors.graph;
    exports jp.cafebabe.birthmarks.extractors.impl;
    exports jp.cafebabe.birthmarks.comparators;
    exports jp.cafebabe.birthmarks.pairers;
}