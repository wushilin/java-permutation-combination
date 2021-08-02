# java-permutation-combination
A memory efficient java implementation of combination and permutation iterators.

It uses only a single list for iteration. It is using lexical order for permutation and combination.

Based on test, 400M iterations can be done in a few secodns, and no memory overhead, at all!

# Importing the library

```java
#in build.gradle, dependencies:

In Groovy
implementation 'net.wushilin:permutation-and-combination:1.0.0'
```

In maven
```xml
<dependency>
  <groupId>net.wushilin</groupId>
  <artifactId>permutation-and-combination</artifactId>
  <version>1.0.0</version>
</dependency>
```

In kotlin:
```java
implementation("net.wushilin:permutation-and-combination:1.0.0")
```

In Scala:
```java
libraryDependencies += "net.wushilin" % "permutation-and-combination" % "1.0.0"
```

Raw lib file:
```
Down load this and save to your lib folder.
https://repo1.maven.org/maven2/net/wushilin/permutation-and-combination/1.0.0/permutation-and-combination-1.0.0.jar
```

# Usage:
See examples for usage. Happy coding!

## Combinations
```java
package examples;

import net.wushilin.combperm.CombinationIterable;

import java.util.Arrays;
import java.util.List;

public class TestCombination {
    public static void main(String[] args) {
        CombinationIterable<String> iter = new CombinationIterable(Arrays.asList("A", "B", "C", "D", "E", "F", "G"), 3);
        System.out.println(iter.stream().count());
        iter.stream().forEach(i->System.out.println(i));

        CombinationIterable<Integer> test = new CombinationIterable<Integer>(Arrays.asList(), 0);
        System.out.println(">>>" + test.stream().count());
        test.stream().forEach(it -> System.out.println(it));
    }
}
```

## Permutations

```java

package examples;

import net.wushilin.combperm.PermutationIterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TestPermutation {
    public static void main(String[] args) {
        PermutationIterable<String> permItera = new PermutationIterable<String>(Arrays.asList("A", "B", "C", "D", "E"), 3);
        System.out.println(permItera.stream().count());
        permItera.stream().forEach(it -> System.out.println(it));

        PermutationIterable<Integer> test1 = new PermutationIterable<Integer>(Arrays.asList(1), 1);
        System.out.println(test1.stream().count());
        test1.stream().forEach(System.out::println);

        List<Integer> candidates = new ArrayList<>();
        for(int i = 0; i < 12;i++) {
            candidates.add(i);
        }
        candidates.add(1);
        PermutationIterable<Integer> test3 = new PermutationIterable(candidates, candidates.size());
        System.out.println(test3.stream().count());
    }
}

```
