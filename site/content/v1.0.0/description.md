---
title: ":newspaper: What is pochi"
date: 2019-12-02
draft: false
weight: 50
---

## :key: Key idea

Almost traditional birthmark systems are usually simple input and output.
For example, an user specifies the type of target birthmark, $p$ and $q$ as input, and obtains result as output.
The process of such system is hard to use.
Because, when the user would perform some process (ex. filtering results), it requires the update of birthmark system.

In general, the process of birthmarking is composed of extraction phase, and comparison phase.
Also, the both phases are performed to huge amount of programs.

Therefore, the birthmark system accept the script file as input, and the user describes extraction and comparison of birthmarks in the script file.
If the user would perform filtering process, it is easy to perform the process.

{{< gototop >}}

## :fork_and_knife: Usage

### :runner: CLI Interface

```sh
$ extractors [OPTIONS] [SCRIPT [ARGV...]]

OPTIONS:
    -e <EXPRESSION>     one line of script.
    -config <CONFIG>    specify the configuration file.

    -help               print this message and exit.
    -version            print the version.
    -license            print the license.

SCRIPT [ARGV...]
    Specify the script file for executing.
    Suitable script engine is parsed from extension of the script file.
    ARGV is arguments list for the script.

NO SCRIPT FILE, and NO ONE LINER
    If no script file and no one liner were specified, extractors runs on interactive mode.
```

#### Script file

The script files are parsed by the Nashorn (JavaScript engine for Java).
In the future version of **pochi**, other script languages will be supported, because [Nashorn was deprecated on Java SE 11](http://openjdk.java.net/jeps/335).

### :whale: Docker

Container images of **pochi** for Docker are:

* `tamada/pochi`
    * `1.0.0`, `latest`


To run **pochi** on Docker container OS'

```sh
$ docker run --rm -v "$PWD":/home/extractors tamada/extractors [OPTIONS] [SCRIPT [ARGV...]]
```


* `OPTIONS`: the options for **pochi**.
* `[SCRIPT [ARGV...]]`: script file for **pochi**.
* `--rm`: remove the container after running.
* `-v "$PWD":/home/pochi`: share volume `$PWD` in host OS to `/home/pochi` in the container OS.
    * `$PWD` must be the absolute path.

{{< gototop >}}

## Examples of script files

### Extracting birthmarks

```javascript:extract.js
extractor = engine.extractor("uc");
source = engine.source("target-test-classes/resources");
birthmarks = extractor.extract(source);
birthmarks.forEach(function(b){
   // If the filtering is required, the filtering routine locates here!
   print(b);
});

// to compute the execution time, use the commented script.
//   extractor = engine.extractor("uc");
//   source = engine.source("target/test-classes/resources");
//   result = {}
//   result.time = sys.measure(function(){
//       result.birthmarks = extractor.extract(source);
//   })
//   result.birthmarks.forEach(function(birthmark){
//       print(birthmark);
//   });
//   print(result.time + " ns");
```

{{< gototop >}}
