package net.wushilin.combperm;

import java.util.Arrays;

public class TestFullPermutationIterator {
    public static void main(String[] args) {
        FullPermutationIterator<String> permIter = new FullPermutationIterator<String>().init(Arrays.asList("A", "B", "C", "D"));
        while(permIter.hasNext()) {
            System.out.println(permIter.next());
        }

        permIter = new FullPermutationIterator<Integer>().init(Arrays.asList());
        while(permIter.hasNext()) {
            System.out.println(permIter.next());
        }
    }
}
