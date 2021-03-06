---
title: ":house: Home"
date: 2021-05-01
draft: false
---

[![build](https://github.com/tamada/pochi/workflows/build/badge.svg)](https://github.com/tamada/pochi/actions?query=workflow%3Abuild)
[![Coverage Status](https://coveralls.io/repos/github/tamada/pochi/badge.svg?branch=main)](https://coveralls.io/github/tamada/pochi?branch=main)
[![codebeat badge](https://codebeat.co/badges/8e8c5e70-cb07-4f58-941c-3ddb64f3c059)](https://codebeat.co/projects/github-com-tamada-pochi-main)

[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg?style=flat)](https://github.com/tamada/pochi/blob/master/LICENSE)
[![Version](https://img.shields.io/badge/Version-{{< version >}}-green.svg)](https://github.com/tamada/pochi/releases/tag/v{{< version >}})
[![DOI](https://img.shields.io/badge/DOI-10.5281/zenodo.4271132-green.svg)](https://zenodo.org/badge/latestdoi/82773287)

[![Javadoc](https://img.shields.io/badge/Javadoc-v{{< version >}}-blue?logo=java)](https://tamada.github.io/pochi/apidocs)
[![Docker](https://img.shields.io/badge/Docker-ghcr.io%2Ftamada%2Fpochi%3A{{< version >}}-blue?logo=docker)](https://github.com/users/tamada/packages/container/package/pochi)
[![GitHub Discussion](https://img.shields.io/badge/GitHub-Discussions-blue?logo=github)](https://github.com/tamada/pochi/discussions)


**pochi** is the birthmarking toolkit aims to detect the software theft.
The software birthmarks are the native characteristics extracted from the executables.
And then, the similarities between two birthmarks shows the copy relation possibilities between originals.
**pochi** supports the extraction of birthmarks the JVM platform, and supports several algorithms for comparing.
By following the format of the birthmarks, **pochi** can compare the birthmarks from non-JVM platform.

## :bookmark: Table of Contents

* [:books: Birthmarks](birthmarks)
    * [:green_book: Definition of Birthmarks](birthmarks#-definition-of-birthmarks)
    * [:blue_book: Types of Birthmarks](birthmarks#-types-of-birthmarks)
    * [:orange_book: Similarities](birthmarks#-similarities)
    * [:closed_book: Theft detection process by birthmarks](birthmarks#-theft-detection-process-by-birthmarks)
* [:newspaper: What is **pochi**](description)
    * [:key: Key idea](description#-key-idea)
    * [:fork_and_knife: Usage](description#-usage)
        * [:runner: CLI Interface](description#-cli-interface)
        * [:whale: Docker](description#-docker)
    * [:swimmer: The birthmarking flow](description#-the-birthmarking-flow)
* [:anchor: Install](install)
    * [:beer: Homebrew](install#-homebrew)
    * [:muscle: Compiling pochi yourself](install#-compiling-pochi-yourself)
    * [:package: Maven repository](install#-maven-repository)
    * [:briefcase: Requirements](install#-requirements)
        * [:pouch: Modules](install#-modules)
* [:mountain_bicyclist: Algorithms](algorithms)
  * [Pairer algorithms](algorithms/#pairer-algorithms)
  * [Comparator algorithms](algorithms/#comparator-algorithms)
    * [Euclidean similarity](algorithms/#euclidean-similarity)
* [:ant: Examples](examples)
    * [:one: `printing_args.groovy`](examples#1-printing_argsgroovy)
    * [:two: `printing_pochi_info.groovy`](examples#2-printing_pochi_infogroovy)
    * [:three: `extracting_birthmarks.groovy`](examples#3-extracting_birthmarksgroovy)
    * [:four: `filtering_source.groovy`](examples#4-filtering_sourcegroovy)
    * [:five: `comparing_birthmarks.groovy`](examples#5-comparing_birthmarksgroovy)
    * [:six: `comparing_birthmarks_with_specified_pair_matcher.groovy`](examples#6-comparing_birthmarks_with_specified_pair_matchergroovy)
    * [:seven: `registering_new_extractor.groovy`](examples#7-registering_new_extractorgroovy)
* [:smile: About](about)
    * [:scroll: License](about#-license)
    * [:man_office_worker: Developers :woman_office_worker:](about#-developers-)
    * [:question: Icon of pochi](about#-icon-of-pochi)
    * [:surfer: References](about#-references)
        * [:basketball: Surveys](about#-surveys)
        * [:soccer: Articles of supported birthmark types](about#-articles-of-supported-birthmark-types)
        * [:tennis: Articles by H. Tamada](about#-articles-by-h-tamada)
* [:smile_cat: API document](apidocs)
