---
title: ":anchor: Install"
date: 2019-11-29"
draft: false
---

## :beer: Homebrew

For macOS user, **pochi** supports homebrew installation.

```sh
$ brew install tamada/brew/extractors
```

{{< gototop >}}

## :muscle: Compiling **pochi** yourself

For building yourself, clone the source code from GitHub, and build it with [Maven](https://maven.apache.org/).

```sh
$ git clone https://github.com/tamada/pochi.git
$ cd extractors
$ mvn package
```

Then, maven packages **pochi** and generates `pochi-1.0.0-fat.jar`, and `pochi-1.0.0-all.jar` onto `distribution/target` directory.

{{< gototop >}}

## :briefcase: Requirements

* [Jackson](https://github.com/FasterXML/jackson) 2.10.0
* [Args4j](https://github.com/kohsuke/args4j) 2.33
* [JLine](https://mvnrepository.com/artifact/org.jline/jline/3.13.2) 3.13.2
* [ASM](https://asm.ow2.io/) 7.2

### :pouch: Modules

* [java.base](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/module-summary.html)
* [java.logging](https://docs.oracle.com/en/java/javase/11/docs/api/java.logging/module-summary.html)
* [java.scripting](https://docs.oracle.com/en/java/javase/11/docs/api/java.scripting/module-summary.html)
* [java.xml](https://docs.oracle.com/en/java/javase/11/docs/api/java.xml/module-summary.html)
* [jdk.zipfs](https://docs.oracle.com/en/java/javase/11/docs/api/jdk.zipfs/module-summary.html)
* [jdk.scripting.nashorn](https://docs.oracle.com/en/java/javase/11/docs/api/jdk.scripting.nashorn/module-summary.html)
* jdk.scripting.nashorn.shell

{{< gototop >}}
