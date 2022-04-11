# clpond

This tool is to read/store class files from/to directories, jar file, and war files.
[kunai](https://github.com/tamada/kunai) is same tool, however, kunai is for Java 7 or before.
clpond implemented for Java 17 and used streaming API.

## Simple use case

```java
// Reading
Path sourcePath = Paths.get("path/of/source/file/or/directory");
DataSourceBuilder builder = DataSourceBuilder.instance();
try(DataSource source = builder.build(sourcePath)){
    // Storing
    Path outputPath = Paths.get("path/of/output/file.jar");
    DataSinkFactory sinkFactory = DataSinkFactory.instance();
    try(DataSink sink = sinkFactory.build(outputPath)){
        sink.consume(source);
        //     above lines means following code.
        // source.forEach(entry -> {
        //     try{ sink.consume(entry); }
        //     catch(Exception e){ }
        // });
    }
}
```
