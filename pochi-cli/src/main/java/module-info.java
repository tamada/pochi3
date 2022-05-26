/**
 * This module contains the classes related to cli interfaces of pochi command.
 */
module jp.cafebabe.pochi.cli {
    requires com.google.gson;
    requires io.vavr;
    requires info.picocli;
    requires jp.cafebabe.pochi.clpond;
    requires jp.cafebabe.pochi.core;
    requires jp.cafebabe.pochi.birthmarks;
    requires java.scripting;
    requires org.objectweb.asm;

    opens jp.cafebabe.pochi.cli to info.picocli;
}