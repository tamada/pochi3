#! /bin/bash

buildlibs=$(ls build/libs/*)
classpath=$(echo -n $buildlibs | tr ' ' ':')

DIST=build/generated/completions/bash

mkdir -p $DIST
java -cp "$classpath" picocli.AutoComplete \
    -n pochi3 \
    --force \
    --completionScript $DIST/pochi3 \
    jp.cafebabe.pochi.cli.Pochi
