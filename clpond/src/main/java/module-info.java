module jp.cafebabe.pochi.clpond {
    requires io.vavr;
    requires org.objectweb.asm;

    exports jp.cafebabe.clpond.entities;
    exports jp.cafebabe.clpond.source;
    exports jp.cafebabe.clpond.sink;
    exports jp.cafebabe.clpond.source.factories;
    exports jp.cafebabe.clpond.sink.factories;
}