module jp.cafebabe.pochi.cli {
    requires com.google.gson;
    requires io.vavr;
    requires info.picocli;
    requires jp.cafebabe.pochi.clpond;
    requires jp.cafebabe.pochi.core;
    requires jp.cafebabe.pochi.birthmarks;
    requires org.objectweb.asm;

    opens jp.cafebabe.pochi.cli to info.picocli;
}