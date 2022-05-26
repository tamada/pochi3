/**
 * <p>This module contains reading/writing the classes from/to class files, jar files, war files, and directories.</p>
 * <p>The typical usage is as follows.</p>
 * <pre><code>
 * // Reading
 * Path sourcePath = Paths.get("path/of/source/file/or/directory");
 * DataSourceBuilder builder = DataSourceBuilder.instance();
 * try(DataSource source = builder.build(sourcePath)){
 *     // Storing
 *     Path outputPath = Paths.get("path/of/output/file.jar");
 *     DataSinkFactory sinkFactory = DataSinkFactory.instance();
 *     try(DataSink sink = sinkFactory.build(outputPath)){
 *         sink.consume(source);
 *         //     above lines means following code.
 *         // source.forEach(entry -> {
 *         //     try{ sink.consume(entry); }
 *         //     catch(Exception e){ }
 *         // });
 *     }
 * }
 * </code></pre>
 */
module jp.cafebabe.pochi.clpond {
    requires io.vavr;
    requires org.objectweb.asm;

    exports jp.cafebabe.clpond.entities;
    exports jp.cafebabe.clpond.source;
    exports jp.cafebabe.clpond.sink;
    exports jp.cafebabe.clpond.source.factories;
    exports jp.cafebabe.clpond.sink.factories;
}