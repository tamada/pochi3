---
title: ":mountain_bicyclist: Algorithms"
---

## Table of Contents

* [Pairer Algorithms](#pairer-algorithms)
  * [Guessed](#guessed)
  * [Round-robin](#round-robin)
  * [Specified](#specified)
* [Comparator Algorithms](#comparator-algorithms)
  * [List birthmarks](#list-birthmarks)
    * [Edit distance](#edit-distance)
    * [Longest common subsequence](#longest-common-subsequence)
    * [Smith Waterman](#smith-waterman)
  * [Set birthmarks](#set-birthmarks)
    * [Dice index](#dice-index)
    * [Jaccard index](#jaccard-index)
    * [Simpson index](#simpson-index)
  * [Vector birthmarks](#vector-birthmarks)
    * [Cosine Similarity](#cosine-similarity)
    * [Euclidean Similarity](#euclidean-similarity)
    * [Manhattan Similarity](#manhattan-similarity)

## Pairer Algorithms

`Pairer` accepts one or two `Birthmarks` objects and returns the stream object of `Pair<Birthmark>` by the given algorithm.

Constructing `Pairer` object is as follows.

```groovy
Pairer construct(String pairerType, Configuration config) {
    return PairerBuilderFactory.build(pairerType)
        .build(config);
}
```

### Guessed

This algorithm construct pairs by guessing the name from `Birthmark` objects.
We can specify the matching algorithm by class name, FQCN (Fully qualified class name; package name and class name).
Available types are: `guessed-simple`, and `guessed-fully`. 

### Round-Robin

This algorithm constructs pairs by round-robin of possible pairs from `Birthmark`s in `Birthmarks` objects.
Let us consider accepting `Pairer` accepts two `Birthmarks` objects `b1(a, b)` and `b2 (x, y, z)`.
`a`, `b`, `x`, `y`, `z` are the `Birthmark` objects.
The resultant pairs are `(a, x)`, `(a, y)`, `(a, z)`, `(b, x)`, `(b, y)`, `(b, z)`, `(c, x)`, `(c, y)`, and `(c, z)`.

In the other hand, let us consider accepting `Pairer` one `Birthmarks` objects `b(a, b, c)`,
and, `a`, `b`, `c` are `Birthmark` objects.
In the case of `round-robin`, the resultant pairs are `(a, a)`, `(a, b)`, `(a, c)`, `(b, b)`, `(b, c)`, and `(c, c)`.
Then, the resultant pairs are `(a, b)`, `(a, c)`, and `(b, c)` by `round-robin2`.


### Specified

## Comparator Algorithms

### List birthmarks

#### Edit distance

This algorithm counts the minimum number of operations required to transform one list to the other.
Then, the similarity based on edit distance is calculated by $1 - \frac{d(A, B)}{\max(|A|, |B|)}$, and
$d(A, B)$ is the result of the edit distance between $A$ and $B$. 
Note that the edit distance is also called levenshtein distance.

#### Longest common subsequence

This algorithm (LCS) finds the longest subsequence common to both sequences in the list.
Then, the similarity based on edit distance is calculated by $1- \frac{d(A, B)}{\min(|A|, |B|)}$, and
$d(A, B)$ is the result of LCS between $A$ and $B$.

#### Smith-Waterman

### Set birthmarks

#### Dice index

$\frac{2|A \cap B|}{|A| + |B|}$

#### Jaccard index

$\frac{|A \cap B|}{|A \cup B|}$

#### Simpson index

This similarity calculation algorithm is known as overlap coefficient. 

$\frac{|A \cap B|}{\min(|A|, |B|)}$

### Vector birthmarks

In the comparison algorithms for the vector birthmarks, 
we assume the following two birthmarks are given.

$\boldsymbol{p} = (p_1, p_2, ..., p_n), \boldsymbol{q}=\(q_1, q_2, ..., q_n\)$

#### Cosine Similarity

The similarity from this algorithm is cosine for the angle of two vectors. 

$s(\boldsymbol{p}, \boldsymbol{q}) = \cos \theta = \frac{\boldsymbol{p}\cdot\boldsymbol{q}}{|\boldsymbol{p}||\boldsymbol{q}|} $

#### Chebyshev Similarity

This algorithm calculates similarity between two vector birthmarks by the following formula.
This similarity algorithm is based on euclidean distance.

$d(\boldsymbol{p}, \boldsymbol{q}) = \max{|q_i - p_i|}$

$s(\boldsymbol{p}, \boldsymbol{q}) = \frac{1}{1+d(\boldsymbol{p}, \boldsymbol{q})}$

#### Euclidean Similarity

This algorithm calculates similarity between two vector birthmarks by the following formula.
This similarity algorithm is based on euclidean distance.

$d(\boldsymbol{p}, \boldsymbol{q}) = \sqrt{\sum_{i=1}^n{(q_i - p_i)^2}}$

$s(\boldsymbol{p}, \boldsymbol{q}) = \frac{1}{1+d(\boldsymbol{p}, \boldsymbol{q})}$

#### Manhattan Similarity

This algorithm calculates similarity between two vector birthmarks by the following formula.
This similarity algorithm is based on manhattan distance.

$d(\boldsymbol{p}, \boldsymbol{q}) = \sum_{i=1}^n{|q_i - p_i|}$

$s(\boldsymbol{p}, \boldsymbol{q}) = \frac{1}{1+d(\boldsymbol{p}, \boldsymbol{q})}$

#### Differences among chebyshev, euclidean and manhattan distance

Those three distances are summarized into minkowski distance defined by the following formula.

$d_k(\boldsymbol{p}, \boldsymbol{q})=(\sum_{i=1}^n |p_i - q_i|^k)^{\frac{1}{k}}$

Chebyshev distance is $k=\infty$, euclidean distance is $k=2$, and manhattan distance is $k=1$.
Figure 1 shows the differences among three distances.
In Fig. 1, the euclidean distance shows the purple line, the manhattan distance is the total length of yellow lines,
and the chebyshev distance (dashed line) is the max length of each dimension.

{{< image src="/images/distances.svg" title="Figure 1. Illustration of Chebyshev, Euclidean, and Manhattan distances">}}

