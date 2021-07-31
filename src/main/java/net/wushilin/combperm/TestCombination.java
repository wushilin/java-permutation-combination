package net.wushilin.combperm;

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
