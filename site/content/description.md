---
title: ":newspaper: What is pochi"
date: 2022-05-02
draft: false
weight: 50
---

## :key: Key idea

Almost traditional birthmark systems are usually simple input and output.
For example, users specify the type of target birthmark, $p$ and $q$ as input, and obtains result of similarities as output.
The process of such system is hard to use.
Because, when the user would perform some process (ex. filtering results), it requires the update of birthmark system.

In general, the process of birthmarking is composed of extraction phase, and comparison phase.
Also, the both phases are performed to huge amount of programs.

Therefore, the birthmark system accept the script file as input, and the user describes extraction and comparison of birthmarks in the script file.
If the user would perform filtering process, it is easy to perform the process.

{{< gototop >}}

## :swimmer: The birthmarking flow

The birthmarking process in **pochi** shows in Figure 1.

{{< image src="images/birthmarking_process.svg" caption="Figure 1: birthmarking process in pochi" >}}

The followings are the description of the nodes and edges in the flowchart.

* `Classes`
    * is the Java class files, almost included in the jar files, and the directory.
      **pochi** treats them as the object of [`DataSource`](../apidocs/jp.cafebabe.pochi.clpond/jp/cafebabe/kunai/source/DataSource.html)
* [`Birthmarks`]((../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/entities/Birthmarks.html))
    * shows the extracted characteristics from Java class files by certain method.
      In the **pochi**, [`Birthmarks`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/entities/Birthmarks.html) and [`Birthmark`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/entities/Birthmark.html) show the extracted birthmarks.
      The string representation of [`Birthmark`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/entities/Birthmark.html) (the return value of `toString` method) is CSV format, therefore, we can store them into some csv file.
* [`Pairer`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/entities/Pairer.html)
    * shows the pair list of extracted birthmarks.
      **pochi** shows these objects as [`Pair<Birthmark>`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/entities/Pair.html).
* [`Comparisons`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/comparators/Comparisons.html)
    * represents the comparison results of birthmarks by the certain similarity calculation algorithm.
      **pochi** shows these objects as [`Comparisons`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/comparators/Comparisons.html) and [`Comparison`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/comparators/Comparison.html).
* [`Extractor`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/extractors/Extractor.html)
    * extracts birthmarks from given class files by the certain algorithm.
      In the script file, `pochi.extractor("ALGORITHM_NAME")` obtains the instance of [`Extractor`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/extractors/Extractor.html).
* [`Parser`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/BirthmarkParser.html)
    * parses the given csv file and build [`Birthmarks`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/entities/Birthmarks.html) object.
      To get the instance of [`BirthmarkParser`](../apidocs/jp.cafebabe.birthmarks/jp/cafebabe/birthmarks/BirthmarkParser.html), call `pochi.parser()` method in the script file.
