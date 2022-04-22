# pochi-cli

## :speaking_head: Description



## :runner: Usage

```shell
Usage: pochi [-hV] [-c=<configPath>] [COMMAND]
Java Birthmark Toolkit
  -c, --config=<configPath>
                  specify the configuration file.
  -h, --help      print this message
  -V, --version   print version and exit
Commands:
  help     Displays help information about the specified command
  compare  Compares the given birthmarks by a round-robin
  extract  Extracts the birthmarks from given targets
  info     Prints information of processors in pochi
  run      Runs the given scripts for birthmarking
  perform  Extracts birthmarks and compares the extraction results by a
             round-robin
  shell    Starts interactive shell for birthmarking (not implemented yet)
```

### `pochi compare`

Compares the given birthmarks by the certain algorithm and output the resultant similarities.

```shell
Usage: pochi compare -a=ALGORITHM [-d=DEST] [BIRTHMARKs...]
compares the given birthmarks by a round-robin
      [BIRTHMARKs...]   birthmarks in json format. If parameters were empty or
                          dash ("-"), read from stdin
  -a, --algorithm=ALGORITHM
                        specify the comparing algorithm. info command shows
                          availables
  -d, --dest=DEST       specify the destination. If this option is absent or
                          dash ("-"), output to stdout .
```

### `pochi extract`

Extracts the birthmarks from the given files and output birthmarks.

```shell
Usage: pochi extract -b=BIRTHMARK [-c=CONTAINER] [-d=DEST]
                     CLASS|ZIP|JAR|WAR_FILEs...
extract the birthmarks from given targets
      CLASS|ZIP|JAR|WAR_FILEs...
                    targets of birthmark extraction
  -b, --birthmark=BIRTHMARK
                    specify the birthmark type. This option is mandatory
  -c, --container=CONTAINER
                    specify the container type. Available: list, set, vector,
                      graph. Default: list
  -d, --dest=DEST   specify the destination. If this option is absent or dash
                      ("-"), output to stdout.
```

### `pochi perform`

Performs `extract` and then `compare` the results of `extract`.

```sh
Usage: pochi perform -a=ALGORITHM -b=BIRTHMARK [-c=CONTAINER] [-d=DEST]
                     CLASS|ZIP|JAR|WAR_FILEs...
extract birthmarks and compare the extraction results by a round-robin
      CLASS|ZIP|JAR|WAR_FILEs...
                    targets of birthmark extraction
  -a, --algorithm=ALGORITHM
                    specify the comparing algorithm. info command shows
                      availables
  -b, --birthmark=BIRTHMARK
                    specify the birthmark type. This option is mandatory
  -c, --container=CONTAINER
                    specify the container type. Available: list, set, vector,
                      graph. Default: list
  -d, --dest=DEST   specify the destination. If this option is absent or dash
                      ("-"), output to stdout
```

#### `pochi info`

Prints information of pochi.

```shell
Usage: pochi info [-i[=<targets>...]]...
print information of processors in pochi
  -i, --info[=<targets>...]
         specify the printing information. Availables: all, extractors,
           pairers, algorithms, relationers, rules, properties, and runtimes.
           Default: all
```

#### `pochi run`

Runs the given script file.

```shell
Usage: pochi run [-e=ONE_LINER] [-E=ENGINE_NAME] [-w=DIR] [SCRIPT] [ARGS...]
run the given scripts for birthmarking
      [SCRIPT]               specify a script file for birthmarking
      [ARGS...]              args for the script file
  -e=ONE_LINER               specify a command line script
  -E, --engine=ENGINE_NAME   specify the script engine.  Available values are
                               shown in info subcommand. Default: groovy
```

#### Supported runtime environment

The following languages are supported by no additional settings.

| Languages  | Script Name | Additional Requirements |
| ---------- | ----------- | ----------------------- |
| Groovy     | Groovy      | Default                 |
| JavaScript | `Graal.js`  | GraalVM 17              |

The following languages are should update some settings to use them.

| Languages  | Script Name   | Additional Requirements   |
| ---------- | ------------- | ------------------------- |
| Python 2.x | `jython`      | jython-slim               |
| Python 3.x | `graalpython` | Graal.Python (GraalVM 17) |
| Ruby       | ruby          | TruffleRuby (GraalVM 17)  |



### `pochi shell`

Runs on interactive mode (not implemented yet).

```shell
pochi [GLOBAL_OPTS] shell [OPTIONS] 
GLOBAL_OPTS
    -c, --config <CONFIG>          specify the configuration file.
OPTIONS
        --runtime <ENV>      specify the runtime environment.  Available: ruby, python, javascript, kotlin, scala.
```