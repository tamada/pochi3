---
title: ":fork_and_knife: Usage"
---

## :runner: CLI Interface

```sh
Usage: pochi [-hV] [-c=<configPath>] [COMMAND]
Java Birthmarking Toolkit
  -c, --config=<configPath>     specify the configuration file.
  -P, --property <KEY=VALUE>    specify the property key and value. 
  -h, --help                    print this message.
  -V, --version                 print version and exit.
Commands:
  help     Displays help information about the specified command.
  compare  compares the given birthmarks by a round-robin.
  extract  extract the birthmarks from given targets.
  info     print information of processors in pochi.
  run      run the given scripts for birthmarking.
  perform  extract birthmarks and compare the extraction results by a round-robin.
  shell    start interactive shell for birthmarking (not implemented yet).
```

#### Information of **pochi**

This subcommand prints the information of **pochi**, such as supported algorithms, scripts, etc. 

```shell
Usage: pochi info [-i[=<targets>...]]...
print information of processors in pochi.
  -i, --info[=<targets>...]
         specify the printing information.
           Availables: all, extractors, pairers, algorithms, relationers, rules, properties, and runtimes.
           Default: all
```

#### Extracting birthmarks from class files

This subcommand extracts the birthmarks from the given targets (supports class files, zip, jar, war files).
And prints the extracted birthmarks in the JSON format.
The printed JSON is for the input to `compare` subcommand.

```shell
Usage: pochi extract -b=BIRTHMARK [-c=CONTAINER] [-d=DEST]
                     CLASS|ZIP|JAR|WAR_FILEs...
extract the birthmarks from given targets
      CLASS|ZIP|JAR|WAR_FILEs...
                    targets of birthmark extraction.
  -b, --birthmark=BIRTHMARK
                    specify the birthmark type. This option is mandatory.
                    Available: show by info subcommand.
  -c, --container=CONTAINER
                    specify the container type.
                    Available: list, set, vector, graph.
                    Default: list
  -d, --dest=DEST   specify the destination. 
                    If this option is absent or dash ("-"), output to stdout.
```

The birthmark types show a particular aspect of the extraction from the programs.
Then, **pochi** stores the elements extracted from each program into a container.
The supported containers are: `list`, `set`, `vector`, and `graph`.
**Pochi** automatically converts the container type by the following flow.
Note that container types are `list`, `graph`, `vector`, and `set` in the order of the amount of the information.
That is, converting `list` to `set` drops some information, `set` to `list` could not restore information perfectly.

{{< mermaid >}}
flowchart TD
  list --> set
  frequency --> set
  list --> vector
  g[graph] --> set
  g --> vector
{{< /mermaid >}}

### Comparing the birthmarks

This subcommand compares the extracted birthmarks by the given comparing algorithm.
Note that comparators have the setting of acceptable container types

```shell
Usage: pochi compare -a=ALGORITHM [-d=DEST] [BIRTHMARKs...]
compares the given birthmarks by a round-robin.
      [BIRTHMARKs...]   birthmarks in json format. If parameters were empty or
                          dash ("-"), read from stdin.
  -a, --algorithm=ALGORITHM
                        specify the comparing algorithm. This option is mandatory.
                        Available: show info subcommand.
  -d, --dest=DEST       specify the destination.
                        If this option is absent or dash ("-"), output to stdout.
```

Each comparing algorithm has the acceptable container types.
If container type of given birthmarks are mismatched for the container type of algorithm,
**pochi** converts the birthmark for suitable container type.

### Extracting the birthmarks and Comparing them

This subcommand is the combination of `extract` and `compare` subcommands.
Therefore, this subcommand has the options from both subcommands.

```shell
Usage: pochi perform -a=ALGORITHM -b=BIRTHMARK [-c=CONTAINER] [-d=DEST]
                     CLASS|ZIP|JAR|WAR_FILEs...
extract birthmarks and compare the extraction results by a round-robin.
      CLASS|ZIP|JAR|WAR_FILEs...
                    targets of birthmark extraction.
  -a, --algorithm=ALGORITHM
                    specify the comparing algorithm.
                    Available algorithm show info subcommands.
  -b, --birthmark=BIRTHMARK
                    specify the birthmark type. This option is mandatory.
                    This option is mandatory.
  -c, --container=CONTAINER
                    specify the container type.
                    Available: list, set, vector, graph.
                    Default: list.
  -d, --dest=DEST   specify the destination. If this option is absent or dash
                      ("-"), output to stdout.
```

### :whale: Docker

Container images of **pochi** for Docker are:

* [`ghcr.io/tamada/pochi`](https://github.com/users/tamada/packages/container/package/pochi)
    * `2.6.0`, `latest`
    * `2.5.2`
    * `2.5.1`
    * `2.5.0`
    * `2.4.6`
        * `2.4.0`
        * `2.3.24`
        * `2.3.23`
        * `2.3.21`
        * `2.3.19`
        * `2.3.18`
        * `2.3.17`
        * `2.3.16`
        * `2.3.10`
        * `2.3.2`
        * `2.3.1`
        * `2.3.0`
        * `2.2.0`
        * `2.1.0`
        * `2.0.0`
            * accept only `.groovy` script files.
        * `1.0.0`
            * accept only `.js` script files.

[![Docker](https://img.shields.io/badge/Docker-ghcir.io%2Ftamada%2Fpochi%3A2.6.0-blue?logo=docker)](https://github.com/users/tamada/packages/container/package/pochi)

To run **pochi** on Docker container OS, type the following commands.

```sh
$ docker run --rm -it -v "$PWD":/home/extractors ghcr.io/tamada/extractors:latest [OPTIONS] [SCRIPT [ARGV...]]
```

* `OPTIONS`: the options for **pochi**.
* `[SCRIPT [ARGV...]]`: script file for **pochi**.
* `--rm`: remove the container after running.
* `-it`: interactive and tty mode.
* `-v "$PWD":/home/pochi`: share volume `$PWD` in host OS to `/home/pochi` in the container OS.
    * `$PWD` must be the absolute path.

`ghcr.io/tamada/pochi` does not include groovy interactive shell environment.
Therefore, it does not work on interactive mode.
If want to run `pochi` on the interactive mode, use `ghcr.io/tamada/pochi-groovysh` image instead.

#### Environments in the docker container

* `USER`: `pochi`
* `WORKDIR`: `/home/pochi`
* `JAVA_HOME`: `/opt/java` (symbolic link from `/opt/openjdk-11-minimal`)
    * This Java runtime environment do not include unnecessary modules.
* `POCHI_HOME`: `/opt/pochi` (symbolic link from `/opt/pochi-x.x.x`)
* `GROOVY_HOME`: `/opt/groovy` (symbolic link from `/opt/groovy-x.x.x`, only exist on `ghcr.io/tamada/pochi-groovysh` image)

{{< gototop >}}
