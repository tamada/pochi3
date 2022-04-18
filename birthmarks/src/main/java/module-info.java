module jp.cafebabe.pochi.birthmarks {
    requires io.vavr;
    requires org.objectweb.asm;
    requires com.google.gson;
    requires jp.cafebabe.pochi.clpond;

    exports jp.cafebabe.birthmarks;
    exports jp.cafebabe.birthmarks.comparators;
    exports jp.cafebabe.birthmarks.config;
    exports jp.cafebabe.birthmarks.entities;
    exports jp.cafebabe.birthmarks.entities.elements;
    exports jp.cafebabe.birthmarks.entities.graph;
    exports jp.cafebabe.birthmarks.entities.impl;
    exports jp.cafebabe.birthmarks.events;
    exports jp.cafebabe.birthmarks.extractors;
    exports jp.cafebabe.birthmarks.io;
    exports jp.cafebabe.birthmarks.pairers;
    exports jp.cafebabe.birthmarks.utils;

    opens jp.cafebabe.birthmarks.comparators;
    opens jp.cafebabe.birthmarks.config;
    opens jp.cafebabe.birthmarks.events;
    opens jp.cafebabe.birthmarks.entities;
    opens jp.cafebabe.birthmarks.entities.elements;
    opens jp.cafebabe.birthmarks.entities.impl;
    opens jp.cafebabe.birthmarks.io;
    opens jp.cafebabe.birthmarks.pairers;
}