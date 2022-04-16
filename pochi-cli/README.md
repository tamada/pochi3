# pochi-cli

```shell
pochi [GLOBAL_OPTS] <SUBCOMMANDS [ARGS...]>
GLOBAL_OPTS
    -c, --config <CONFIG>    specify the configuration file.
    -h, --help               print this message.
SUBCOMMANDS
    compare    compare the given birthmarks.
    extract    extract birthmarks from the given class files.
    info       print pochi information.
    run        run the given script. 
    shell      run the interactive mode.
```

## `pochi compare`

Compares the given birthmarks by the certain algorithm and output the resultant similarities.

```shell
pochi [GLOBAL_OPTS] compare [OPTIONS] <BIRTHMARKS...>
GLOBAL_OPTS
    -c, --config <CONFIG>          specify the configuration file.
OPTIONS
    -a, --algorithm <ALGORITHM>    specify the comparing algorithm.  This option is mandatory.
    -d, --dest <FILE>              specify the destination. If this option is absent, the results outputs to stdout.
```

## `pochi extract`

Extracts the birthmarks from the given files and output birthmarks.

```shell
pochi [GLOBAL_OPTS] extract [OPTIONS] <JAR_FILE|WAR_FILE|CLASS_FILE|DIR...>
GLOBAL_OPTS
    -c, --config <CONFIG>          specify the configuration file.
OPTIONS
    -b, --birthmark <BIRTHMARK>    specify the birthmark type. This option is mandatory.
    -c, --container <TYPE>         specify the container type. Available: list, set, vector, graph. 
    -d, --dest <FILE>              specify the destination. If this option is absent, the results outputs to stdout.
```

## `pochi info`

Prints information of pochi.

```shell
pochi [GLOBAL_OPTS] info
GLOBAL_OPTS
    -c, --config <CONFIG>    specify the configuration file.
```

## `pochi run`

Run the script file.

```shell
pochi [GLOBAL_OPTS] run <OPTIONS> <SCRIPT>
GLOBAL_OPTS
    -c, --config <CONFIG>    specify the configuration file.
OPTIONS
        --runtime <ENV>      specify the runtime environment.  Available: ruby, python, javascript, kotlin, and scala.
```

### Supported runtime environment

| Languages  | ScriptName | Runtime              |
| ---------- | ---------- | -------------------- |
| Groovy     | `groovy`   | Default              |
| JavaScript | `graaljs`  | Use GraalVM 17       |
| Python     | `python`   | Jython, Graal.Python |
| Ruby       | `ruby`     | JRuby, TruffeRuby    |
| Scala      | `scala`    |                      |
| Kotlin     | `kotlin`   |                      |
| Clojure    | `clojure`  |                      |

## `pochi shell`

Runs on interactive mode.

```shell
pochi [GLOBAL_OPTS] shell [OPTIONS] 
GLOBAL_OPTS
    -c, --config <CONFIG>          specify the configuration file.
OPTIONS
        --runtime <ENV>      specify the runtime environment.  Available: ruby, python, javascript, kotlin, scala.
```