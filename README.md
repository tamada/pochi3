# pochi3

[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://github.com/tamada/pochi/blob/master/LICENSE)
[![Version](https://img.shields.io/badge/Version-3.0.0-green.svg)](https://github.com/tamada/pochi/releases/tag/v3.0.0)

Detecting the stolen software from enormous amount of software.

## :speaking_head: Overview

**pochi** is the birthmarking toolkit for the JVM platform.
The birthmarks are the native characteristics of software, extracted from binaries.
Then, we compare two extracted birthmarks and computes the similarities between them.
The resultant similarities show the copy relation possibilities between original two binaries.

The new features of **pochi3** comparing to the previous version of **pochi**.

* Supports cli interface for extracting/comparing the birthmarks,
* Defines the format of birthmarks to handle the birthmarks from other languages, and platforms,
* Reorganizing the class libraries,
* Introduce new comparing and pairing algorithms, 

## :runner: Usage

```sh
Usage: pochi [-hV] [-C=<configPath>] [COMMAND]
The Birthmarking Toolkit for the JVM platform.
  -C, --config=<configPath>     specify the configuration file.
  -h, --help                    print this message and exit.
  -V, --version                 print version and exit.
  -P, --property <KEY=VALUE>    specify the property key and its value.
Commands:
  help     Displays help information about the specified command
  compare  compares the given birthmarks by a round-robin.
  extract  extract the birthmarks from given targets.
  info     print information of processors in pochi.
  run      run the given scripts for birthmarking.
  perform  extract birthmarks and compare the extraction results by a round-robin.
  shell    start interactive shell for birthmarking (not implemented yet).
```

### Script file


