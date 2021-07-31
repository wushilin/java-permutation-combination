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
