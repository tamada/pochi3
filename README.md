# pochi3

[![build](https://github.com/tamada/pochi3/actions/workflows/build.yaml/badge.svg)](https://github.com/tamada/pochi3/actions/workflows/build.yaml)
[![Coverage Status](https://coveralls.io/repos/github/tamada/pochi3/badge.svg?branch=main)](https://coveralls.io/github/tamada/pochi3?branch=main)

[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://github.com/tamada/pochi3/blob/main/LICENSE)
[![Version](https://img.shields.io/badge/Version-v3.0.0--alpha--14-green.svg)](https://github.com/tamada/pochi3/releases/tag/v3.0.0-alpha-14)

[![DOI](https://zenodo.org/badge/499123744.svg)](https://zenodo.org/badge/latestdoi/499123744)

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

### :whale: Docker

pochi3 supports running on the docker image.

```shell
docker run -it --rm -v $PWD:/app ghcr.io/tamada/pochi3:latest <arguments of pochi3>
```

Following tas are available.
Each image supports `arm64` and `amd64` platform.

* `3.0.0-alpha-14`
  * `3.0.0-alpha-14-distroless`, `3.0.0-alpha-14`, `distroless`, `latest`
  * `3.0.0-alpha-14-fullgrl` `fullgrl`
  * `3.0.0-alpha-14-minimalgrl`, `minimalgrl`

* `distroless` uses google distroless java image for the base image.
* The base image of `fullgrl` is [GraalVM community edition container images](https://github.com/graalvm/container).
* `minimalgrl` shrinks GraalVM by `jlink` for pochi3.

The main difference between them are:
* GraalVM supports to run JavaScript for script engine,
* We try to minimize the image size,

