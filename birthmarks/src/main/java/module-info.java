module jp.cafebabe.pochi.birthmarks {
    requires io.vavr;
    requires org.objectweb.asm;
    requires com.google.gson;
    requires jp.cafebabe.pochi.clpond;

    exports jp.cafebabe.birthmarks;
    exports jp.cafebabe.birthmarks.comparators;
    exports jp.cafebabe.birthmarks.config;
    exports jp.cafebabe.birthmarks.extractors;
    exports jp.cafebabe.birthmarks.entities.elements;
    exports jp.cafebabe.birthmarks.entities.graph;
    exports jp.cafebabe.birthmarks.entities.impl;
    exports jp.cafebabe.birthmarks.io;
    exports jp.cafebabe.birthmarks.pairers;
    exports jp.cafebabe.birthmarks.utils;
    exports jp.cafebabe.birthmarks.entities;

    uses jp.cafebabe.birthmarks.comparators.ComparatorBuilder;
    uses jp.cafebabe.birthmarks.extractors.ExtractorBuilder;
    uses jp.cafebabe.birthmarks.pairers.PairerBuilder;
}