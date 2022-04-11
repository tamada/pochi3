module jp.cafebabe.pochi.cli {
    requires io.vavr;
    requires org.objectweb.asm;
    requires info.picocli;
    requires jp.cafebabe.pochi.core;
    requires jp.cafebabe.pochi.birthmarks;

    opens jp.cafebabe.pochi.cli to info.picocli;
}