---
title: ":anchor: Install"
date: 2021-07-19
draft: false
---

## :beer: Homebrew

For macOS user, **pochi** supports homebrew installation.

```sh
$ brew tap tamada/brew
$ brew install tamada/brew/pochi3
```

{{< gototop >}}

## :muscle: Compiling **pochi** yourself

For building yourself, clone the source code from GitHub, and build it with [Maven](https://maven.apache.org/).

```sh
$ git clone https://github.com/tamada/pochi3.git
$ cd pochi3
$ gradle build
```

Then, `./bin/make_dist.sh` creates `dist/pochi-${VERSION}` directory contains the interpreter (`bin/pochi`), and dependent jar files (`lib`), documents (`docs`) and misc files (`README.md`, `LICENSE`, `completions`, `examples`, and `Dockerfile`).

{{< gototop >}}

## :package: Maven repository

Copy and paste the following snippet into your `pom.xml`.

```xml
  <repositories>
    <repository>
      <id>tamada_github</id>
      <name>Apache Maven Packages of tamada</name>
      <url>https://tamada.github.io/maven</url>
    </repository>
  </repositories>
```

Then, add the dependencies of your `pom.xml`.

| groupId            | artifactId   | version           |
|--------------------|--------------|-------------------|
|`jp.cafebabe.pochi` | `clpond`     | `{{< version >}}` |
|`jp.cafebabe.pochi` | `pochi-core` | `{{< version >}}` |
|`jp.cafebabe.pochi` | `pochi-api`  | `{{< version >}}` |
|`jp.cafebabe.pochi` | `pochi-cli`  | `{{< version >}}` |


## :briefcase: Requirements

* [google/gson](https://github.com/google/gson) 2.9.0
* [Vavr](https://www.vavr.io/) 0.10.4
* [ASM](https://asm.ow2.io/) 9.2
* [Groovy](https://groovy-lang.org) 4.0.1
    * [groovy-jsr223](https://groovy-lang.org/integrating.html#jsr223) 4.0.1
* [picocli](https://picocli.info) 4.6.3

### :pouch: Modules

* [`jp.cafebabe.pochi`](https://tamada.github.io/pochi/apidocs/jp.cafebabe.pochi/module-summary.html)
    * [`jp.cafebabe.birthmarks`](https://tamada.github.io/pochi/apidocs/jp.cafebabe.birthmarks/module-summary.html)
        * [`com.fasterxml.jackson.databind`](https://github.com/FasterXML/jackson-databind)
        * [`io.vavr`](https://www.vavr.io/)
        * [`jp.cafebabe.kunai`](https://tamada.github.io/pochi/apidocs/jp.cafebabe.kunai/module-summary.html)
            * [`org.objectweb.asm`](https://asm.ow2.io/)
            * [`jdk.zipfs`](https://docs.oracle.com/en/java/javase/11/docs/api/jdk.zipfs/module-summary.html)
    * [`java.logging`](https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/module-summary.html)
* [`org.codehaus.groovy`](https://groovy-lang.org/)
    * [`java.scripting`](https://docs.oracle.com/en/java/javase/11/docs/api/java.scripting/module-summary.html)
    * [`java.desktop`](https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/module-summary.html)
    * [`java.sql`](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/module-summary.html)
    * [`java.xml`](https://docs.oracle.com/en/java/javase/11/docs/api/java.xml/module-summary.html)
* [`jp.cafebabe.pochicmd`](https://tamada.github.io/pochi/apidocs/jp.cafebabe.pochicmd/module-summary.html)
    * [`info.picocli`](https://picocli.info)
    * [`java.scripting`](https://docs.oracle.com/en/java/javase/11/docs/api/java.scripting/module-summary.html)

### :steam_locomotive: Module Graph

{{< image src="images/module-graph.svg" caption="Figure 1. :steam_locomotive: Module Graph" >}}

{{< gototop >}}
